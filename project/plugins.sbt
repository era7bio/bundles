resolvers ++= Seq(
  "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"
, Resolver.url("Era7 ivy releases", url("http://releases.era7.com.s3.amazonaws.com"))(Resolver.ivyStylePatterns)
// , Resolver.url("Era7 ivy snapshots", url("http://snapshots.era7.com.s3.amazonaws.com"))(Resolver.ivyStylePatterns)
)

addSbtPlugin( "ohnosequences" % "sbt-statika-ohnosequences" % "0.2.1" )
