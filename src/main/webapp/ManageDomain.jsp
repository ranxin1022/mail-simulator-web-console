<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.ericsson.jigsaw.simulator.mail.web.control.domain.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mail Simulator Web Console domain management</title>
</head>
<link href="css/mail-simulator-style.css" rel="stylesheet" type="text/css" />
<style   type=text/css> 
.tdfont{font-size:18px} 
</style> 
<body>
<table class="management">
	<tr class="tr_first_row" >
		<td class="td_first_col">
			<img alt='' src='picture/ericsson.png'/><br/><font size="3px">Domain Management</font>
		</td>
		<td align="right" class="td_second_col">
		<br>
			<div style="text-align: right;width:100%"><font size="5px">Mail Simulator Web Console-domain management</font></div>
			<br/><br/><br/><br/>
			<div align="right" ><a href="index.html"><img border=0 src="picture/home.png"/>home</a></div>&nbsp;&nbsp;
		</td>
	</tr>
	<tr><td><br/><br/><br/><br/></td></tr>
	<tr height=80% >
		<td valign="top">
			<br><br>
			<input  type="text" id="domainText" ><br><br>
			<img src="picture/addpic.png" onclick="add(domainText.value)"/>
			
			<br><br>
		</td>
		
		<td valign="top" align="left">
			<table class="list" style="width:50%;height: 50%; "><tr><th>NO.</th><th>username</th><th>del</th></tr>
				<% 
						DomainControlImpl domainControlImpl = new DomainControlImpl();
						String[] domains = domainControlImpl.listDomains();
						if(domains==null){%>
							<tr><td></td><td></td></tr>
						<%}else{
							for(int i=0;i<domains.length;i++){
							%>
							<tr>
							<td class="tdfont"><%=(i+1)%></td><td class="tdfont"><%=domains[i] %></td>
							
							<td><input type="hidden" id="domain<%=i %>" value='<%=domains[i]%>'><img alt="" src="picture/delpic.png" onclick="del(domain<%=i %>.value)"></td>
							</tr>
						<% }
						
					}
				%>
		</table>
		</td>
	</tr>
	<tr><td valign="bottom" colspan=2><iframe src="footer.html" frameborder=0 scrolling="no"></iframe></td></tr>
</table>

<script type="text/javascript">
	function add(str){
		
		location.href="ManageDomains?addDomain="+str;
	}
	function del(str){
		location.href="ManageDomains?delDomain="+str;
	}
</script>
</body>
</html>