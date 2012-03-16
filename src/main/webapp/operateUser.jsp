<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="control.User.*,model.User"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="index.jsp">index page</a><br><br>

<input  type="text" id="addUsername" >&nbsp;&nbsp;<input type="button" id="addUser" onclick="add(addUsername.value);" value="addUser">&nbsp;&nbsp;&nbsp;
<br><br>
<input  type="text" id="delUsername" >&nbsp;&nbsp;<input type="button" id="delUser" onclick="del(delUsername.value)"  value="deleteUser"><br><br>
		
<script type="text/javascript">
	
	function add(str){
		location.href="ListUsers?addUsername="+str;
	}
	function del(str){
		location.href="ListUsers?delUsername="+str;
	}
</script>
<table border='1'><tr><th>NO.</th><th>username</th></tr>
<% 
	UserControlImpl userControl = new UserControlImpl();
	String[] init_usernames = userControl.listUsernames();
	if(init_usernames==null){
		UserControlImpl userControlImpl = new UserControlImpl();
		String[] usernames = userControlImpl.listUsernames();
		for(int i=0;i<usernames.length;i++){
		%>
			<tr><td><%=(i+1) %></td><td><%=usernames[i]%></td></tr>
	<% }
		
	}else{
		
		for(int i=0;i<init_usernames.length;i++){
		%>	
			<tr><td><%=(i+1) %></td><td><%=init_usernames[i] %></td></tr>
	<%	}
		
	}
%>
</table>
</body>
</html>