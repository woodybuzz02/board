<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp"%>

	<div class="container">
		<button class="btn btn-secondary" onclick="history.back()">글목록</button>
		<button id="btn-share" class="btn btn-secondary" onclick="sharePost(${post.id})">공유하기</button>		
		<c:choose>
			<c:when test = "${empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
			</c:when>
			<c:otherwise>
			<button id="btn-update" class="btn btn-secondary" onClick="location.href='${post.id}/modify'">수정</button>
			<button id="btn-delete" class="btn btn-secondary" onclick="deletePost(${post.id})">삭제</button>
			</c:otherwise>
		</c:choose>
		
		<br><br>
		<div>
			<h3>${post.title}</h3>
		</div>
		<hr />
		<div>
			<div>${post.content}</div>
		</div>
	</div>
	<br>

<script src="/js/detail.js"></script>
<%@ include file="../layout/footer.jsp"%>