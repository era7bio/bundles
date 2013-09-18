package ohnosequences.statika.distributions

import ohnosequences.statika._
import ohnosequences.statika.aws._

case object AmazonLinux extends AWSDistribution(
    ami.AMI44939930,
    members = 
      // Cufflinks :: Tophat :: Bowtie :: Boost :: S3cmd :: Python :: 
      Velvet :: ZlibDevel :: GCC :: Git :: HNil
  ){

  val metadata = generated.metadata.AmazonLinux

  override val instanceProfileARN = 
  	Some("arn:aws:iam::857948138625:instance-profile/statika-private-resolver")

  def install[D <: AnyDistribution](distribution: D): InstallResults =
    success(metadata+" is installed")

}
