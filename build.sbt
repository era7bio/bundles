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


// for publishing you need to set `s3credentials`
publishTo <<= (isSnapshot, s3credentials) { 
                (snapshot,   credentials) => 
  val prefix = if (snapshot) "snapshots" else "releases"
  credentials map s3resolver("Era7 "+prefix+" S3 bucket", "s3://"+prefix+".era7.com")
}

libraryDependencies ++= Seq(
    "ohnosequences" %% "git" % "0.4.0-SNAPSHOT"
  , "ohnosequences" % "gener8bundle_2.10.2" % "0.12.0-SNAPSHOT" % "test"
  )

// Metadata generation
bundlePackage := "ohnosequences.statika"

bundleObject := "AmazonLinux"
