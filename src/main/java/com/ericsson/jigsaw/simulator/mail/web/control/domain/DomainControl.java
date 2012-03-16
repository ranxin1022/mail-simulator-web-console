package com.ericsson.jigsaw.simulator.mail.web.control.domain;

import com.ericsson.jigsaw.simulator.mail.web.model.Domain;


public interface DomainControl {
	
	public String addDomain(Domain domain) throws Exception;
	public String delDomain(Domain domain) throws Exception;
	public String[] listDomains() throws Exception; 
}
