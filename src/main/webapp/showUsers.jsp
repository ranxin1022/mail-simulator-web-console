<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,model.User"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 	List<User> users =(ArrayList<User>)request.getAttribute("users");
	if(users==null){%>
		<table>
			<tr>
				<td>there is no user</td>
			</tr>
		</table>
	
  <%}else{
%>
	<table>
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