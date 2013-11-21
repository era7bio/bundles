### Repository for Statika distributions

So far it contains a simple distribution, based on Amazon Linux AMI and the following bundles:

* [Git](https://github.com/statika/yum)
* [S3cmd](https://github.com/statika/s3cmd)
* [Velvet](https://github.com/statika/velvet)
* [Cufflinks](https://github.com/statika/cufflinks)
* [Tophat](https://github.com/statika/tophat)
* [Bowtie & Bowtie2](https://github.com/statika/bowtie)

#### Usage

Add the following dependency to your sbt project

```scala
resolvers += "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"

libraryDependencies += "ohnosequences" %% "statika-distributions" % "0.8.0"
```
