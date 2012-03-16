<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="javax.mail.*,com.ericsson.jigsaw.simulator.mail.web.util.*;"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mail Simulator Web Console list all mails</title>
</head>
<link href="css/show-all-mails-style.css" rel="stylesheet" type="text/css" />
<body style="width: 60%">
<%
	response.setContentType("text/html;charset=GBK");
	Message[] messages =(Message[])request.getAttribute("messages");
	String username = (String)request.getAttribute("username");
	String msg = (String)request.getAttribute("msg");
	
	if(msg!=null){%>
		<table>
		<tr>
			<td> <%=msg %></td>
		</tr>
		</table>
	<%}else{
		
	
%>

<table >

		<tr>
			<th align="left">No.</th><th align="left">From</th><th align="left">To</th><th align="left">Subject</th><th align="left">ReceiveTime</th><th align="left">del</th>
		</tr>
 		
		<%	if(messages.length==0){
				
			}else{
				for(int i=0;i<messages.length;i++){%>	
							
							<tr>
							<td id='no' align='left'><font size='4'><%=i %></font></td>
								
							<td align='left'><font size='4'><%=messages[i].getFrom()[0]%></font></td>
													
							<td align='left'><font size='4'><%= messages[i].getAllRecipients()[0]%></font></td>
													
							<td align='left'><font size='4'><a href='ShowMailDetail.jsp?id=<%=username %>&subjectId=<%=i %>'><%= messages[i].getSubject()%></a>
							</font></td>
													
							<td align='left'><font size='4'><%=UtilTools.convertDate(messages[i].getSentDate()) %></font></td>	
								
							<td align='left'><font size='4'><a href='ShowUserMails?id=<%=username %>&subjectId=<%=i %>'><img border=0 src="picture/delpic.png"/></a>
							</font></td>
							</tr>
			<%	}
			}
		}	%>
</table>
</body>
</html>