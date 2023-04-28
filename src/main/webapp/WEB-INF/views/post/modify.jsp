<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp"%>

	<div class="container">
		<form>
			<div class="form-group">
				<label for="title">Title:</label>
				<input value="${post.title}" type="text" class="form-control" placeholder="Enter title" name="title" id="title">
			</div>
			<div class="form-group">
				<label for="content">Content:</label>
				<textarea class="form-control" rows="5" placeholder="Enter content" name="content" id="content">${post.content}</textarea>	
			</div>
			<button id="btn-modify" class="btn btn-primary" onclick="modify(${post.id})" style="float: right; position: relative; left: -50%;">수정</button>
		</form>
	</div>	

<script src="/js/modify.js"></script>
<%@ include file="../layout/footer.jsp"%>