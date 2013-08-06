package ohnosequences.statika

import shapeless._
import General._
import MetaData._
import Distribution._

trait CommonMetaData[S] extends MetaDataOf[S] {
  val organization = meta.AmazonLinux.organization
  val artifact = meta.AmazonLinux.artifact
  val version = meta.AmazonLinux.version
  val statikaVersion = meta.AmazonLinux.statikaVersion
  val resolvers = meta.AmazonLinux.resolvers
  val privateResolvers = meta.AmazonLinux.privateResolvers
}

case object Foo extends Bundle() {
  val metadata = new CommonMetaData[this.type] {
    val name = "ohnosequences.statika.Foo"
  }
}


case object Bar extends Bundle(Foo :: HNil) {
  val metadata = new CommonMetaData[this.type] {
    val name = "ohnosequences.statika.Bar"
  }
}

object AmazonLinux extends Distribution(
    AMI44939930,
    Git :: Bar :: Foo :: HNil,
    HNil
  ){

  // generated metadata
  val metadata = meta.AmazonLinux

  val defaultCreds = Right("s3://private.snapshots.statika.ohnosequences.com/credentials/AwsCredentials.properties")

  override def userScript[B <: BundleAux : IsMember](
      bundle: B
    , credentials: Either[(String, String), String] = defaultCreds
    ): String =
      ami.userScript(this, bundle, credentials)

  val resourceBucket = ""
  def getResourcePath[B <: BundleAux](bundle: B, relativePath: Path): Path = ""
}
