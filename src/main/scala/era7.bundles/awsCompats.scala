package era7.bundles

import ohnosequences.statika._, bundles._, aws._
import ohnosequences.awstools.ec2._
import ohnosequences.awstools.regions._

case object awsCompats {

  abstract class CompatibleFor[B <: AnyBundle](bundle: B) extends Compatible(
    amznAMIEnv(AmazonLinuxAMI(
      Region.Ireland,
      HVM,
      InstanceStore
    )),
    bundle,
    generated.metadata.Bundles
  )

  case object velvet     extends CompatibleFor(std.velvet)
  case object metaVelvet extends CompatibleFor(std.metaVelvet)
  case object samtools   extends CompatibleFor(std.samtools)
  case object bowtie2    extends CompatibleFor(std.bowtie2)
  case object tophat     extends CompatibleFor(std.tophat)
  case object cufflinks  extends CompatibleFor(std.cufflinks)
  case object blast      extends CompatibleFor(std.blast)
  case object flash      extends CompatibleFor(std.flash)
  case object spades     extends CompatibleFor(std.spades)
  case object fastqc     extends CompatibleFor(std.fastqc)
  case object cutadapt     extends CompatibleFor(std.cutadapt)


}
