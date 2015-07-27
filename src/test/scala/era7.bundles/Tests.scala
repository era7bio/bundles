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


class ApplicationTest extends FunSuite with ParallelTestExecution {

  val ec2 = EC2.create(new ProfileCredentialsProvider("default"))
  // val keyPair = "aalekhin"
  val testKeyPair = "era7.mmanrique"
  val testRole = Some("era7-projects")

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


  ignore("trying to launch an instance") {
    val velvetSpecs = velvetCompat.instanceSpecs(
      instanceType = m1_small,
      testKeyPair,
      testRole
    )

    // println(velvetConf.specs.userData)

    val N = 1
    val instances = launchAndWait(ec2, velvetCompat.name, velvetSpecs, N)
    // instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }


  ignore("trying to download bio4j-lite on an instance") {
    val bio4jLiteSpecs = bio4jLiteCompat.instanceSpecs(
      instanceType = i2_xlarge,
      testKeyPair,
      testRole
    )

    println(bio4jLiteSpecs.userData)

    val N = 1
    val instances = launchAndWait(ec2, bio4jLiteCompat.name, bio4jLiteSpecs, N)
    // instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }


  ignore("testing samtools") {
    val samtoolsSpecs = samtoolsCompat.instanceSpecs(
      instanceType = m3_medium,
      keyPair = "era7.mmanrique",
      role = Some("era7-projects")
    )

    // println(samtoolsSpecs.userData)

    val N = 1
    val instances = launchAndWait(ec2, samtoolsCompat.name, samtoolsSpecs, N)
    // instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }


}
