<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="login" onsubmit="return validate()">
Username:
<input type="text" id="username" name="username">
Password:
<input type="password" id="password" name="password">
<input type="submit" value="login" name="login" >${error}
</form>
<script>
function validate()
{
	
	var name = document.getElementById("username");
	if(username.value=="")
		{
		alert("name field cannot be left empty");
		name.focus();
		return false;
		}
	var password = document.getElementById("password");
	if(password.value=="")
		{
		alert("name field cannot be left empty");
		password.focus();
		return false;
		}
	return true;
		
	
	
}
</script>
</body>
</html>