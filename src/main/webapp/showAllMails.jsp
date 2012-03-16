<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="javax.mail.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<%
	response.setContentType("text/html;charset=GBK");
	Message[] messages =(Message[])request.getAttribute("messages");
	String username = (String)request.getAttribute("username");
	String msg = (String)request.getAttribute("msg");
	
	if(msg!=null){%>
		<table>
		<tr>
			<td> there is no Mail!</td>
		</tr>
		</table>
	<%}else{
		
	
%>

<table >
		<tr>
			<th>No.</th><th>From</th><th>To</th><th>Subject</th><th>ReceiveTime</th><th>del</th>
		</tr>
 	
		<%for(int i=0;i<messages.length;i++){%>	
						
						<tr>
						<td id='no' align='center'><font size='4'><%=i %></font></td>
							
						<td align='center'><font size='4'><%=messages[i].getFrom()[0]%></font></td>
												
						<td align='center'><font size='4'><%= messages[i].getAllRecipients()[0]%></font></td>
												
						<td align='center'><font size='4'><a href='ShowMailDetail.jsp?id=<%=username %>&subjectId=<%=i %>'><%= messages[i].getSubject()%></a>
						</font></td>
												
						<td align='center'><font size='4'><%=messages[i].getSentDate() %></font></td>	
							
						<td align='center'><font size='4'><a href='ShowUserMails?id=<%=username %>&subjectId=<%=i %>'>del</a>
						</font></td>
						</tr>
				<%	}
		}	%>
			</table>
</body>
</html>