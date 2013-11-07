package ohnosequences.statika.distributions

import ohnosequences.statika._
import ohnosequences.statika.aws._

case object StatikaDistribution extends AWSDistribution(
    ami = ami.AMI149f7863
  , members = Git :+: ∅ //:+: S3cmd :+: Velvet :+: Bowtie :+: Bowtie2 :+: Tophat :+: Cufflinks
  ) {
  val metadata = new generated.metadata.StatikaDistributions()
}
