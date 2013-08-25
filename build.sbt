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


// no private plugins so far
privateResolvers := Seq()

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
    "ohnosequences" % "statika-cli_2.10.2" % "0.15.1" % "test"
  , "ohnosequences" %% "ami-44939930" % "0.8.1"
  , "ohnosequences" %% "git" % "0.6.0"
  , "ohnosequences" %% "boost" % "0.1.0"
  , "ohnosequences" %% "bowtie" % "0.1.0"
  , "ohnosequences" %% "tophat" % "0.1.0"
  , "ohnosequences" %% "cufflinks" % "0.1.0"
  )

bundleObjects := Seq("ohnosequences.statika.distributions.AmazonLinux")
