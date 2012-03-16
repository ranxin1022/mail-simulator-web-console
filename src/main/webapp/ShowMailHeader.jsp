<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.ericsson.jigsaw.simulator.mail.web.control.mail.receive.*,com.ericsson.jigsaw.simulator.mail.web.util.*,com.ericsson.jigsaw.simulator.mail.web.model.*,java.util.*,javax.mail.internet.*,javax.mail.Message.RecipientType,javax.mail.*,com.ericsson.jigsaw.simulator.mail.web.*"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mail Simulator Web Console show mail header</title>
</head>
<body>
<%
	response.setContentType("text/html;charset=GBK");
	String userId = request.getParameter("id");
 	int subjectId = Integer.parseInt(request.getParameter("subjectId"));

	User u = new User(userId);
	ReceiveMail receiveMail = new ReceiveMail();
	Message message = receiveMail.getOneMail(u, subjectId);
	WebMail webMail = MimeMailUtil.parse(message);                   
	List<MimeBodyPart> attachments = webMail.getAttachments();
	
%>
<table>
	<tr>
		<td>Subject:</td>
		<td><%=message.getSubject() %></td>
	</tr>
	<tr>
		<td>From:</td>
		<td><%
			for(Address from:message.getFrom()){
				%>
			<span><%=from.toString() %></span>
		  <%}
			
			%>
		</td>
	</tr>
	<tr>
		<td>To:</td>
		<td><%
			for(Address to:message.getRecipients(RecipientType.TO)){
			%>
			<span><%=to.toString() %></span>
			<%} %>
		</td>
	</tr>
	<tr>
		<td>Cc:</td>
		
			<%if(message.getRecipients(RecipientType.CC)==null){%>
					<td><span>  </span></td>
			<% }else{
			%>
		
		<td><%
			for(Address cc:message.getRecipients(RecipientType.CC)){
				
			%>
			<span><%=cc.toString() %></span>
			<%}
			} %>
		</td>
	</tr>
	<tr>
		<td>Bcc:</td>
			<%if(message.getRecipients(RecipientType.BCC)==null){%>
				<td><span>  </span></td>
			<%}else{
			%>
		<td><%
			for(Address bcc:message.getRecipients(RecipientType.BCC)){
				if(bcc==null){%>
					<span></span>
				<% }
			%>
			<span><%=bcc.toString() %></span>
			<%}
		}%>
		</td>
	</tr>
	<tr>
		<td>sent date:</td>
		<td><%=UtilTools.convertDate(message.getSentDate()) %></td>
	</tr>
	
	<%
		if(attachments.size()!=0){
			for(int i=0; i<attachments.size();i++){  	
				MimeBodyPart bodypart = attachments.get(i);
				if(bodypart.getDisposition() != null) {
					
		            String filename = bodypart.getFileName();  
		            String downloadFileName = filename.replace(" ", "_");
		            
		            if(filename.startsWith("=?"))  
		            {  
		                filename = MimeUtility.decodeText(filename);  
		            }  
		           
		            %>
		            <tr>
		           	<td> <b>attachment<%=i %>:</b> </td> 
		           <td>
		           	<a href="DownloadAttachments?id=<%= userId%>&subjectId=<%=subjectId %>&bodynum=<%=i %>&filename=<%=downloadFileName %>"><%=filename %></a> 
		           	</td>
					</tr>
			<% 	}
			}
			
		}
		%>
	   
	
</table>

</body>
</html>