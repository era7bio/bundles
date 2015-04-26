package ohnosequences.statika

import ohnosequences.statika._, aws._
import ohnosequences.awstools.regions.Region._

case object bioinfo {

  case object ami extends amzn_ami_pv_64bit(Ireland)(1)

  case object velvetCompat extends AMICompatible(ami, DefaultVelvet, generated.metadata.StatikaBioinfo)

  //case object spadesCompat extends AMICompatible(ami, spades, generated.metadata.Bioinfo)*/

}
