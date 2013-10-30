package ohnosequences.statika.distributions

import ohnosequences.statika._
import ohnosequences.statika.aws._

case object AmazonLinux extends AWSDistribution(
    metadata = new generated.metadata.StatikaDistributions()
  , ami = ami.AMI149f7863
  , members = Git :+: S3cmd :+: Velvet :+: Bowtie :+: Bowtie2 :+: Tophat :+: Cufflinks
  ){

  def install[D <: AnyDistribution](d: D): InstallResults =
    success(name+" is installed")

}
