package com.ericsson.jigsaw.simulator.mail.web.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ericsson.jigsaw.simulator.mail.web.model.WebMail;

/**
 * A simple size limited cache for WebMail
 * @author exialia
 *
 */
public class SimpleWebmailCache {
	private Map<String, WebMail> cache ;
	private int hardLimit = 10;
	
	public SimpleWebmailCache(int limit) {
		this.hardLimit = limit;
		cache = new LinkedHashMap<String, WebMail>() {
			private static final long serialVersionUID = 4974486455246634608L;

			@Override
	        protected boolean removeEldestEntry(Map.Entry<String, WebMail> eldest) {
					/**
					 * The cache will remove eldest objects from the cache.
					 * @param msgId
					 * @param mail
					 */
	                return size() > hardLimit;
	        }

		};

	}
	
	public WebMail getMailByMsgId(String msgId) {
		synchronized (cache) {
			return cache.get(msgId);
		}
	}

	public void addMailToCache(String msgId, WebMail mail) {
		synchronized (cache) {
			cache.put(msgId, mail);
		}
	}
	
	private static SimpleWebmailCache instance;
	
	public synchronized static SimpleWebmailCache getInstance() {
		if(instance == null) {
			instance = new SimpleWebmailCache(50);
		}
		
		return instance;
	}
}
