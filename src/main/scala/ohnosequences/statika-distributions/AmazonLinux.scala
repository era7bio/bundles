package ohnosequences.statika.distributions

import ohnosequences.statika._

object AmazonLinux extends Distribution(
    ami.AMI44939930,
    members = Git :: HNil
  ){

  // generated metadata
  val metadata = meta.AmazonLinux

  val defaultCreds = 
    Right("s3://private.snapshots.statika.ohnosequences.com/credentials/AwsCredentials.properties")

  // overrriding only to set the default credentials parameter
  override def userScript[B <: BundleAux : IsMember](
      bundle: B
    , credentials: Either[(String, String), String] = defaultCreds
    ): String = ami.userScript(this, bundle, credentials)

  val resourceBucket = ""
  def getResourcePath[B <: BundleAux](bundle: B, relativePath: Path): Path = ""

}
