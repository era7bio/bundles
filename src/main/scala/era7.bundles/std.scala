package era7.bundles

import ohnosequencesBundles.statika._

case object std {

	case object samtools extends Samtools("1.2")

	case object velvet extends Velvet(
	  categories = 2,
	  maxKmerLength = 99,
	  bigAssembly = false,
	  longSequences = false,
	  openMP = false
	)

	case object bowtie2 extends Bowtie2("2.2.6")

}
