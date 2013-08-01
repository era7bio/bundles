package ohnosequences.statika

import shapeless._
import General._
import MetaData._
import Distribution._

case object Foo extends Bundle() {

  implicit object MetaData extends MetaDataOf[this.type] {
    val name = "Foo"
    val organization = "ohnosequences"
    val artifact = "foo"
    val version = "0.2.3"
    val resolvers = "FooResolvers"
  }
}

case object Bar extends Bundle(Foo :: HNil) {
  implicit object MetaData extends MetaDataOf[this.type] {
    val name = "Bar"
    val organization = "ohnosequences"
    val artifact = "bar"
    val version = "0.2.3"
    val resolvers = "BarResolvers"
  }
}

object AmazonLinux extends Distribution(
    AMI44939930,
    Bar :: Foo :: HNil
  ){

  val resourceBucket: Path = ""
  def getResourcePath[B <: BundleAux](bundle: B, relativePath: Path): Path = ""

  implicit object MetaData extends MetaDataOf[this.type] {
    val name = "AmazonLinux"
    val organization = "ohnosequences"
    val artifact = "statika-distributions"
    val version = "0.1.0"
    val resolvers = "AmazonLinuxResolvers"
  }
}
