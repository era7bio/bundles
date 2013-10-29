package ohnosequences.statika.distributions

import ohnosequences.statika._
import ohnosequences.statika.aws._

case object AmazonLinux extends AWSDistribution(
    metadata = new generated.metadata.StatikaDistributions() {
      // because we want to use fat artifact:
      override def moduleID = super.moduleID + """ intransitive() classifier "fat" """
    }
  , ami = ami.AMI44939930
  , members = Git :+: S3cmd :+: Velvet :+: Bowtie :+: Bowtie2 :+: Tophat :+: Cufflinks
  ){

  def install[D <: AnyDistribution](d: D): InstallResults =
    success(name+" is installed")

}
