package ohnosequences.statika.distributions

import ohnosequences.statika._

object AmazonLinux extends Distribution(
    ami.AMI44939930,
    members = Cufflinks :: Tophat :: Bowtie :: Boost :: Git :: HNil
  ){

  val metadata = generated.metadata.AmazonLinux

  def install[D <: DistributionAux](distribution: D): InstallResults =
    success(metadata+" is installed")

}
