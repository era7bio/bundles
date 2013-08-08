package ohnosequences.statika.tests

class GitTest extends org.scalatest.FunSuite {

  import ohnosequences.statika._

  import gener8bundle.StatikaEC2._
  import ohnosequences.awstools.ec2._
  import java.io._

  test("Running instance with Git bundle test"){

    val userscript = AmazonLinux.userScript(Git)
    println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = Some("arn:aws:iam::857948138625:instance-profile/statika-tester")
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }
}
