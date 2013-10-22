package ohnosequences.statika.distributions

import ohnosequences.statika._
import ohnosequences.statika.aws._

case object AmazonLinux extends AWSDistribution(
    metadata = generated.metadata.StatikaDistributions
  , ami = ami.AMI44939930
  , members = Git :+: Python :+: S3cmd
      // Cufflinks :+: Tophat :+: Bowtie :+: 
      // Boost :+: Velvet :+: ZlibDevel :+: GCC
  ){

  def install[D <: AnyDistribution](distribution: D): InstallResults =
    success(name+" is installed")

}
