<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/menu.jsp" %>
<%@include file="../layout/header.jsp" %>
<%
	BoardVO vo = (BoardVO) request.getAttribute("vo");
	System.out.println(vo);
%>

	<h3>게시글 수정화면</h3>
	<form action="modifyBoard.do" method="post" >
		<input type="hidden" name="bno" class="form-control" value="<%=vo.getBoardNo() %>">
		<table class="table">
			<tr>
				<th>제목</th>
				<td><input type="text" class="form-control" name="title" value="<%=vo.getTitle() %>"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" class="form-control" name="writer" value="<%=vo.getWriter() %>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="2"><textarea class="form-control" cols="40" rows="5" name="content"><%=vo.getContent() %></textarea></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td><img src="images/"></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" class="btn btn-primary" value="수정"> 
				<input type="reset" class="btn btn-warning" value="초기화">
				</td>
			</tr>
		</table>
	</form>
<%@include file="../layout/footer.jsp" %>