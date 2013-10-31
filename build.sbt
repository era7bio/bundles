name := "statika-distributions"

description := "statika distributions"

homepage := Some(url("https://github.com/ohnosequences/statika-distributions"))

organization := "ohnosequences"

organizationHomepage := Some(url("http://ohnosequences.com"))

licenses := Seq("AGPLv3" -> url("http://www.gnu.org/licenses/agpl-3.0.txt"))


distributionSettings

publishMavenStyle := true

publishBucketSuffix := "era7.com"

bucketSuffix := "statika.ohnosequences.com"


libraryDependencies ++= Seq(
  "ohnosequences" % "statika-cli_2.10.3" % "0.16.1" % "test"
, "ohnosequences" %% "amazon-linux-ami" % "0.12.0"
///////////// members: ///////////////
, "ohnosequences" %% "yum" % "0.1.0"
, "ohnosequences" %% "s3cmd" % "0.3.0"
, "ohnosequences" %% "velvet" % "0.3.0"
, "ohnosequences" %% "bowtie" % "0.3.0"
, "ohnosequences" %% "tophat" % "0.3.0"
, "ohnosequences" %% "cufflinks" % "0.3.0"
)

awsStatikaVersion := "0.5.0"


// Running test in parallel
testOptions in Test += Tests.Argument("-P12")

// Showing time spent on each test
testOptions in Test += Tests.Argument("-oD")
