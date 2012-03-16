package com.ericsson.jigsaw.simulator.mail.web.control.user;

import java.util.List;

import com.ericsson.jigsaw.simulator.mail.web.model.User;


public interface UserControl {

	public String addUser(User user) throws Exception;
	public String delUser(User user) throws Exception;
	public List<User> listUsers() throws Exception;
	public String[] listUsernames() throws Exception;
}
