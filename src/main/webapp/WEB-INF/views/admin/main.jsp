<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h2>비속어 관리</h2>
	<br>
	<br>          
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
                <td onClick="location.href='/post/${pList.id}'">${pList.title }</td>
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

</div>

<%@ include file="../layout/footer.jsp"%>