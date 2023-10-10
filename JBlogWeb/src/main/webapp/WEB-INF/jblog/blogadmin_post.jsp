<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>포스트 등록</title>
<Link rel="stylesheet" href="css/theme.css">
</head>
<body>
<form action="insertPost.do" method="post">

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
		
	<table width="100%" height="30%" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td bgcolor="orange" width="70" align="center">제목</td>
			<td align="left">&nbsp;&nbsp; 
				<input type="text" name="title"/>
				<select name="category_id">
					<option value="TITLE"<c:if test="${search.searchCondition == 'TITLE' }">selected</c:if>>제목</option>
					<option value="CONTENT"<c:if test="${search.searchCondition == 'CONTENT' }">selected</c:if>>내용</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td bgcolor="orange" width="70" align="center">내용</td>
			<td align="left">&nbsp;&nbsp; <input size="100" type="text" name="content"/></td>
		</tr>

		<tr>
			<td height="40" colspan="10" align="center">
			<input type="submit" value="포스트 등록"></td>
		</tr>
		
	</table>

</form>
</body>
</html>