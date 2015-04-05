package ohnosequences.statika

import ohnosequences.statika._
import sys.process._

object Git extends Bundle() {

  val metadata = generated.metadata.Git

  def install[D <: AnyDistribution](distribution: D): Results =
  	"yum install git -y" ->- success(metadata+" is installed")

  // TODO: check url for format correctness
  def clone(repo: String, dir: String = ""): Results =
  	Seq("git", "clone", repo) ++ (if (dir.isEmpty) Seq() else Seq(dir))

}
