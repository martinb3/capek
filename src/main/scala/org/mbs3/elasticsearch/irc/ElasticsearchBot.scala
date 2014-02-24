package org.mbs3.elasticsearch.irc

import org.pircbotx._
import org.pircbotx.hooks._
import org.pircbotx.hooks.events._
import org.mbs3.elasticsearch.irc.commands.CommandListener

object ElasticsearchBot extends App {

	val config = new Configuration.Builder()
					.setName("martin_esbot")
					.setLogin("elasticsearch@bot")
					.setCapEnabled(true)
					.setShutdownHookEnabled(false)
					.setServerHostname("irc.freenode.net")
					.addAutoJoinChannel("#martin-elasticsearch-test")
					.addAutoJoinChannel("#gatorlug")
					.addAutoJoinChannel("#archlinux")
					.addListener(new LogEverythingListener)
					.addListener(new CommandListener)
					.addListener(new ElasticsearchListener("elasticsearch", "localhost", 9300))
					.buildConfiguration
	
	val bot = new PircBotX(config);
	bot.startBot();
}