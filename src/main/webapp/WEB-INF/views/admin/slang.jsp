<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>비속어 관리</h2>
	<br>
	<br>
	<form action="/slang-filter" method="POST">
		<div class="input-group mb-3">
		  <input type="text" class="form-control" placeholder="등록할 비속어를 입력하세요." name="scNm" id="scNm">
		  <div class="input-group-append">
		    <button class="btn btn-outline-secondary">등록</button>
		  </div>
		</div>
	</form>
    <br>
	<br>
  <button id="btn-modify" class="btn btn-primary" onclick="deleteSlang()" style="float: right;">삭제</button>
  <br>
  <br>
  <table  class="table" id="slang_table" style="table-layout: fixed">
    <thead>
      <tr>
      	<th></th>
      	<th width="10%">번호</th>
        <th width="50%">비속어</th>
        <th width="15%">작성자</th>
        <th width="20%">작성일</th>
      </tr>
    </thead>
    <tbody>
    <c:choose>
		<c:when test="${fn:length(list) > 0 }">
	    	<c:set var="num" value="${pageMaker.cri.page*10-9}"/>
			     <c:forEach items="${list }" var="sList">
			         <tr>
			         	 <td><input type="checkbox" name="select-slang" value="${sList.id }"></td>
				         <td>${num }</td>
				         <td>${sList.scNm}</td>
				         <td>${sList.writer}</td>
				         <td>${sList.createdAt }</td>
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
     </tbody>
  </table>
  
  <br>

		<nav aria-label="Page navigation example" style="float: right; position: relative; left: -50%;">
		  <ul class="pagination">
		  <c:if test="${pageMaker.prev }">
		    <li class="page-item">
		      <a class="page-link" href='<c:url value="/admin/slang?page=${pageMaker.startPage-1 }"/>' aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		        <span class="sr-only">Previous</span>
		      </a>
		    </li>
		   </c:if>
		   <c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
		    <li class="page-item"><a class="page-link" href='<c:url value="/admin/slang?page=${pageNum }"/>'>${pageNum }</a></li>
		   </c:forEach>
		   <c:if test="${pageMaker.next && pageMaker.endPage >0 }">
		    <li class="page-item">
		      <a class="page-link" href='<c:url value="/admin/slang?page=${pageMaker.endPage+1 }"/>' aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		        <span class="sr-only">Next</span>
		      </a>
		    </li>
		   </c:if>
		  </ul>
		</nav>
		
	</div>


<script src="/js/slang.js"></script>
<%@ include file="../layout/footer.jsp"%>