package org.mbs3.elasticsearch.irc

import org.pircbotx._
import org.pircbotx.hooks._
import org.pircbotx.hooks.events._
import org.pircbotx.hooks.types.GenericMessageEvent

class LogEverythingListener extends Listener[PircBotX] {
	override def onEvent(event: Event[PircBotX]): Unit = {
	  println(event.toString)
	}
}
