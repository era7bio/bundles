package ohnosequences.statika.tests

import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.statika.bundles._
import ohnosequences.statika.distributions._

import cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.ec2.{Tag => Ec2Tag}
import java.io._
import org.scalatest._

class ApplicationTest extends FunSuite with ParallelTestExecution {

  // for running test you need to have this file in your project folder
  val ec2 = EC2.create(new File("/Users/laughedelic/.ec2/Era7.credentials"))

  val dist = StatikaDistribution

  def testBundle[B <: AnyBundle : dist.isMember : dist.isInstallable](bundle: B) = {
    test("Apply "+bundle.name+" bundle to an instance"){
      val userscript = dist.userScript(bundle, RoleCredentials)
      // println(userscript)

      val specs = InstanceSpecs(
          instanceType = InstanceType.InstanceType("c1.medium")
        , amiId = dist.ami.id
        , keyName = "statika-launcher" 
        , deviceMapping = Map()
        , userData = userscript
        , instanceProfileARN = Some("arn:aws:iam::857948138625:instance-profile/statika-private-resolver")
        )

      val result = ec2.applyAndWait(bundle.name, specs, 1) match {
        case List(inst) => {
          val ok = inst.getTagValue("statika-status") == Some("success")
          if(ok) inst.terminate()
          ok
        }
        case _ => false
      }
      assert(result)
    }
  }

  testBundle(Git)
  testBundle(S3cmd)
  testBundle(Velvet)
  testBundle(Bowtie)
  testBundle(Bowtie2)
  testBundle(Tophat)
  testBundle(Cufflinks)

}
