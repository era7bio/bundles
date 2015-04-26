launchCredentials := new com.amazonaws.auth.profile.ProfileCredentialsProvider("intercrossing")
instanceType := ohnosequences.awstools.ec2.InstanceType.m1_small
keyPair := "statika-test"
instanceRole := Some("god")
applyCompat := ohnosequences.statika.bioinfo.velvetCompat
