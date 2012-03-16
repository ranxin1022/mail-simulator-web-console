<%@ page language="java" contentType="text/html; charset=GBK" import = "java.io.*,javax.mail.*,com.ericsson.jigsaw.simulator.mail.web.model.User,com.ericsson.jigsaw.simulator.mail.web.control.mail.receive.ReceiveMail"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mail Simulator Web Console show mails detail</title>
</head>
<link href="css/mail-simulator-style.css" rel="stylesheet" type="text/css" />
<body >
<%
	response.setContentType("text/html;charset=GBK");
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
		<tr ><td align=left colspan=5><font face="黑体" size=3>User:<%=id %></font></td><td align=right><a href="ShowUserMails?id=<%=id%>"><img border=0 src="picture/back.png" /></a></td></tr>
		<tr>
			<td height=200>
				<iframe width="100%" height="100%" frameborder=0 src='ShowMailHeader.jsp?id=<%=id%>&subjectId=<%=subjectId%>' scrolling="no"></iframe>
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