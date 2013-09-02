package ohnosequences.statika.distributions

import ohnosequences.statika._

case object AmazonLinux extends Distribution(
    ami.AMI44939930,
    members = 
      Velvet :: Cufflinks :: Tophat :: Bowtie :: Boost :: 
      ZlibDevel :: GCC :: S3cmd :: Python :: Git :: HNil
  ){

  val metadata = generated.metadata.AmazonLinux

  def install[D <: DistributionAux](distribution: D): InstallResults =
    success(metadata+" is installed")

}
