Statika.distributionProject

name := "statika-distributions"

organization := "ohnosequences"

description := "statika distributions"

publishMavenStyle := true

publishBucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  "ohnosequences" % "statika-cli_2.10.3" % "0.16.1" % "test"
, "ohnosequences" %% "amazon-linux-ami" % "0.13.0-SNAPSHOT"
///////////// members: ///////////////
, "ohnosequences" %% "yum" % "0.2.0-SNAPSHOT"
// , "ohnosequences" %% "s3cmd" % "0.3.0"
// , "ohnosequences" %% "velvet" % "0.3.0"
// , "ohnosequences" %% "bowtie" % "0.3.0"
// , "ohnosequences" %% "tophat" % "0.3.0"
// , "ohnosequences" %% "cufflinks" % "0.3.0"
)


// Running test in parallel
// testOptions in Test += Tests.Argument("-P12")

// Showing time spent on each test
testOptions in Test += Tests.Argument("-oD")
