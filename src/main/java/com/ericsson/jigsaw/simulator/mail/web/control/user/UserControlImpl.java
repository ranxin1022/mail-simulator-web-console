package com.ericsson.jigsaw.simulator.mail.web.control.user;


import java.util.ArrayList;
import java.util.List;
import org.apache.james.cli.probe.impl.JmxServerProbe;

import com.ericsson.jigsaw.simulator.mail.web.model.User;
import com.ericsson.jigsaw.simulator.mail.web.util.UtilTools;






public class UserControlImpl implements UserControl  {

	static JmxServerProbe probe = null;

	public String addUser(User user) throws Exception {
		String result="";
		probe = UtilTools.conn();
		user.setPassword("123456");
		probe.addUser(user.getName(),user.getPassword());
		result = "user: "+user.getName()+" added!";
		
		return result;
	}
	
	public String delUser(User user) throws Exception{
		String result = "";
		probe = UtilTools.conn();
		probe.removeUser(user.getName());
		result ="user:"+ user.getName()+" deleted!";
		
		return result;
	}
	
	public String[] listUsernames() throws Exception{
		probe = UtilTools.conn();
		String[] strUsers = probe.listUsers();
		return strUsers;
		
	}
	
	public List<User> listUsers() throws Exception{
		List<User> users = new ArrayList<User>();
		probe = UtilTools.conn();
		String[] strUsers = probe.listUsers();
		for(int i=0;i<strUsers.length;i++){
			User u = new User();
			u.setPassword("123456");
			u.setName(strUsers[i]);
			users.add(u);
		}
		return users;
		
	}
}
