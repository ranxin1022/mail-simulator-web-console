<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="control.Domain.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<a href="index.jsp">index page</a><br><br>
<input  type="text" id="addDomainText" >&nbsp;&nbsp;<input type="button" id="addDomain" onclick="add(addDomainText.value);" value="addDomain">&nbsp;&nbsp;&nbsp;
<br><br>
<input  type="text" id="delDomainText" >&nbsp;&nbsp;<input type="button" id="delDomain" onclick="del(delDomainText.value)"  value="delDomain"><br><br>
<div id="result"></div>
<script type="text/javascript">
			
	
	function add(str){
		location.href="ListDomains?addDomain="+str;
	}
	function del(str){
		location.href="ListDomains?delDomain="+str;
	}
</script>
<table border='1'><tr><th>NO.</th><th>username</th></tr>
<% 
	DomainControlImpl userControl = new DomainControlImpl();
	String[] init_domains = userControl.listDomains();
	if(init_domains==null){
		DomainControlImpl domainControlImpl = new DomainControlImpl();
		String[] domains = domainControlImpl.listDomains();
		
		for(int i=0;i<domains.length;i++){
		%>
		<tr><td><%=(i+1)%></td><td><%=domains[i] %></td></tr>
	<% }
	}else{
		
		for(int i=0;i<init_domains.length;i++){ %>
			
			<tr><td><%=(i+1) %></td><td><%=init_domains[i] %></td></tr>
		<%}
		
	}
%>
</table>
<script type="text/javascript">
	
	function add(str){
		location.href="ListDomains?addDomain="+str;
	}
	function del(str){
		location.href="ListDomains?delDomain="+str;
	}
</script>
</body>
</html>