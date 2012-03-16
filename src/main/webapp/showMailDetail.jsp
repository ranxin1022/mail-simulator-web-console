<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import = "java.io.*,javax.mail.*,model.User,control.mail.receive.ReceiveMail"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body >
<%

	String id = request.getParameter("id");
	int subjectId = 0; 
	if(id==null||request.getParameter("subjectId")==null){%>
		<table border=1  width="80%" >
		
		<tr>
			<td height=800>
			
			</td>
		</tr>
	</table>
<%  }else{
	
	subjectId = Integer.parseInt(request.getParameter("subjectId"));
%>
	
	<table width="80%" >
		<tr>
			<td height=200>
				<iframe width="100%" height="100%" frameborder=0 src='ShowHeader?id=<%=id%>&subjectId=<%=subjectId%>' scrolling="no"></iframe>
			</td>
			
		</tr>
		<tr>
			<td>
				<hr >
			</td>
		</tr>
		<tr>
			<td height=800>
	 			<iframe  width="100%" name ="content" height="100%" frameborder=0 src="ShowContent?id=<%=id%>&subjectId=<%=subjectId%>" scrolling="no"></iframe> 
			</td>
		</tr>
	</table>
	<%} %>
</body>
</html>