package com.ericsson.jigsaw.simulator.mail.web.util;

import com.ericsson.jigsaw.simulator.mail.web.model.WebMail;

/**
 * Map content-ID or content-location to something to be replaced in HTML
 * 
 * 
 * @author exialia
 *
 */
public interface ContentInfoMapper {
	/**
	 * 
	 * Utilizing the info the WebMail to map contentId or ContentLocation to some URL for downloading media from server
	 * 
	 * @param contentIdOrContentLocation
	 * @param mail
	 * @return
	 */
	public String getContentIdMapper(String contentIdOrContentLocation, WebMail mail);
}
