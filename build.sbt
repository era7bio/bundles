import sbtrelease._
import ReleaseStateTransformations._
import ReleasePlugin._
import ReleaseKeys._


name := "statika-distributions"

description := "statika distributions"

homepage := Some(url("https://github.com/ohnosequences/statika-distributions"))

organization := "ohnosequences"

organizationHomepage := Some(url("http://ohnosequences.com"))

licenses += "AGPLv3" -> url("http://www.gnu.org/licenses/agpl-3.0.txt")


scalaVersion := "2.10.2"

statikaVersion := "0.12.2"


s3resolvers := Seq()

publishMavenStyle := false

// for publishing you need to set `s3credentials`
publishTo <<= (isSnapshot, s3credentials) { 
                (snapshot,   credentials) => 
  val prefix = if (snapshot) "snapshots" else "releases"
  credentials map S3Resolver(
      "Era7 "+prefix+" S3 bucket"
    , "s3://"+prefix+".era7.com"
    , Resolver.ivyStylePatterns
    ).toSbtResolver
}

libraryDependencies ++= Seq(
    "ohnosequences" %% "git" % "0.5.0"
  , "ohnosequences" % "gener8bundle_2.10.2" % "0.12.0" % "test"
  )

// Metadata generation
bundlePackage := "ohnosequences.statika.distributions"

bundleObject := "AmazonLinux"
