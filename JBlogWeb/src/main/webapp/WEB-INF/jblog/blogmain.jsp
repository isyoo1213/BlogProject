<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 블로그 메인</title>
<Link rel="stylesheet" href="css/theme.css">
</head>
<body>
<form action="#" method="post">

	<table width="100%" height=5% border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height=100% colspan="10">&nbsp;</td>
		</tr>
	</table>
	
	<table width="100%" height=25% border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" height="50%" colspan="10" align="center">${selectedBlog.title}</td>
		</tr>
		<tr>
			<td width="100%" height="30%" colspan="10" align="center">${selectedBlog.tag}</td>
		</tr>
	</table>
	
	<table width="100%" height=60% border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td width="70%" class="tablelabel">포스트는 아직 구현을 못했습니다..ㅠㅠ..
		</td>
			
		<td width="100" class="tablelabel">
		
			<c:if test="${user == null }">
				<td width="10%" height="20" align="center"><a href="/loginView.do">로그인</a></td>
			</c:if>
			<c:if test="${user != null }">
				<td width="10%" height="20" align="center"><a href="/logout.do">로그아웃</a></td>
				<c:if test="${user.userId == selectedBlog.user_id }">
					<td width="10%" height="20" align="center"><a href="/adminBasicView.do?userId=${user.userId}">블로그 관리</a></td>
				</c:if>
			</c:if>
		</td>
	</tr>
	</table>

</form>
</body>
</html>