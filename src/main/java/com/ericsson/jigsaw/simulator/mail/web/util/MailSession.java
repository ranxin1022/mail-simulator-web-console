package com.ericsson.jigsaw.simulator.mail.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Session;


public class MailSession {
	
	public static Session createSession(String props_path) {
		Session session = null;
		Properties props = MailSession.getProperties(props_path);
		session = Session.getInstance(props);
		return session;
	}

	
	public static Properties getProperties(String props_path) {
		Properties props = null;
		InputStream is = null;
		try {
			is = MailSession.class.getClassLoader().getResourceAsStream(props_path);
			props = new Properties();
			props.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}

	public static Session getInstance(String props_path) {
		return Session.getInstance(getProperties(props_path));
	}
}
