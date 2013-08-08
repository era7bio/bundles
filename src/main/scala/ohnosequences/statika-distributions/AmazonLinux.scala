package ohnosequences.statika

case class CommonMetaData[S](s: S, val name: String) extends MetaDataOf[S] {
  val organization = meta.AmazonLinux.organization
  val artifact = meta.AmazonLinux.artifact
  val version = meta.AmazonLinux.version
  val statikaVersion = meta.AmazonLinux.statikaVersion
  val resolvers = meta.AmazonLinux.resolvers
  val privateResolvers = meta.AmazonLinux.privateResolvers
}

// Just a couple of testing bundles
case object Foo extends Bundle() {
  val metadata = CommonMetaData(this, "ohnosequences.statika.Foo")
}
case object Bar extends Bundle(Foo :: HNil) {
  val metadata = CommonMetaData(this, "ohnosequences.statika.Bar")
}

// Actual distribution
object AmazonLinux extends Distribution(
    AMI44939930,
    Git :: Bar :: Foo :: HNil,
    HNil
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
