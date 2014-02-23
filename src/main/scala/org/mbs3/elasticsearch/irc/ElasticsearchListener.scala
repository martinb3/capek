package org.mbs3.elasticsearch.irc

import scala.collection.JavaConverters._
import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.PircBotX
import org.pircbotx.hooks.events.MessageEvent
import org.elasticsearch.common.settings.ImmutableSettings
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.client.Client
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.common.xcontent.XContentFactory

class ElasticsearchListener(cluster:String, url:String, port:Int) extends ListenerAdapter[PircBotX] {

	val INDEX_NAME = "es-ircbot"
	val TYPE_NAME = "message"
  
	// constructors
    val s = ImmutableSettings.settingsBuilder().put("cluster.name", cluster).build()
	val client = new TransportClient(s)
	client.addTransportAddress(new InetSocketTransportAddress(url, port))
	
	
	checkOrBuildIndex(client)
  
	def checkOrBuildIndex(client: Client) {
    	val idx = client.admin().indices()
    	var prepAliasExist = idx.prepareAliasesExist(INDEX_NAME)
    	var ret = prepAliasExist.execute().actionGet();
    	
    	if(!ret.isExists()) {
    	  println("Index doesn't exist, create it.")
    	  
    	  val createIndexRequestBuilder = client.admin().indices().prepareCreate(INDEX_NAME);
    	  //createIndexRequestBuilder.execute().actionGet();
    	}
    }
	
	override def onMessage(event: MessageEvent[PircBotX]) {
	  //println("Inserting new message")
		//Add documents
		val indexRequestBuilder = client.prepareIndex(INDEX_NAME, TYPE_NAME);
		
		//build json object
		val jsonBuilder = XContentFactory.jsonBuilder();
		val contentBuilder = jsonBuilder.startObject().prettyPrint();
		contentBuilder.field("nick", event.getUser().getNick());
		contentBuilder.field("channel", event.getChannel().getName());
		contentBuilder.field("timestamp", event.getTimestamp());
		contentBuilder.field("message", event.getMessage());
		indexRequestBuilder.setSource(contentBuilder);
		val response = indexRequestBuilder.execute().actionGet();
	}
}