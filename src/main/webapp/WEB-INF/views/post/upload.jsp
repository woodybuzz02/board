<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp"%>

	<div class="container">
		<form action="/post" method="post">
			<div class="form-group">
				<label for="title">Title:</label>
				<input type="text" class="form-control" placeholder="Enter title" name="title" id="title">
			</div>
			<div class="form-group">
				<label for="content">Content:</label>
				<textarea class="form-control" rows="5" placeholder="Enter content" name="content" id="content"></textarea>	
			</div>
			<button id="btn-save" class="btn btn-primary">등록</button>
		</form>
	</div>	

<%@ include file="../layout/footer.jsp"%>