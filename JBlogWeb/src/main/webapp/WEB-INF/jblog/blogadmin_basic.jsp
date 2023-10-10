<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>블로그 관리</title>
<Link rel="stylesheet" href="css/theme.css">
</head>
<body>
<form action="updateBasic.do" method="post">

	<table width="100%" height=5% border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height=100% colspan="10">&nbsp;</td>
		</tr>
	</table>
	
	<table width="100%" height=25% border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" height="50%" colspan="10" align="center">${userBlog.title}</td>
		</tr>
		<tr>
			<td width="100%" height="30%" colspan="10" align="center">${userBlog.tag}</td>
		</tr>
	</table>
	
	<table width="100%" height=10% border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="70%" height="20" align="center"></td>
			<td width="15%" height="20" align="center"><a href="/logout.do">로그아웃</a></td>
			<td width="15%" height="20" align="center"><a href="/blogMain.do?blog_id=${user.userId}">내 블로그 메인</a></td>
		</tr>
	</table>
	
		<table width="100%" height=10% border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="15%" height="20" align="center"><a href="/adminBasicView.do?userId=${user.userId}">기본설정</a></td>
			<td width="15%" height="20" align="center"><a href="/insertCategoryView.do">카테고리</a></td>
			<td width="15%" height="20" align="center"><a href="/insertPostView.do">글작성</a></td>
			<td width="70%" height="20" align="center"></td>
		</tr>
	</table>
	
	<table width="100%" height=60% border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="orange" width="20%" align="center">제목</td>
			<td align="left">&nbsp;&nbsp; <input type="text" name="title" value="${userBlog.title}"/></td>
		</tr>
		<tr>
			<td bgcolor="orange" width="15%" align="center">블로그 태그</td>
			<td align="left">&nbsp;&nbsp; <input type="text" name="tag" value="${userBlog.tag}"/></td>
		</tr>
		<tr>
			<td bgcolor="orange" width="15%" align="center">메인 페이지 포스트</td>
			<td align="left">&nbsp;&nbsp; <input type="text" name="cnt_display_post" value="${userBlog.cnt_display_post}"/></td>
		</tr>
		<tr>
			<td bgcolor="orange" width="15%" align="center">로고 이미지</td>
			<td align="left">&nbsp;&nbsp; <img src="/images/j2eelogo.jpg"></td>
		</tr>
		<tr>
			<td height="40" colspan="10" align="center">
			<a href="/updateBasic.do"></a>
			<input type="submit" value="설정 저장"></td>
		</tr>
	</table>

</form>
</body>
</html>