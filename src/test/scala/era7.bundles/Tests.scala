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
  val testKeyPair = "aalekhin"
  // val testKeyPair = "era7.mmanrique"
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

  val N = 1

  ignore("testing velvet") {
    val velvetSpecs = awsCompats.velvet.instanceSpecs(
      instanceType = m3.medium,
      testKeyPair,
      testRole
    )

    // println(velvetSpecs.userData)
    val instances = launchAndWait(ec2, awsCompats.velvet.name, velvetSpecs, N)
    instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }

  ignore("testing samtools") {
    val samtoolsSpecs = awsCompats.samtools.instanceSpecs(
      instanceType = m3.medium,
      testKeyPair,
      testRole
    )

    val instances = launchAndWait(ec2, awsCompats.samtools.name, samtoolsSpecs, N)
    instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }

  ignore("testing bowtie2") {
    val bowtie2Specs = awsCompats.bowtie2.instanceSpecs(
      instanceType = m3.medium,
      testKeyPair,
      testRole
    )

    val instances = launchAndWait(ec2, awsCompats.bowtie2.name, bowtie2Specs, N)
    instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }

  ignore("testing tophat") {
    val tophatSpecs = awsCompats.tophat.instanceSpecs(
      instanceType = m3.medium,
      testKeyPair,
      testRole
    )

    val instances = launchAndWait(ec2, awsCompats.tophat.name, tophatSpecs, N)
    instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }

  ignore("testing cufflinks") {
    val cufflinksSpecs = awsCompats.cufflinks.instanceSpecs(
      instanceType = m3.medium,
      testKeyPair,
      testRole
    )

    val instances = launchAndWait(ec2, awsCompats.cufflinks.name, cufflinksSpecs, N)
    instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }

  test("testing blast") {
    val blastSpecs = awsCompats.blast.instanceSpecs(
      instanceType = m3.medium,
      testKeyPair,
      testRole
    )

    val instances = launchAndWait(ec2, awsCompats.blast.name, blastSpecs, N)
    instances.foreach{ _.terminate }
    assert{ instances.length == N }
  }

  // ignore("testing Oases") {
  //   val oasesSpecs = oasesCompat.instanceSpecs(
  //     instanceType = m3.medium,
  //     testKeyPair,
  //     testRole
  //   )
  //   val N = 1
  //   val instances = launchAndWait(ec2, oasesCompat.name, oasesSpecs, N)
  //   // instances.foreach{ _.terminate }
  //   assert{ instances.length == N }
  // }


  // ignore("trying to download bio4j-lite on an instance") {
  //   val bio4jLiteSpecs = bio4jLiteCompat.instanceSpecs(
  //     instanceType = i2_xlarge,
  //     testKeyPair,
  //     testRole
  //   )
  //
  //   println(bio4jLiteSpecs.userData)
  //
  //   val N = 1
  //   val instances = launchAndWait(ec2, bio4jLiteCompat.name, bio4jLiteSpecs, N)
  //   // instances.foreach{ _.terminate }
  //   assert{ instances.length == N }
  // }


  // ignore("testing prinseq") {
  //   val prinseqSpecs = prinseqCompat.instanceSpecs(
  //     instanceType = m3.medium,
  //     testKeyPair,
  //     testRole
  //   )
  //
  //   val N = 1
  //   val instances = launchAndWait(ec2, prinseqCompat.name, prinseqSpecs, N)
  //   // instances.foreach{ _.terminate }
  //   assert{ instances.length == N }
  // }

}
