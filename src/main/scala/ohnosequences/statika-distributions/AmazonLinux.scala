package ohnosequences.statika.distributions

import ohnosequences.statika._
import ohnosequences.statika.aws._

case object AmazonLinux extends AWSDistribution(
    metadata = generated.metadata.StatikaDistributions
  , ami = ami.AMI44939930
  , members = Git :+: S3cmd :+: Velvet
      // Cufflinks :+: Tophat :+: Bowtie :+: Boost
  ){

  def install[D <: AnyDistribution](d: D): InstallResults =
    success(name+" is installed")

}
