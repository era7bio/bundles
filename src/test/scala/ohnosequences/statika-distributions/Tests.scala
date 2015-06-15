package ohnosequences.statika.tests

import ohnosequences.statika._, aws._, bundles._
import ohnosequences.statika.bioinfo._

// import cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.ec2.{Tag => Ec2Tag}
import java.io._
import org.scalatest._
import com.amazonaws.auth._, profile._
import ohnosequences.awstools.regions.Region._
import ohnosequences.awstools.ec2.InstanceType._

import era7.project._, era7.aws._, defs._

class ApplicationTest extends FunSuite with ParallelTestExecution {


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
    println(conf.specs.userData)
    ec2.runInstances(1, conf.specs)
  }

}

// val result = ec2.applyAndWait(bundle.name, specs, 1) match {
//   case List(inst) => inst.getTagValue("statika-status") == Some("success")
//   case _ => false
// }
// assert(result)
