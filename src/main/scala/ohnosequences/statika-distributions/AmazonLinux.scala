package ohnosequences.statika.distributions

import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.statika.bundles._

case object StatikaDistribution extends AWSDistribution(
    metadata = new generated.metadata.StatikaDistributions()
  , ami = ami.AMI149f7863
  , members = 
      Git :~: 
      S3cmd :~: 
      Velvet :~: 
      Bowtie :~: 
      Bowtie2 :~: 
      Tophat :~: 
      Cufflinks 
  ) {}
