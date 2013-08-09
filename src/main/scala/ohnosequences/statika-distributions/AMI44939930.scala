package ohnosequences.statika.ami

import ohnosequences.statika._

object AMI44939930 extends AbstractAMI("ami-44939930", "2013.03"){

    private[statika] def userScript[
        D <: DistributionAux
      , B <: BundleAux : distribution.IsMember
      ](distribution: D
      , bundle: B
      , credentials: Either[(String, String), String]
      ): String = {

        val mb = bundle.metadata
        val md = distribution.metadata

    val initSetting =
"""#!/bin/sh

# redirecting output for logging
exec &> /root/log.txt

echo
echo " -- Setting environment -- "
echo
cd /root
export HOME="/root"
export PATH="/root/bin:$PATH"
env

echo
echo " -- Installing git -- "
echo
yum install git -y
"""

  val credentialsSet = credentials match {
      case Left((usr,psw)) => """
echo "accessKey = %s" >  /root/AwsCredentials.properties
echo "secretKey = %s" >> /root/AwsCredentials.properties
      """ format (usr, psw)
      case Right(bucket) => """
echo
echo " -- Installing s3cmd -- "
echo
git clone https://github.com/s3tools/s3cmd.git
cd s3cmd/
python setup.py install
cd /root

echo
echo " -- Creating empty s3cmd config, it will use IAM role -- "
echo "[default]" > /root/.s3cfg
cat /root/.s3cfg

echo
echo " -- Getting credentials -- "
echo
s3cmd --config /root/.s3cfg get """ + bucket
  }

  val sbtCsG8 = """
echo
echo " -- Installing sbt -- "
echo
curl http://scalasbt.artifactoryonline.com/scalasbt/sbt-native-packages/org/scala-sbt/sbt/0.12.3/sbt.rpm > sbt.rpm
yum install sbt.rpm -y 

echo
echo " -- Installing conscript -- "
echo
curl https://raw.github.com/n8han/conscript/master/setup.sh | sh

echo
echo " -- Installing giter8 -- "
echo
cs n8han/giter8
"""

  val template = """
echo
echo " -- Running g8 -- "
echo
g8 ohnosequences/statika-bundle.g8 -b feature/bundle-tester \
  '--name=BundleApplicator' """+
  "'--bundle_object="+mb.name+"' "+
  "'--bundle_artifact=\"" +mb.organization+"\" %% \""+mb.artifact+"\" % \""+mb.version+"\"' "+
  "'--distribution_object="+md.name+"' "+
  "'--distribution_artifact=\"" +md.organization+"\" %% \""+md.artifact+"\" % \""+md.version+"\"' "+
  "'--statika_version="+md.statikaVersion+"' "+
  "'--resolvers="+md.resolvers.mkString(", ")+"' "+
  "'--private_resolvers="+md.privateResolvers.mkString(", ")+"' "+
  """'--credentials=/root/AwsCredentials.properties'
cd bundleapplicator
"""

  val building = """
echo
echo " -- Building -- "
echo
sbt start-script
"""

  val running = """
echo
echo " -- Running -- "
echo
target/start
"""

    initSetting + credentialsSet + sbtCsG8 +
    template + building + running
  }

}
