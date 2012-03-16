package com.ericsson.jigsaw.simulator.mail.web.control.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.james.cli.probe.impl.JmxServerProbe;
import com.ericsson.jigsaw.simulator.mail.web.model.Domain;
import com.ericsson.jigsaw.simulator.mail.web.util.UtilTools;




public class DomainControlImpl implements DomainControl{

	private JmxServerProbe probe = null;

	public String addDomain(Domain domain) throws Exception {
		String result = null;
		probe = UtilTools.conn();
		probe.addDomain(domain.getName());
		result = "domain:"+domain.getName()+" added!";
		
		return result;
	}
	
	public String delDomain(Domain domain) throws Exception{
		String result = null;
		probe = UtilTools.conn();
		probe.removeDomain(domain.getName());
		result = domain.getName()+" deleted!";
		return result;
	}
	
	public String[] listDomains() throws Exception{
		
		
		probe = UtilTools.conn();
		String[] allDomains = probe.listDomains();
		/*List<String> tempDomains = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile("^(?:(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\b");
		for(int i=0;i<allDomains.length;i++){
			Matcher matcher = pattern.matcher(allDomains[i]);
			if(matcher.find()){
				
			}else{
				tempDomains.add(allDomains[i]);
			}
		}
		if(tempDomains.size()!=0){
			String[] result = new String[tempDomains.size()];
			for(int i=0;i<tempDomains.size();i++){
				result[i] = tempDomains.get(i);
			}
			return result;
		}else{
			return null;
		}
		*/
		return allDomains;
	}
	
	
}
