package ohnosequences.statika.tests

import ohnosequences.statika._
import distributions._

import cli.StatikaEC2._
import ohnosequences.awstools.ec2._
import java.io._

class ApplicationTest extends org.scalatest.FunSuite {

  test("Running instance with Git bundle test"){

    val userscript = AmazonLinux.userScript(Git, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

  test("Running instance with Boost bundle test"){

    val userscript = AmazonLinux.userScript(Boost, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

  test("Running instance with Bowtie bundle test"){

    val userscript = AmazonLinux.userScript(Bowtie, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

  test("Running instance with Tophat bundle test"){

    val userscript = AmazonLinux.userScript(Tophat, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

  test("Running instance with Cufflinks bundle test"){

    val userscript = AmazonLinux.userScript(Cufflinks, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

  test("Running instance with Python bundle test"){

    val userscript = AmazonLinux.userScript(Python, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

  test("Running instance with S3cmd bundle test"){

    val userscript = AmazonLinux.userScript(S3cmd, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

  test("Running instance with GCC bundle test"){

    val userscript = AmazonLinux.userScript(GCC, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

  test("Running instance with ZlibDevel bundle test"){

    val userscript = AmazonLinux.userScript(ZlibDevel, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

  test("Running instance with Velvet bundle test"){

    val userscript = AmazonLinux.userScript(Velvet, RoleCredentials)
    // println(userscript)

    // for running test you need to have this file in your project folder
    val ec2 = EC2.create(new File("AwsCredentials.properties"))

    val specs = InstanceSpecs(
        instanceType = InstanceType.InstanceType("c1.medium")
      , amiId = AmazonLinux.ami.id
      , keyName = "statika-launcher" 
      , deviceMapping = Map()
      , userData = userscript
      , instanceProfileARN = AmazonLinux.metadata.instanceProfileARN
      )

    // we asked for 1 instanse — we should get 1 instance
    ec2.runInstancesAndWait(1, specs).length == 1
  }

}
