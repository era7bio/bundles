package era7.bundles

import ohnosequencesBundles.statika._

case object std {

	case object velvet extends Velvet(
	  categories = 2,
	  maxKmerLength = 99,
	  bigAssembly = false,
	  longSequences = false,
	  openMP = false,
		version = "1.2.10"
	)

	case object samtools extends Samtools(
		version = "1.2"
	)

	case object bowtie2 extends Bowtie2(
		version = "2.2.6",
		samtools = std.samtools
	)

	case object tophat extends Tophat(
		version = "2.1.0",
		bowtie2 = std.bowtie2
	)

	case object cufflinks extends Cufflinks(
		version = "2.2.1"
	)

	case object blast extends Blast(
		version = "2.2.31"
	)

	case object spades extends Spades(
		version = "3.6.1"
	)

	case object flash extends Flash(
		version = "1.2.11"
	)

	case object fastqc extends FastQC(
		version = "0.11.3"
	)

}
