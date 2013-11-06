resolvers += "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"

resolvers += "Era7 maven snapshots" at "http://snapshots.era7.com.s3.amazonaws.com"

addSbtPlugin( "ohnosequences" % "sbt-statika" % "0.13.0-SNAPSHOT" )
