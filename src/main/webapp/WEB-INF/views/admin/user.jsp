<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>계정 관리</h2>
	<br>
	  <button id="btn-modify" class="btn btn-primary" onclick="modifyUserRole()" style="float: right;">수정</button>    
	<br>
	<br>
	  <table class="table" id="user_table" style="table-layout: fixed">
	    <thead>
	      <tr>
	      	<th width="10%">번호</th>
	        <th width="15%">아이디</th>
	        <th width="20%">이메일</th>
	        <th width="15%">권한</th>
	        <th width="15%">가입일자</th>
	      </tr>
	    </thead>
	    <tbody>
	    <c:choose>
	        <c:when test="${fn:length(list) > 0 }">
	        	<c:set var="num" value="${pageMaker.startPage*10-9}"/>
	            <c:forEach items="${list }" var="uList" varStatus="status">
	              <tr>
	              	<td>${num }</td>
	                <td>${uList.username}</td>
	                <td>${uList.email }</td>
	                <td>
	                <input type="hidden" id="userId" value="${uList.id}" />
	                <input type="hidden" id="userRole" value="${uList.role}" />
	                <select id="selectRole">
	                <c:choose>
	                	<c:when test="${uList.role eq 'USER'}">
		                	<option name="role" value="USER" selected>USER</option>
							<option name="role" value="ADMIN">ADMIN</option>
	                	</c:when>
	                	<c:otherwise>
	                		<option name="role" value="USER">USER</option>
							<option name="role" value="ADMIN" selected>ADMIN</option>
	                	</c:otherwise>	
	                </c:choose>
					</select>
	                </td>
	                <td>${uList.created_at}</td>
	              </tr>
	            <c:set var="num" value="${num+1 }"></c:set>
	            </c:forEach>
	        </c:when>
	        <c:otherwise>
	            <tr>
	                <td colspan="5">조회된 결과가 없습니다.</td>
	            </tr>
	        </c:otherwise>
	    </c:choose>
	  </table>
	  
	  <br>
	
		<nav aria-label="Page navigation example" style="float: right; position: relative; left: -50%;">
		  <ul class="pagination">
		  <c:if test="${pageMaker.prev }">
		    <li class="page-item">
		      <a class="page-link" href='<c:url value="/admin/user?page=${pageMaker.startPage-1 }"/>' aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		        <span class="sr-only">Previous</span>
		      </a>
		    </li>
		   </c:if>
		   <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
		    <li class="page-item"><a class="page-link" href='<c:url value="/admin/user?page=${pageNum }"/>'>${pageNum }</a></li>
		   </c:forEach>
		   <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
		    <li class="page-item">
		      <a class="page-link" href='<c:url value="/admin/user?page=${pageMaker.endPage+1 }"/>' aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		        <span class="sr-only">Next</span>
		      </a>
		    </li>
		   </c:if>
		  </ul>
		</nav>
</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>