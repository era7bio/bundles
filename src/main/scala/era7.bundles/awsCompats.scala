package era7.bundles

import ohnosequences.statika._, bundles._
import ohnosequences.statika.aws._, api._, amazonLinuxAMIs._
import ohnosequences.awstools.regions.Region._

case object awsCompats {

  case object velvetCompat extends Compatible(
    amzn_ami_64bit(Ireland, Virtualization.PV)(1),
    std.velvet,
    generated.metadata.Bundles
  )

  case object samtoolsCompat extends Compatible(
    amzn_ami_64bit(Ireland, Virtualization.HVM)(1),
    std.samtools,
    generated.metadata.Bundles
  )

  case object bowtie2Compat extends Compatible(
    amzn_ami_64bit(Ireland, Virtualization.HVM)(1),
    std.bowtie2,
    generated.metadata.Bundles
  )

  case object tophatCompat extends Compatible(
    amzn_ami_64bit(Ireland, Virtualization.HVM)(1),
    std.tophat,
    generated.metadata.Bundles
  )

  case object cufflinksCompat extends Compatible(
    amzn_ami_64bit(Ireland, Virtualization.HVM)(1),
    std.cufflinks,
    generated.metadata.Bundles
  )

  case object prinseqCompat extends Compatible(
    amzn_ami_64bit(Ireland, Virtualization.HVM)(1),
    std.prinseq,
    generated.metadata.Bundles
  )
  
  case object blastCompat extends Compatible(
    amzn_ami_64bit(Ireland, Virtualization.HVM)(1),
    std.blast,
    generated.metadata.Bundles
  )

  case object bio4jLiteCompat extends Compatible(
    amzn_ami_64bit(Ireland, Virtualization.HVM)(1),
    Bio4jDistLite,
    generated.metadata.Bundles
  )
}
