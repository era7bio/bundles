package ohnosequences.statika.tests

import ohnosequences.statika._, aws._, bundles._
import ohnosequences.statika.bioinfo._

// import cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.ec2.{Tag => Ec2Tag}
import java.io._
import org.scalatest._
import scala.annotation.tailrec
import com.amazonaws.auth._, profile._
import ohnosequences.awstools.regions.Region._
import ohnosequences.awstools.ec2.InstanceType._

import era7.project._, era7.aws._, defs._

class ApplicationTest extends FunSuite with ParallelTestExecution {

  def launchAndWait(ec2: EC2, name: String, specs: InstanceSpecs, number: Int = 1): List[ec2.Instance] = {
    // TODO: run instances in parallel
    ec2.runInstances(number, specs) flatMap { inst =>
      def checkStatus: String = inst.getTagValue("statika-status").getOrElse("...")

      val id = inst.getInstanceId()
      def printStatus(s: String) = println(name+" ("+id+"): "+s)

      inst.createTag(Ec2Tag("Name", name))
      printStatus("launched")

      while(checkStatus != "preparing") { Thread sleep 2000 }
      printStatus("url: "+inst.getPublicDNS().getOrElse("..."))

      @tailrec def waitForSuccess(previous: String): String = {
        val current = checkStatus
        if(current == "failure" || current == "success") {
          printStatus(current)
          current
        } else {
          if (current != previous) printStatus(current)
          Thread sleep 3000
          waitForSuccess(current)
        }
      }

      if (waitForSuccess(checkStatus) == "success") Some(inst) else None
    }
  }


  case object bundlesTest extends Project("BundlesTest")
  case object doTest extends Task(bundlesTest, "dotest")

  object conf extends InstanceConf(
    task = doTest,
    keypair = keypairs.aalekhin,
    instanceType = m1_small,
    comp = velvetCompat
  )

  val ec2 = EC2.create(new ProfileCredentialsProvider("default"))

  test("trying to launch an instance") {
    // println(conf.specs.userData)
    val N = 1
    val instances = launchAndWait(ec2, conf.comp.name, conf.specs, N)
    instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }

}
