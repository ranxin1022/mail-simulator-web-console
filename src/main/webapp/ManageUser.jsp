<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.ericsson.jigsaw.simulator.mail.web.control.domain.*,com.ericsson.jigsaw.simulator.mail.web.control.user.*,com.ericsson.jigsaw.simulator.mail.web.model.User"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mail Simulator Web Console  user management</title>
</head>
<link href="css/mail-simulator-style.css" rel="stylesheet" type="text/css" />
<style   type=text/css> 
.tdfont{font-size:18px} 
</style> 
<body>

<table class="management">
	<tr class="tr_first_row">
		<td class="td_first_col">
			<img alt='' src='picture/ericsson.png'/><br/><font size="3px">User Management</font>
		</td>
		<td  align="right" class="td_second_col">
		<br/>
			<div style="text-align:right;width:100%"><font  size="5px">Mail Simulator Web Console-user management</font></div>
			<br/><br/><br/><br/>
			<div align="right" ><a href="index.html"><img border=0 src="picture/home.png"/>home</a></div>&nbsp;&nbsp;
		</td>	
	</tr>
	<tr><td><br/><br/><br/><br/></td></tr>
	<tr height=80% >
		<td valign="top">
			<br><br>
			<input  type="text" id="username" >
			<select onclick="select(this.value);"  id="domain">
				<% 	DomainControlImpl domainControlImpl = new DomainControlImpl();
					String[] domains = domainControlImpl.listDomains();
					if(domains!=null){
						for(int i=0;i<domains.length;i++){
							%><option  value='@<%=domains[i] %>' >@<%=domains[i] %></option><%
						}
					}else{%>
						
						<option>add domain first</option>
						
					<%} %>
			</select>
			
			<br><br>
			<img src="picture/addpic.png" onclick="add(username.value)"/>
			
			<br><br>
		</td>
		
		<td valign="top" align="left">
			<table style="width:50%;height: 50%;" ><tr style="height: 10px;"><th>NO.</th><th>username</th><th>del</th></tr>
				<% 
					
						UserControlImpl userControlImpl = new UserControlImpl();
						String[] usernames = userControlImpl.listUsernames();
						if(usernames==null){%>
							<tr><td></td><td></td></tr>
						<%}else{
							for(int i=0;i<usernames.length;i++){
						%>
							<tr height="10px">
								<td class="tdfont"><%=(i+1) %></td><td class="tdfont"><%=usernames[i]%></td>
								<td ><input type="hidden" id="user<%=i %>" value='<%=usernames[i]%>'><img alt="" src="picture/delpic.png" onclick="del(user<%=i %>.value)"></td>
							</tr>
					<% 	 	}
						}
				%>
			</table>
		</td>
	</tr>
	<tr><td valign="bottom" colspan=2><iframe src="footer.html" frameborder=0 scrolling="no"></iframe></td></tr>
</table>

<script type="text/javascript">
	var domainname=document.getElementById("domain").options[0].value;
	
	function select(optionvalue){
		domainname = optionvalue;
	}
	
	function add(str){
		str = str+domainname;
		location.href="ManageUsers?addUsername="+str;
	}
	function del(str){
		location.href="ManageUsers?delUsername="+str;
	}
</script>
</body>
</html>