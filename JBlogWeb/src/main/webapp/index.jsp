<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog 홈</title>
<Link rel="stylesheet" href="css/theme.css">
</head>
<body>
<center>
<form action="/searchBlog.do" method="post">
<table width="100%" height=320 border="0" cellpadding="0" cellspacing="0">
<tr><td height=40 colspan="10">&nbsp;</td></tr>
<tr>
	<td width="100%" height="120" colspan="10" align="center">
	<a href="/"><img src="images/logo.jpg" border="0"></a>
	</td>
</tr>
<tr>
	<td width="30%" height="30">&nbsp;</td>
	<td width="70%" colspan="2">
		<!-- 로그인 성공하지 않은 경우 session에 user가 null -->
		<c:if test="${user == null }">
		<a href="loginView.do"><b>로그인</b></a>&nbsp;&nbsp;
		</c:if>
		<!-- 로그인에 성공한 경우 -->
		<!-- 블로그 있는지, 없는지에 따라서 서로 베타적으로 구성할 것 -->
		<c:if test="${user != null }">
			<c:if test="${userBlog == null}">
			<a href="insertBlogView.do"><b>블로그등록</b></a>&nbsp;&nbsp;
			</c:if>
			<c:if test="${userBlog != null}">
			<a href="blogMain.do?blog_id=${user.userId}"><b>내 블로그로 가기</b></a>
			</c:if>
		</c:if>
	</td>
</tr>
<tr>
	<td width="40%" height="20">&nbsp;</td>
	<td width="60%"><input type="text" name="searchKeyword" size="65"><input type="submit" value="검색"></td>
</tr>
<tr>
	<td height="20" colspan="10" align="center" class="tdcontent">
		<input type="radio" name="searchCondition" value="TITLE"<c:if test="${search.searchCondition == 'TITLE'}">checked</c:if>>블로그 제목&nbsp;&nbsp; 
		<input type="radio"	name="searchCondition" value="TAG"<c:if test="${search.searchCondition == 'TAG'}">checked</c:if>>태그&nbsp;&nbsp; 
		<input type="radio" name="searchCondition" value="BLOGGER"<c:if test="${search.searchCondition == 'BLOGGER'}">checked</c:if>>블로거
	</td>
</tr>
</table>
</form>

<table width="720" height="100" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td width="320" class="tablelabel">블로그 제목</td>
	<td width="100" class="tablelabel">블로거</td>
	<td width="100" class="tablelabel">로고</td>
	<td width="100" class="tablelabel">상태</td>
	<td width="100" class="tablelabel">삭제</td>
</tr>

<c:forEach var="blog" items="${blogList}">
<tr>
	<td class="tablecontent" align="center">
		<a href="/blogMain.do?blog_id=${blog.blog_id}">${blog.title}</a>
	</td>
	<td class="tablecontent" align="center">${blog.user_name}</td>
	<td class="tablecontent" align="center"><img height="32" src="images/j2eelogo.jpg" border="0"></td>
	<td class="tablecontent" align="center">${blog.status}</td>
	<td class="tablecontent" align="center">
	<c:if test="${user.role == 'ADMIN' }">
		<a href="deleteBlog.do?blog_id=${blog.blog_id}"><img height="9"	src="images/delete.jpg" border="0"></a>
	</c:if>
	</td>
</tr>
</c:forEach>
<tr>
	<td height="40" colspan="10" align="center">
	<a href="logout.do">로그아웃</a></td>
</tr>

</table>
</center>
</body>
</html>