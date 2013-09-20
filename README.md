### Repository for Statika distributions

So far it contains a simple distribution, based on Amazon Linux AMI and the following bundles:

* [S3cmd](https://github.com/statika/s3cmd)
* [ZlibDevel](https://github.com/statika/zlib-devel)
* [GCC](https://github.com/statika/gcc)
* [Git](https://github.com/statika/git)
* [Python](https://github.com/statika/python)
* [Velvet](https://github.com/statika/velvet)
* [Cufflinks](https://github.com/statika/cufflinks)
* [Tophat](https://github.com/statika/tophat)
* [Bowtie](https://github.com/statika/bowtie)
* [Boost](https://github.com/statika/boost)

#### Usage

Add the following dependency to your sbt project

```scala
resolvers += "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"

libraryDependencies += "ohnosequences" %% "statika-distributions" % "0.5.0"
```
