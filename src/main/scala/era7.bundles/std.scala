package era7.bundles

import ohnosequencesBundles.statika._

case object std {

	case object samtools extends Samtools("1.2")

	case object velvet extends Velvet(
	  categories = 2,
	  maxKmerLength = 99,
	  bigAssembly = false,
	  longSequences = false,
	  openMP = false,
		version = "1.2.10"
	)

	case object oases extends Oases(
		version = "0.2.09"
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

	case object blast extends Blast(
		version = "2.2.31"
	)

	case object prinseq extends Prinseq

}