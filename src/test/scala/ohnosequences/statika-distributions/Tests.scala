package ohnosequences.statika.tests

import ohnosequences.statika._, aws._, bundles._
import ohnosequences.statika.bioinfo._

import cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.ec2.{Tag => Ec2Tag}
import java.io._
import org.scalatest._
import com.amazonaws.auth._, profile._
import ohnosequences.awstools.regions.Region._


class ApplicationTest extends FunSuite with ParallelTestExecution {

  // for running test you need to have this file in your project folder
  val ec2 = EC2.create(new ProfileCredentialsProvider("intercrossing"))

  def testBundle[E <: AnyAMI, B <: AnyBundle](compat: AMICompatible[E, B]) = {

    val ami = compat.environment
    val bundle = compat.bundle

    test(s"Apply ${bundle.name} bundle to an instance"){
      val specs = InstanceSpecs(
        instanceType = InstanceType.m1_small,
        amiId = ami.id,
        keyName = "statika-test",
        userData = ami.userScript(bundle)(_ => new AMICompatible(ami, bundle, compat.metadata)),
        instanceProfile = Some("god")
      )

      println(specs.userData)

      val result = ec2.applyAndWait(bundle.name, specs, 1) match {
        case List(inst) => inst.getTagValue("statika-status") == Some("success")
        case _ => false
      }
      assert(result)
    }
  }

  testBundle(velvetCompat)

}
