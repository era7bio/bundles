name := "statika-distributions"

organization := "ohnosequences"

description := "statika distributions"

publishMavenStyle := true

publishBucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  //"ohnosequences" %% "aws-statika" % "0.14.1",*/
  "ohnosequences" %% "statika-cli" % "0.18.0-SNAPSHOT" % Test
)

dependencyOverrides += "org.scala-lang" % "scala-compiler" % scalaVersion.value

// Running test in parallel
// testOptions in Test += Tests.Argument("-PS")

generateDocs := {}

// Showing time spent on each test
testOptions in Test += Tests.Argument("-oD")
