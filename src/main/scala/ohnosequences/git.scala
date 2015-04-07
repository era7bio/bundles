package ohnosequences.statika

import ohnosequences.cosas.typeSets._
import ohnosequences.statika._, bundles._, instructions._, aws._
import sys.process._
import ohnosequences.awstools.regions.Region._

case object bioinfo {

  case object git extends Bundle(∅) {

    def install: Results =
      "yum install git -y" ->- success("Git is installed")

    // TODO: check url for format correctness
    def clone(repo: String, dir: String = ""): Results =
      Seq("git", "clone", repo) ++ (if (dir.isEmpty) Seq() else Seq(dir))
  }


  case object compatibles {

    implicit def gitCompat[E <: AmazonLinuxAMI](e: E): Compatible[E, git.type] =
      new Compatible(e, git, generated.metadata.StatikaBioinfo)
  }

}
