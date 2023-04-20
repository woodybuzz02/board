<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp"%>

	<input type="hidden" id="principalId" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id}" />
	<input type="hidden" id="postId" value="${post.id}" />

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
	<br>
	<br>
	<br>
	<br>
	
	<div class="container">
		<c:choose>
			<c:when test = "${empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}">
				<div class="card">
					<div class="card-body">
						<textarea class="form-control" rows="1" disabled placeholder="로그인후 이용해주세요."></textarea>
					</div>
					<div class="card-footer">
						<button class="btn btn-primary" disabled>등록</button>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="card">
					<div class="card-body">
						<textarea class="form-control" id="replyContent" rows="1"></textarea>
					</div>
					<div class="card-footer">
						<button class="btn btn-primary" onClick="addReply(${post.id})">등록</button>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	<br>
		<div class="card">
			<div class="card-header">댓글</div>
			<ul id="reply-box" class="list-group">
				<li id="reply--1" class="list-group-item d-flex justify-content-between">
					<div>작성내용</div>
					<div class="d-flex">
						<div class="font-italic">작성자 : 작성이름누구누구누구&nbsp;</div>
						<button class="badge">삭제</button>
					</div>
			 	</li>
			</ul>
		</div>
	</div>

<script src="/js/detail.js"></script>
<%@ include file="../layout/footer.jsp"%>