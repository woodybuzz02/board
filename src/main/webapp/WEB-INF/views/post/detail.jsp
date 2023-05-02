<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp"%>

	<input type="hidden" id="principalId" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.id}" />
	<input type="hidden" id="username" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" />
	<input type="hidden" id="postId" value="${post.id}" />
	
	<div class="container" name="replyContainer">
		<div style="float: right;">
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
		</div>
		<br><br>
		
		<c:choose>
    		<c:when test="${post.status == 1}">
    		<div>
				<h3>블라인드 처리된 글입니다.</h3>
			</div>
			<hr />
			<div>
				<P>게시글이 비속어 사용으로 인해 블라인드 처리되었습니다.</P>
				<P>많은 회원님들께 불편함을 주는 내용의 글이나 각 게시판 성격에 맞지 않는 글은 등록시 주의해 주시기 바랍니다.</P>
			</div>
    		</c:when>
      		<c:otherwise>
      			<div>
					<h3>${post.title}</h3>
				</div>
				<hr />
				<div>
					<div>${post.content}</div>
				</div>
      		</c:otherwise>
        </c:choose>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<div class="container" name="replyContainer">
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
						<textarea class="form-control" id="replyContent" rows="4" cols="70"></textarea>
					</div>
					<div class="card-footer">
						<button class="btn btn-primary" onClick="addReply(${post.id},0,0)" style="float: right;">등록</button>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	<br>
		<div class="card" id="readReply">
			<div class="card-header">댓글</div>
		</div>
		<div id="replyBtn-more" style="text-align:center;">
		<br>
		<button class="btn btn-primary" onClick="more()">더보기</button>
		</div>
	</div>

<script src="/js/detail.js"></script>
<%@ include file="../layout/footer.jsp"%>