package ohnosequences.statika

import shapeless._
import General._
import MetaData._
import Distribution._

case object Foo extends Bundle() {
  val metadata = new MetaDataOf[this.type] {
    val name = "ohnosequences.statika.Foo"
    val organization = "ohnosequences"
    val artifact = "foo"
    val version = "0.2.3"
    val resolvers = Seq()
  }
}


case object Bar extends Bundle(Foo :: HNil) {
  val metadata = new MetaDataOf[this.type] {
    val name = "ohnosequences.statika.Bar"
    val organization = "ohnosequences"
    val artifact = "bar"
    val version = "0.2.3"
    val resolvers = Seq()
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
