package control.Domain;


import model.Domain;
import org.apache.james.cli.probe.impl.JmxServerProbe;
import util.UtilTools;



public class DomainControlImpl implements DomainControl{

	private JmxServerProbe probe = null;

	public String addDomain(Domain domain) throws Exception {
		String result = "";
		probe = UtilTools.conn();
		probe.addDomain(domain.getName());
		result = "domain:"+domain.getName()+" added!";
		
		return result;
	}
	
	public String delDomain(Domain domain) throws Exception{
		String result = "";
		probe = UtilTools.conn();
		probe.removeDomain(domain.getName());
		result = domain.getName()+" deleted!";
		return result;
	}
	
	public String[] listDomains() throws Exception{
		
			probe = UtilTools.conn();
			return probe.listDomains();
		
	}

}
