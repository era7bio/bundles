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

	case object bowtie2 extends Bowtie2(
		version = "2.2.6",
		samtools = std.samtools
	)

	case object tophat extends Tophat(
		version= "2.1.0",
		bowtie2 = std.bowtie2
	)

	case object cufflinks extends Cufflinks(
		version = "2.2.1"
	)

	case object prinseq extends Prinseq

}
