<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  import="javax.mail.Message,java.util.*,control.mail.receive.ReceiveMail,model.User,control.User.UserControlImpl"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mail simulator-index page</title>
</head>
<style type="text/css">
	table{
		border:1px;
		color:black;
		width:100%
	}
	
</style>
<body>

<table border=1>
	<tr>
		<td width=200 align='center'>
			<img alt='' src='picture/ericsson.png'>
		</td>
		
		<td align='right'><br><br><br><br><a href='operateUser.jsp' ><font size='5'>User Management</font></a> &nbsp;  &nbsp;<a href='operateDomain.jsp'><font size='5'>Domain Management</font></a>&nbsp;&nbsp;
		</td>
	</tr>
	<tr  height="600">
		<td align="center" width=200>
			<iframe width="100%" height="100%" frameborder=0 src='ShowUsers' scrolling="auto"></iframe>			
		</td>
		<td align="right">
			<iframe width="100%" name="right" height="100%" frameborder=0 src='ShowUserMails' scrolling="auto"></iframe>	
		</td>
	</tr>
	
	</table>
</body>
</html>