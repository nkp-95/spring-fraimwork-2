<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보</title>
</head>
<body>
	<h2>학생 정보</h2>[<a href="/student/list">새로고침</a>]
	<hr />
	<table border="1">
		<tr><th>id</th><th>이름</th><th>대학</th><th>생일</th><th>이메일</th></tr>
		<c:forEach items="${studentList}" var="s">
			<tr><td>${s.id}</td><td>${s.username}</td><td>${s.univ}</td><td>${s.birth}</td><td>${s.email}</td></tr>
		</c:forEach>
	</table>
	<hr />
	<h2>학생 추가</h2>
	<hr />
	<form method="post" action="/student/add">
		<label for="username">이름</label>
		<input type="text" name="username" id="username"/><br>
		<label for="univ">대학</label>
		<input type="text" name="univ" id="univ"/><br>
		<label for="birth">생일</label>
		<input type="text" name="birth" id="birth"/><br>
		<label for="email">이메일</label>
		<input type="text" name="email" id="email"/><br>
		<button type="submit">등록</button>
	</form>
</body>
</html>