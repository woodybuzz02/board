<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<input type="hidden" id="error" value="${error}" />

<div class= "container">

	<form action="/auth/signin" method="POST">
	  <div class="form-group">
	    <label for="username">Username:</label>
	    <input type="text" class="form-control" placeholder="Enter username" name="username" id="username" required="required">
	  </div>
	  <div class="form-group">
	    <label for="password">Password:</label>
	    <input type="password" class="form-control" placeholder="Enter password" name="password" id="password" required="required">
	  </div>
	  <button class="btn btn-primary">로그인</button>
	</form>
	
	<br>
	
	<!--로그인 오류시 메시지-->
	<span> 
		<c:if test="${error}">
			<p id="valid" class="alert-danger">${exception}</p>
		</c:if>
	</span>
	<!--로그인 오류시 메시지 end-->

</div>
<%@ include file="../layout/footer.jsp"%>