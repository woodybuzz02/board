<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>    

<div class= "container">

	<form action="/auth/signup" method="post">
	  <div class="form-group">
	    <label for="username">Username:</label>
	    <input type="text" class="form-control" placeholder="Enter username" name="username" id="username" required="required" onchange="checkUsername()">
	    <!-- id 중복체크 -->
			<span class="username_ok" id="usernameOk">사용 가능한 유저네임입니다.</span>
			<span class="username_already" id="usernameAlready">누군가 이 유저네임을 사용하고 있어요.</span>
		<!-- id 중복체크 end -->
	  </div>
	  <div class="form-group">
	    <label for="email">Email address:</label>
	    <input type="email" class="form-control" placeholder="Enter email" name="email" id="email">
	  </div>
	  <div class="form-group">
	    <label for="password">Password:</label>
	    <input type="password" class="form-control" placeholder="Enter password" name="password" id="password" required="required">
	  </div>
	  <div class="form-group">
	    <label for="zipcode">zipcode:</label>
	    <input type="text" class="form-control" placeholder="Enter zipcode" name="zipcode" id="zipcode" />
	  </div>
	  <div class="form-group">
	    <label for="steetAdr">steetAdr:</label>
	    <input type="text" class="form-control" placeholder="Enter steetAdr" name="steetAdr" id="steetAdr"/>
	  </div>
	  <div class="form-group">
	    <label for="detailAdr">detailAdr:</label>
	    <input type="text" class="form-control" placeholder="Enter detailAdr" name="detailAdr" id="detailAdr"/>
	  </div>
	  <button class="btn btn-primary">회원가입</button>
	</form>

</div>

<script src="/js/signup.js"></script>
<%@ include file="../layout/footer.jsp"%>