Nice.scalaProject

name          := "bundles"
organization  := "era7"
description   := "A standard set of bundles tested in AWS"

publishBucketSuffix := "era7.com"

scalaVersion := "2.11.7"

resolvers := Seq(
  "Era7 public maven releases"  at s3("releases.era7.com").toHttps(s3region.value.toString),
  "Era7 public maven snapshots" at s3("snapshots.era7.com").toHttps(s3region.value.toString)
) ++ resolvers.value

libraryDependencies ++= Seq(
  "ohnosequences"          %% "statika"        % "2.0.0-new-instructions-SNAPSHOT",
  "ohnosequences"          %% "aws-statika"    % "2.0.0-new-instructions-SNAPSHOT",
  "ohnosequences-bundles"  %% "velvet"         % "0.5.0-SNAPSHOT",
  "ohnosequences-bundles"  %% "samtools"       % "0.1.0-SNAPSHOT",
  "ohnosequences-bundles"  %% "bowtie2"        % "0.1.0-SNAPSHOT",
  "ohnosequences-bundles"  %% "tophat"         % "0.1.0-SNAPSHOT",
  "ohnosequences-bundles"  %% "cufflinks"      % "0.1.0-SNAPSHOT",
  "ohnosequences-bundles"  %% "blast"          % "0.2.0-SNAPSHOT",
  // "ohnosequences-bundles"  %% "prinseq"        % "0.1.0-SNAPSHOT",
  // "ohnosequences-bundles"  %% "bio4j-dist"     % "0.1.0-SNAPSHOT",
  "org.scalatest"          %% "scalatest"      % "2.2.5"           % Test
)

dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "1.0.4"



fatArtifactSettings

// Running test in parallel
// testOptions in Test += Tests.Argument("-PS")
// Showing time spent on each test
// testOptions in Test += Tests.Argument("-oD")

val metadataObject = Def.setting { name.value.split("""\W""").map(_.capitalize).mkString }

// mvn: "[organisation]/[module]_[scalaVersion]/[revision]/[artifact]-[revision]-[classifier].[ext]"
// ivy: "[organisation]/[module]_[scalaVersion]/[revision]/[type]s/[artifact]-[classifier].[ext]"
val fatUrl = Def.setting {
  val isMvn = publishMavenStyle.value
  val scalaV = "_"+scalaBinaryVersion.value
  val module = moduleName.value + scalaV
  val artifact =
    (if (isMvn) "" else "jars/") +
    module +
    (if (isMvn) "-"+version.value else "") +
    "-fat" +
    ".jar"

  Seq(
    publishS3Resolver.value.url,
    organization.value,
    module,
    version.value,
    artifact
  ).mkString("/")
}

val generateMetadata = Def.task {

  val text = s"""
    |package generated.metadata
    |
    |import ohnosequences.statika.bundles._
    |
    |case object ${metadataObject.value} extends AnyArtifactMetadata {
    |  val organization: String = "${organization.value}"
    |  val artifact:     String = "${name.value.toLowerCase}"
    |  val version:      String = "${version.value}"
    |  val artifactUrl:  String = "${fatUrl.value}"
    |}
    |""".stripMargin

  val file = (sourceManaged in Compile).value / "statika" / "metadata.scala"
  IO.write(file, text)
  Seq(file)
}

sourceGenerators in Compile += generateMetadata.taskValue
