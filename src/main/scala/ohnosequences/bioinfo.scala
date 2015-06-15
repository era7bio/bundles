package ohnosequences.statika

import ohnosequences.statika._, bundles._, aws._, amazonLinuxAMIs._
import ohnosequences.awstools.regions.Region._

case object bioinfo {

  case object velvetCompat extends Compatible(
    amzn_ami_pv_64bit(Ireland)(1),
    DefaultVelvet,
    generated.metadata.StatikaBioinfo
  )

}

// object apply extends App {
//   val results = bioinfo.velvetCompat.install(ohnosequences.statika.instructions.failFast);
//   results foreach println;
//   if (results.hasFailures) sys.error(results.toString)
// }
