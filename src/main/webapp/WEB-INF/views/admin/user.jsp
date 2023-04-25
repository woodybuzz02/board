<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<%@ include file="../layout/adminHeader.jsp"%>

<div class="container">
	<h2>계정 관리</h2>
	<br>
	<br>    
	  <table class="table" style="table-layout: fixed">
	    <thead>
	      <tr>
	      	<th width="10%">번호</th>
	        <th width="15%">아이디</th>
	        <th width="20%">이메일</th>
	        <th width="15%">권한</th>
	        <th width="15%">가입일자</th>
	        <th width="10%">관리</th>
	      </tr>
	    </thead>
	    <tbody>
	    <c:choose>
	        <c:when test="${fn:length(list) > 0 }">
	        	<c:set var="num" value="${pageMaker.postNum }"/>
	            <c:forEach items="${list }" var="uList">
	              <tr>
	              	<td>${num }</td>
	                <td>${uList.username}</td>
	                <td>${uList.email }</td>
	                <td>${uList.role}</td>
	                <td>${uList.created_at}</td>
	                <td><button type="button" class="btn btn-primary" onclick="userInfoModalOpen(${uList.id})">설정</button></td>
	              </tr>
	            <c:set var="num" value="${num-1 }"></c:set>
	            </c:forEach>
	        </c:when>
	        <c:otherwise>
	            <tr>
	                <td colspan="5">조회된 결과가 없습니다.</td>
	            </tr>
	        </c:otherwise>
	    </c:choose>
	  </table>
	
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		  <c:if test="${pageMaker.prev }">
		    <li class="page-item">
		      <a class="page-link" href='<c:url value="/post/main?page=${pageMaker.startPage-1 }"/>' aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		        <span class="sr-only">Previous</span>
		      </a>
		    </li>
		   </c:if>
		   <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
		    <li class="page-item"><a class="page-link" href='<c:url value="/post/main?page=${pageNum }"/>'>${pageNum }</a></li>
		   </c:forEach>
		   <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
		    <li class="page-item">
		      <a class="page-link" href='<c:url value="/post/main?page=${pageMaker.endPage+1 }"/>' aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		        <span class="sr-only">Next</span>
		      </a>
		    </li>
		   </c:if>
		  </ul>
		</nav>
</div>

<!-- 회원 설정 모달 -->
<div class="modal-user" id="modal-user" style="display: none; justify-content: center;">
	<div class="user">
		<div class="user-header">
			<span>계정 설정</span>&nbsp &nbsp &nbsp &nbsp &nbsp <button onclick="modalClose()">X</button>
		</div>
		<div class="user-list" id="userModalList"></div>
	</div>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>