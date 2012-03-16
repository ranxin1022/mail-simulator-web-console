package util;

import java.io.IOException;

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
	
}
