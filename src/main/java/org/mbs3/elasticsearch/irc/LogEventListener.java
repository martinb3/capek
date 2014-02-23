package org.mbs3.elasticsearch.irc;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.Listener;

public class LogEventListener implements Listener<PircBotX> {

	@Override
	public void onEvent(Event<PircBotX> arg0) throws Exception {
		System.out.println(arg0.toString());
	}

}
