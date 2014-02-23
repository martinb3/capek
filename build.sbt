name := "elasticsearch-bot"

libraryDependencies += "org.pircbotx" % "pircbotx" % "2.0.1"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.1"

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.6.1"

libraryDependencies += "org.elasticsearch" % "elasticsearch" % "0.90.11"

mainClass in (Compile, run) := Some("org.mbs3.elasticsearch.irc.ElasticsearchBot")

