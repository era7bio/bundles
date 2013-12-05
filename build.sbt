Statika.distributionProject

name := "statika-distributions"

organization := "ohnosequences"

description := "statika distributions"

publishMavenStyle := true

publishBucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  "ohnosequences" %% "amazon-linux-ami" % "0.14.1"
, "ohnosequences" %% "statika-cli" % "0.17.0" % "test"
///////////// members: ///////////////
, "ohnosequences" %% "yum" % "0.2.0"
, "ohnosequences" %% "s3cmd" % "0.4.0"
, "ohnosequences" %% "velvet" % "0.4.0"
, "ohnosequences" %% "bowtie" % "0.4.0"
, "ohnosequences" %% "tophat" % "0.4.0"
, "ohnosequences" %% "cufflinks" % "0.4.0"
)

dependencyOverrides += "org.scala-lang" % "scala-compiler" % scalaVersion.value

// Running test in parallel
// testOptions in Test += Tests.Argument("-PS")

generateDocs := {}

// Showing time spent on each test
testOptions in Test += Tests.Argument("-oD")
