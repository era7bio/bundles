package era7.bundles.tests

import ohnosequences.statika._, bundles._, aws._, amazonLinuxAMIs._
import era7.bundles._, std._, awsCompats._

// import cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.ec2.{Tag => Ec2Tag}
import java.io._
import org.scalatest._
import scala.annotation.tailrec
import com.amazonaws.auth._, profile._
import ohnosequences.awstools.regions.Region._
import ohnosequences.awstools.ec2.InstanceType._


class ApplicationTest extends FunSuite with ParallelTestExecution {

  val ec2 = EC2.create(new ProfileCredentialsProvider("default"))
  val testKeyPair = "aalekhin"
  // val testKeyPair = "era7.mmanrique"
  val testRole = Some("era7-projects")

  def launchAndWait(ec2: EC2, name: String, specs: InstanceSpecs): List[ec2.Instance] = {
    ec2.runInstances(1, specs) flatMap { inst =>
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

  def testCompat[C <: AnyAmazonLinuxAMICompatible](comp: C) = {
    test(s"testing ${comp.name}") {
      val specs = comp.instanceSpecs(
        instanceType = m3.medium,
        testKeyPair,
        testRole
      )

      // println(specs.userData)
      val instances = launchAndWait(ec2, comp.name, specs)
      // if it was successful, we kill the instance immediately
      instances.foreach{ _.terminate }
      assert{ instances.length == 1 }
    }
  }

  val compats = List(
    // awsCompats.velvet,
    // awsCompats.samtools,
    // awsCompats.bowtie2,
    // awsCompats.tophat,
    // awsCompats.cufflinks,
    // awsCompats.blast,
    // awsCompats.flash,
    // awsCompats.spades,
    // awsCompats.fastqc
    awsCompats.metaVelvet
  )

  compats.foreach(testCompat)

}
