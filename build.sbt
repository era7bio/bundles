SbtStatikaPlugin.projectSettings

name := "statika-bioinfo"
organization := "ohnosequences"
description := "statika distributions"

publishBucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  //"ohnosequences" %% "aws-statika" % "0.14.1",*/
  "ohnosequences" %% "statika-cli" % "0.18.0-SNAPSHOT" % Test,
  "org.scalatest" %% "scalatest" % "2.2.4" % Test
)


// Running test in parallel
// testOptions in Test += Tests.Argument("-PS")
// Showing time spent on each test
// testOptions in Test += Tests.Argument("-oD")
