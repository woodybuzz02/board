<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>    

<div class= "container">

	<form action="/auth/signup" method="post">
	  <div class="form-group">
	    <label for="username">아이디:</label>
	    <input type="text" class="form-control" placeholder="아이디를 입력해주세요." name="username" id="username" required="required" onchange="checkUsername()">
	    <!-- id 중복체크 -->
			<span class="username_ok" id="usernameOk">사용 가능한 아이디입니다.</span>
			<span class="username_already" id="usernameAlready">누군가 이 아이디를 사용하고 있어요.</span>
		<!-- id 중복체크 end -->
	  </div>
	  
	  <div class="form-group">
	    <label for="password">비밀번호:</label>
	    <input type="password" class="form-control" placeholder="비밀번호를 입력해주세요." name="password" id="password" required="required">
	  </div>
	  
	  <div class="form-group">
	    <label for="email">이메일:</label>
	    <input type="email" class="form-control" placeholder="이메일을 입력해주세요." name="email" id="email" required="required">
	    <!-- <span>이메일 인증번호</span> -->
		<button class="btn btn-outline-primary" type="button" id="checkEmail" onclick="chkEmail()">인증</button>
	  </div>
	  
	  <div class="form-group">
		<div class="mail-check-box">
			<input class="form-control mail-check-input" placeholder="인증번호를 입력해주세요!" disabled="disabled">
			<span id="mail-check-warn"></span>
		</div>
	    <!-- 이메일 인증 end -->
	  </div>
	    
	  <div class="form-group">
		<input type="text" id="sample6_postcode" placeholder="우편번호" name="zipcode">
		<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
		<input type="text" id="sample6_address" placeholder="주소" name="steetAdr"><br>
		<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="detailAdr">
		<input type="text" id="sample6_extraAddress" placeholder="참고항목">
	  </div>
	  <button class="btn btn-primary">회원가입</button>
	</form>

</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/js/signup.js"></script>
<%@ include file="../layout/footer.jsp"%>