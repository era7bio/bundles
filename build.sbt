name := "statika-bioinfo"
organization := "ohnosequences"
description := "statika distributions"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "ohnosequencesBundles" %% "velvet" % "0.5.0-SNAPSHOT"
  // "ohnosequences" %% "statika-cli" % "0.18.0-SNAPSHOT" % Test,
  // "org.scalatest" %% "scalatest" % "2.2.4" % Test
)

SbtStatikaPlugin.fatJarSettings


// Running test in parallel
// testOptions in Test += Tests.Argument("-PS")
// Showing time spent on each test
// testOptions in Test += Tests.Argument("-oD")
