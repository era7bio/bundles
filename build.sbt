Nice.scalaProject

name         := "bundles"
organization := "era7"
description  := "A standard set of bundles tested in AWS"

// the org name differs on github:
GithubRelease.repo := s"era7bio/${name.value}"

publishBucketSuffix := "era7.com"

scalaVersion := "2.11.7"

resolvers := Seq(
  "Era7 public maven releases"  at s3("releases.era7.com").toHttps(s3region.value.toString),
  "Era7 public maven snapshots" at s3("snapshots.era7.com").toHttps(s3region.value.toString)
) ++ resolvers.value

libraryDependencies ++= Seq(
  "ohnosequences" %% "statika"     % "2.0.0-M4",
  "ohnosequences" %% "aws-statika" % "2.0.0-M5",
  "org.scalatest" %% "scalatest"   % "2.2.5"     % Test,
  // bundles:
  "ohnosequences-bundles" %% "velvet"         % "0.7.0-SNAPSHOT",
  "ohnosequences-bundles" %% "samtools"       % "0.2.0-SNAPSHOT",
  "ohnosequences-bundles" %% "bowtie2"        % "0.2.0-SNAPSHOT",
  "ohnosequences-bundles" %% "tophat"         % "0.2.0-SNAPSHOT",
  "ohnosequences-bundles" %% "cufflinks"      % "0.1.0",
  "ohnosequences-bundles" %% "spades"         % "0.1.0",
  "ohnosequences-bundles" %% "blast"          % "0.2.0",
  "ohnosequences-bundles" %% "flash"          % "0.2.0-SNAPSHOT",
  "ohnosequences-bundles" %% "fastqc"         % "0.1.0",
  "ohnosequences-bundles" %% "cutadapt"       % "0.2.0-SNAPSHOT",
  "ohnosequences-bundles" %% "trimgalore"     % "0.3.0-SNAPSHOT",
  "ohnosequences-bundles" %% "cdevel"         % "0.4.0-SNAPSHOT"


)

// dependencyOverrides ++= Set()


// Showing time spent on each test
testOptions in Test += Tests.Argument("-oD")

fatArtifactSettings

enablePlugins(BuildInfoPlugin)
buildInfoPackage := "generated.metadata"
buildInfoObject  := name.value.split("""\W""").map(_.capitalize).mkString
buildInfoOptions := Seq(BuildInfoOption.Traits("ohnosequences.statika.bundles.AnyArtifactMetadata"))
buildInfoKeys    := Seq[BuildInfoKey](
  organization,
  version,
  "artifact" -> name.value.toLowerCase,
  "artifactUrl" -> fatArtifactUrl.value
)
