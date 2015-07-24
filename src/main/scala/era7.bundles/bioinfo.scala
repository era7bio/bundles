package ohnosequences.statika

import ohnosequences.statika._, bundles._
import ohnosequences.statika.aws._, api._, amazonLinuxAMIs._
import ohnosequences.awstools.regions.Region._
import ohnosequencesBundles.statika._
import ohnosequences.statika.standardVersion._

case object bioinfo {

  case object velvetCompat extends Compatible(
    amzn_ami_64bit(Ireland, PV)(1),
    velvet,
    generated.metadata.StatikaBioinfo
  )

  case object bio4jLiteCompat extends Compatible(
    amzn_ami_64bit(Ireland, HVM)(1),
    Bio4jDistLite,
    generated.metadata.StatikaBioinfo
  )

  case object samtoolsCompat extends Compatible(
    amzn_ami_64bit(Ireland, HVM)(1),
    samtools,
    generated.metadata.StatikaBioinfo
  )

}

// object apply extends App {
//   val results = bioinfo.velvetCompat.install(ohnosequences.statika.instructions.failFast);
//   results foreach println;
//   if (results.hasFailures) sys.error(results.toString)
// }
