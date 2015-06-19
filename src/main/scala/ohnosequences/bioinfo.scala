package ohnosequences.statika

import ohnosequences.statika._, bundles._
import ohnosequences.statika.aws._, api._, amazonLinuxAMIs._
import ohnosequences.awstools.regions.Region._

case object bioinfo {

  case object velvetCompat extends Compatible(
    amzn_ami_64bit(Ireland, PV)(1),
    DefaultVelvet,
    generated.metadata.StatikaBioinfo
  )

  case object bio4jLiteCompat extends Compatible(
    amzn_ami_64bit(Ireland, HVM)(1),
    Bio4jDistLite,
    generated.metadata.StatikaBioinfo
  )

}

// object apply extends App {
//   val results = bioinfo.velvetCompat.install(ohnosequences.statika.instructions.failFast);
//   results foreach println;
//   if (results.hasFailures) sys.error(results.toString)
// }
