<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<%@ include file="../layout/header.jsp"%>

<div class="container">
  <h2>Board</h2>
  <p>글을 자유롭게 작성하세요</p>
  <c:choose>
  	<c:when test = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.user.role eq 'ADMIN'}">
  		 <table class="table" style="table-layout: fixed">
		    <thead>
		      <tr>
		      	<th width="10%">번호</th>
		        <th width="50%">글 제목</th>
		        <th width="15%">작성자</th>
		        <th width="30%">작성일</th>
		        <th width="20%">관리자툴</th>
		        <th width="15%">상태</th>
		        
		      </tr>
		    </thead>
		    <tbody>
		    <c:choose>
		        <c:when test="${fn:length(list) > 0 }">
		        	<c:set var="num" value="${pageMaker.postNum }"/>
		            <c:forEach items="${list }" var="pList">
		                <tr>
		                <td>${num }</td>
		                <c:choose>
		        			<c:when test="${pList.status == 15}">
		        			<td onClick="location.href='/post/${pList.id}'">블라인드 처리된 글입니다.</td>
		        			<input type="hidden" name="title-${pList.id}" id="title-${pList.id}" value="${pList.title}"/>
		        			<input type="hidden" name="content-${pList.id}" id="content-${pList.id}" value="${pList.content}"/>
		        			</c:when>
			        		<c:otherwise>
			        		<td onClick="location.href='/post/${pList.id}'">${pList.title }</td>
			        		</c:otherwise>
		        		</c:choose>
		                <td>${pList.writer}</td>
		                <td>${pList.created_at }</td>
		                <td>
		                <c:if test="${pList.status == 15}">
		                <button id="btn-update" class="btn btn-secondary" onClick="modifyStatus(${pList.id})">예외</button>
		                </c:if>
						<button id="btn-delete" class="btn btn-secondary" onclick="deletePost(${pList.id})">삭제</button></td>
						<c:choose>
							<c:when test="${pList.status == 15}">
							<td>블라인드(비속어)</td>
							</c:when>
							<c:when test="${pList.status == 16}">
							<td>예외</td>
							</c:when>
							<c:otherwise>
							<td>게시중</td>
							</c:otherwise>
						</c:choose>
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
  	</c:when>
  	<c:otherwise>
	  	<table class="table" style="table-layout: fixed">
		    <thead>
		      <tr>
		      	<th width="10%">번호</th>
		        <th width="70%">글 제목</th>
		        <th width="15%">작성자</th>
		        <th width="20%">작성일</th>
		      </tr>
		    </thead>
		    <tbody>
		    <c:choose>
		        <c:when test="${fn:length(list) > 0 }">
		        	<c:set var="num" value="${pageMaker.postNum }"/>
		            <c:forEach items="${list }" var="pList">
		                <tr>
		                <td>${num }</td>
		                <c:choose>
		        			<c:when test="${pList.status == 15}">
		        			<td onClick="location.href='/post/${pList.id}'">블라인드 처리된 글입니다.</td>
		        			</c:when>
			        		<c:otherwise>
			        		<td onClick="location.href='/post/${pList.id}'">${pList.title }</td>
			        		</c:otherwise>
		        		</c:choose>
		                <td>${pList.writer}</td>
		                <td>${pList.created_at }</td>
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
  	</c:otherwise>
  </c:choose>            
  
	 <nav aria-label="Page navigation example" style="float: right; position: relative; left: -50%;">
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

<script src="/js/main.js"></script>
<%@ include file="../layout/footer.jsp"%>