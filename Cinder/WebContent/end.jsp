<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
function C(k){return(document.cookie.match('(^|; )'+k+'=([^;]*)')||0)[2]}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cinder</title>
</head>
<body>
<h1>Thanks for playing Cinder!</h1><br /><br />
<h3>You have earned: </h3><h2><script>document.write(C('points'));</script></h2><h3> points.</h3>
<br /><br />
<h3>Redeem Points</h3><br /><br />
<table>
<tr>
<td>
<div align="center"></div><a href="javascript:alert('Not enough points to redeem!');">XBox One -- 700 points</a></div><br />
<img src="http://compass.xboxlive.com/assets/05/cf/05cf6e58-f496-4ada-8eda-d5c33f2691b3.png?n=Footer_new_525x393.png" width="300" height="200">
</td>
<td>
<div align="center"></div><a href="javascript:alert('Not enough points to redeem!');">$200 Amazon Gift Card -- 500 points</a></div><br />
<img src="http://ecx.images-amazon.com/images/I/71qkxgOJDuL._SL1500_.jpg" width="300" height="200">
</td>
<td>
<div align="center"></div><a href="javascript:alert('Not enough points to redeem!');">iPad -- 800 points</a></div><br />
<img src="http://cdn.toucharcade.com/wp-content/uploads/2012/10/apple-ipad-mini-pr.jpeg" width="300" height="200">
</td>
<td>
<div align="center"></div><a href="javascript:alert('Not enough points to redeem!');">Oatmeal -- 45 points</a></div><br />
<img src="http://img2.timeinc.net/health/img/recipes/recipes/hl/chai-oatmeal-xl.jpg" width="300" height="200">
</td>
</tr>
</table>
<br />
<a href="index.jsp"><h1>Play again!</h1></a>
</body>
</html>