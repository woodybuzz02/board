<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layout/header.jsp"%>

<div class="container">
  <h2>Board</h2>
  <p>글을 자유롭게 작성하세요</p>            
  <table class="table" style="table-layout: fixed">
    <thead>
      <tr>
        <th width="70%">글제목</th>
        <th width="10%">작성자</th>
        <th width="20%">작성일</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${posts}">
      <tr>
        <td>${post.title}</td>
        <td>${post.user.username}</td>
        <td>${post.createAt}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<%@ include file="../layout/footer.jsp"%>