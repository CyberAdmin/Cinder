<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
h1 {
	font-family:sans-serif;
}

h2 {
	font-family:sans-serif;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cinder</title>
</head>
<body>
<script type="text/javascript">
function defaultCookie(){
	document.cookie="index=0";

}
</script>
<div align="center">
<h1>Welcome to Cinder!</h1>
<br /><br />
<h2>Please input the following items to begin playing.</h2>
<div align="left">
<form method="POST" action="begin">
Gender: <select id='gender' name='gender'><option name='female'>Female</option><option name='male'>Male</option></select><br />
Age: <input type="text" id='age' name='age'><br /><br />
<input type="submit" value="Begin" onclick="defaultCookie();">
</form>
</div>
</div>
</body>
</html>