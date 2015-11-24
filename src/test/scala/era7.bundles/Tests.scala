package era7.bundles.tests

import ohnosequences.statika._, bundles._, aws._
import era7.bundles._, std._, awsCompats._

import java.io._
import org.scalatest._
import scala.annotation.tailrec
import com.amazonaws.auth._, profile._
import ohnosequences.awstools.regions.Region._
import ohnosequences.awstools.ec2._, InstanceType._


class ApplicationTest extends FunSuite with ParallelTestExecution {

  val ec2 = EC2.create(new ProfileCredentialsProvider("default"))
  //val testKeyPair = "aalekhin"
   val testKeyPair = "era7.mmanrique"
  val testRole = Some("era7-projects")

  // TODO: change for spot requests:
  def launchAndWait(ec2: EC2, name: String, specs: AnyLaunchSpecs): List[ec2.Instance] = {
    ec2.runInstances(1, specs) flatMap { inst =>
      def checkStatus: String = inst.getTagValue("statika-status").getOrElse("...")

      val id = inst.getInstanceId()
      def printStatus(s: String) = println(name+" ("+id+"): "+s)

      inst.createTag(InstanceTag("Name", name))
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

  val instanceType = m3.medium

  def specs[C <: AnyLinuxAMICompatible](comp: C)(implicit
    checkS: instanceType.type SupportsStorageType C#Environment#AMI#Storage,
    checkV: instanceType.type SupportsVirtualization C#Environment#AMI#Virt
  ) = comp.instanceSpecs(
    instanceType = instanceType,
    testKeyPair,
    testRole
  )

  def testCompat(name: String, specs: AnyLaunchSpecs) = {
    test(s"testing ${name}") {
      // println(specs.userData)
      val instances = launchAndWait(ec2, name, specs)
      // if it was successful, we kill the instance immediately
      instances.foreach{ _.terminate }
      assert{ instances.length == 1 }
    }
  }

  val compats = Map(
    // "velvet" -> specs(awsCompats.velvet),
    // "samtools" -> specs(awsCompats.samtools),
    // "bowtie2" -> specs(awsCompats.bowtie2),
    // "tophat" -> specs(awsCompats.tophat),
    // "cufflinks" -> specs(awsCompats.cufflinks)
    // "blast" -> specs(awsCompats.blast),
    // "flash" -> specs(awsCompats.flash)
    // "spades" -> specs(awsCompats.spades),
    // "fastqc" -> specs(awsCompats.fastqc)
    // "metaVelvet" -> specs(awsCompats.metaVelvet)
    "cutadapt" -> specs(awsCompats.cutadapt)

  )
   compats.foreach{ case (name, specs) => testCompat(name, specs) }
/*
  // spot request for Marina:
  ec2.ec2.requestSpotInstances(
    new com.amazonaws.services.ec2.model.RequestSpotInstancesRequest()
      .withInstanceCount(2)
      .withSpotPrice("0.6")
      .withAvailabilityZoneGroup("eu-west-1b")
      .withLaunchSpecification(
         awsCompats.cufflinks.instanceSpecs(
           instanceType = c3.x8large,
           "era7.mmanrique",
           testRole
         ).toAWS
      )
  )
*/

}
