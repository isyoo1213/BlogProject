<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>카테고리 등록</title>
<Link rel="stylesheet" href="css/theme.css">
</head>
<body>
<form action="insertCategory.do" method="post">

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
			<td width="15%" height="20" align="center"><a href="/blogMain.do?blog_id=${userBlog.blog_id}">내 블로그 메인</a></td>
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
	
	<table width="100%" height="20%" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td width="5%" class="tablelabel">번호</td>
			<td width="15%" class="tablelabel">카테고리명</td>
			<td width="10%" class="tablelabel">보이기유형</td>
			<td width="5%" class="tablelabel">포스트 수</td>
			<td width="40%" class="tablelabel">설명</td>
			<td width="10%" class="tablelabel">삭제</td>
		</tr>
		
		<c:forEach var="category" items="${categoryList}">
		<tr>
			<td class="tablecontent" align="center">${category.category_id}</a></td>
			<td class="tablecontent" align="center">
			<c:if test="${category.category_name == '미분류'}">
				${category.category_name}
			</c:if>
			<c:if test="${category.category_name != '미분류'}">
				<a href="/updateCategoryView.do?category_id=${category.category_id }">${category.category_name}</a>
			</c:if>
			</td>
			<td class="tablecontent" align="center">${category.display_type }</td>
			<td class="tablecontent" align="center">${category.cnt_display_post}</td>
			<td class="tablecontent" align="center">${category.description}</td>
			<td class="tablecontent" align="center">
				<a href="/deleteCategory.do?category_id=${category.category_id}"><img height="9"	src="images/delete.jpg" border="0"></a>
			</td>
		</tr>
		</c:forEach>
	</table>
		
	<table width="100%" height="30%" border="1" cellpadding="0" cellspacing="0">
		<tr>카테고리 추가</tr>
		<tr>
			<td bgcolor="orange" width="15%" align="center">카테고리 명</td>
			<td align="left">&nbsp;&nbsp; <input type="text" name="category_name"/></td>
		</tr>
		<tr>
			<td bgcolor="orange" width="15%" align="center">보이기 유형</td>
			<td align = "left">
				&nbsp;&nbsp; <input type="radio" name="display_type" value="TITLE" check="checked">제목&nbsp;&nbsp; 
				&nbsp;&nbsp; <input type="radio" name="display_type" value="CONTENT">제목 + 내용&nbsp;&nbsp; 
			</td>
		</tr>
		<tr>
			<td bgcolor="orange" width="15%" align="center">포스트 수</td>
			<td align="left">&nbsp;&nbsp; <input size="3" type="text" name="cnt_display_post"/>개</td>
		</tr>
		<tr>
			<td bgcolor="orange" width="15%" align="center">설명</td>
			<td align="left">&nbsp;&nbsp; <input size="100" type="text" name="description"/></td>
		</tr>
		<tr>
			<td height="40" colspan="10" align="center">
			<input type="submit" value="카테고리 추가"></td>
		</tr>
	</table>




</form>
</body>
</html>