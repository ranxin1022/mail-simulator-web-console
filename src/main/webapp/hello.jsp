<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="javax.mail.Message,java.util.*,control.mail.receive.ReceiveMail,model.User,control.User.UserControlImpl"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv=Content-Type content="text/html; charset=us-ascii">
<STYLE>
BLOCKQUOTE {
	MARGIN-TOP: 0px; MARGIN-BOTTOM: 0px; MARGIN-LEFT: 2em
}
OL {
	MARGIN-TOP: 0px; MARGIN-BOTTOM: 0px
}
UL {
	MARGIN-TOP: 0px; MARGIN-BOTTOM: 0px
}
P {
	MARGIN-TOP: 0px; MARGIN-BOTTOM: 0px
}
BODY {
	FONT-SIZE: 10.5pt; COLOR: #000000; LINE-HEIGHT: 1.5; FONT-FAMILY: Segoe UI
}
</STYLE>

<META content="MSHTML 6.00.6002.18552" name=GENERATOR></HEAD>
<BODY style="MARGIN: 10px">
<% 
String errorInfo = (String)request.getParameter("errorInfo"); 
System.out.println(errorInfo);
%>

sorry,error! <%=errorInfo %></BODY></HTML>

