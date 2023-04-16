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
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>john@example.com</td>
      </tr>
      <tr>
        <td>Mary</td>
        <td>Moe</td>
        <td>mary@example.com</td>
      </tr>
      <tr>
        <td>July</td>
        <td>Dooley</td>
        <td>july@example.com</td>
      </tr>
    </tbody>
  </table>
</div>

<%@ include file="../layout/footer.jsp"%>