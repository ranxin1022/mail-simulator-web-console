<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.ericsson.jigsaw.simulator.mail.web.control.user.*,java.util.*,com.ericsson.jigsaw.simulator.mail.web.model.User"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mail Simulator Web Console list all users</title>
</head>
<link href="css/show-user-style.css" rel="stylesheet" type="text/css" />
<body >
<% 
	UserControlImpl userControl = new UserControlImpl();
	List<User> users =userControl.listUsers();
	if(users==null){%>
		<table>
			<tr>
				<td>there is no user</td>
			</tr>
		</table>
	
  <%}else{
%>
	<table >
		<tr><td align=center><img src="picture/user-sign.png"/><b>all users</b></td></tr>
	<%  for(User user:users){ %>
		<tr >
		
			<td>
				<font size='4'>
					<a name='aUsername' target='right' href="ShowUserMails?id=<%=user.getName()%>"><%=user.getName()%></a>
				</font>
			</td>
		</tr>
	<%} 
	}%>
	</table>
</body>
</html>