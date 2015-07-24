package ohnosequences.statika

import ohnosequences.statika._, bundles._
import ohnosequences.statika.aws._, api._, amazonLinuxAMIs._
import ohnosequences.awstools.regions.Region._
import ohnosequencesBundles.statika._

case object standardVersion  {

	case object samtools extends Samtools("1.2")
	case object velvet extends Velvet(
	  categories = 2,
	  maxKmerLength = 99,
	  bigAssembly = false,
	  longSequences = false,
	  openMP = false
	)

}