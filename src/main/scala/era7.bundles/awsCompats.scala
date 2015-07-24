package era7.bundles

import ohnosequences.statika._, bundles._
import ohnosequences.statika.aws._, api._, amazonLinuxAMIs._
import ohnosequences.awstools.regions.Region._

case object awsCompats {

  case object velvetCompat extends Compatible(
    amzn_ami_64bit(Ireland, PV)(1),
    std.velvet,
    generated.metadata.StatikaBioinfo
  )

  case object samtoolsCompat extends Compatible(
    amzn_ami_64bit(Ireland, HVM)(1),
    std.samtools,
    generated.metadata.StatikaBioinfo
  )

  case object bio4jLiteCompat extends Compatible(
    amzn_ami_64bit(Ireland, HVM)(1),
    Bio4jDistLite,
    generated.metadata.StatikaBioinfo
  )
}
