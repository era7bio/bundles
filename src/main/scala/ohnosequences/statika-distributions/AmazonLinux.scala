package ohnosequences.statika

import shapeless._
import General._
import MetaData._
import Distribution._

case object Foo extends Bundle() {

  implicit object MetaData extends MetaDataOfBundle[this.type] {
    val organization = "ohnosequences"
    val artifact = "foo"
    val version = "0.2.3"
  }
}

case object Bar extends Bundle(Foo :: HNil) {
  implicit object MetaData extends MetaDataOfBundle[this.type] {
    val organization = "ohnosequences"
    val artifact = "bar"
    val version = "0.2.3"
  }
}

object AmazonLinux extends Distribution(
    AMI44939930,
    Bar :: Foo :: HNil
  ){

  override val name = "AmazonLinux"

  val resourceBucket: Path = ""
  def getResourcePath[B <: BundleAux](bundle: B, relativePath: Path): Path = ""

  implicit object MetaData extends MetaDataOfDist[this.type] {
    val organization = "ohnosequences"
    val artifact = "statika-distributions"
    val version = "0.1.0"
  }
}
