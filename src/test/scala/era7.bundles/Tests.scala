package era7.bundles.tests

import ohnosequences.statika._, aws._, bundles._
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

import era7.project._, era7.aws._, defs._, era7.aws.keypairs._

class ApplicationTest extends FunSuite with ParallelTestExecution {

  val ec2 = EC2.create(new ProfileCredentialsProvider("default"))

  // def launchInstances(ec2: EC2, specs: InstanceSpecs, number: Int): List[ec2.Instance] = {
  //   // ec2.runInstances(number, specs)
  //   val price = ec2.getCurrentSpotPrice(specs.instanceType)
  //   ec2.requestSpotInstances(number, price + 0.01, specs)
  // }

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

  object velvetConf extends InstanceConf(
    task = doTest,
    keypair = keypairs.aalekhin,
    instanceType = m1_small,
    comp = velvetCompat
  )

  ignore("trying to launch an instance") {
    // println(velvetConf.specs.userData)
    val N = 1
    val instances = launchAndWait(ec2, velvetConf.comp.name, velvetConf.specs, N)
    // instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }


  case object bio4jBundleTest extends Project("Bio4jBundlesTest")
  case object downloadBio4j extends Task(bundlesTest, "download it")

  object bio4jLiteConf extends InstanceConf(
    task = downloadBio4j,
    keypair = keypairs.aalekhin,
    instanceType = i2_xlarge,
    comp = bio4jLiteCompat
  )

  ignore("trying to download bio4j-lite on an instance") {
    println(bio4jLiteConf.specs.userData)
    val N = 1
    val instances = launchAndWait(ec2, bio4jLiteConf.comp.name, bio4jLiteConf.specs, N)
    // instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }

  case object samtoolsBundleTest extends Project("SamtoolsBundlesTest")
  case object testSamtools extends Task(bundlesTest, "download it")

  object samtoolsConf extends InstanceConf(
    task = testSamtools,
    keypair = new Keypair("era7.mmanrique"),
    instanceType = m3_medium,
    comp = samtoolsCompat
  )

  test("testing samtools") {
    println(samtoolsConf.specs.userData)
    val N = 1
    val instances = launchAndWait(ec2, samtoolsConf.comp.name, samtoolsConf.specs, N)
    // instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }


}
