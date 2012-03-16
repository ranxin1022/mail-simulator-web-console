package com.ericsson.jigsaw.simulator.mail.web.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.mail.Address;

import org.apache.james.cli.probe.impl.JmxServerProbe;
public   class UtilTools {

	static JmxServerProbe probe;
	static String host = "localhost";
	
	public static JmxServerProbe conn() throws IOException, InterruptedException{
		probe = new JmxServerProbe(host);
		return probe;
	}

	public static String getHost() {
		return host;
	}
	
	public static String convertDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		return year+"-"+month+"-"+dayOfMonth;
	}
	
	public static String[] addressArrayConvertStringArray(Address[] addresses){
		String[] results = {};
		for(int i=0;i<addresses.length;i++){
			results[i] = addresses[i].toString();
		}
		return results;
	}
	
}
