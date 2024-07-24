<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<title>뉴스 관리앱</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto" >
		<h2>뉴스 목록</h2>
		<hr />
		<ul class="list-group">
		  <c:forEach var="news" items="${newslist}" varStatus="status">
			  <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
			    <a href="/news/${news.aid}" class="text-decoration-none">
			    [${status.count}] ${news.title}, ${news.date}
			    </a>
			    <a href="/news/delete/${news.aid}"><span class="badge bg-secondary">&times;</span></a>
			  </li>
		  </c:forEach>
		</ul>
		<hr />
		<!-- 에러 출력부 -->
		<c:if test="${error != null}">
			<div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
			  <strong>에러 발생:</strong> ${error}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
		</c:if>
		<hr />
		<button type="button" class="btn btn-outline-info mb-3" data-bs-toggle="collapse"
		data-bs-target="#addForm" aria-expanded="false" aria-controls="addForm">뉴스 등록</button>
		
		<div class="collapse" id="addForm">
			<div class="card card-body">
				<form method="post" action="/news/add" enctype="multipart/form-data">
					<label for="title" class="form-label">제목</label>
  					<input type="text" class="form-control" id="title" name="title" />	
					<label for="file" class="form-label">이미지</label>
  					<input type="file" class="form-control" id="file" name="file" />
					<label for="content" class="form-label">기사내용</label>
  					<textarea class="form-control" name="content" id="content" cols="50" rows="5" ></textarea>
  					<button type="submit" class="btn btn-success mt-3">저장</button>  
  				</form>
			</div>
		</div>
		
	</div>
</body>
</html>