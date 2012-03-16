package control.Domain;

import model.Domain;


public interface DomainControl {
	
	public String addDomain(Domain domain) throws Exception;
	public String delDomain(Domain domain) throws Exception;
	public String[] listDomains() throws Exception; 
}
