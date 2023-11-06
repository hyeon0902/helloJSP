<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/menu.jsp"%>
<%@include file="../layout/header.jsp"%>
	<%
    BoardVO vo = (BoardVO) request.getAttribute("bno");
    %>
    <h3>상세화면(조회화면)</h3>
	<form action="modifyForm.do" name="myFrm">
	<input type="hidden" name="bno" value="<%=vo.getBoardNo() %>">
		<table class="table">
			<tr>
				<th>글 번호</th>
				<td><%=vo.getBoardNo()%></td>
				<th>작성일자</th>
				<td><%=vo.getWriteDate()%></td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3"><%=vo.getTitle()%></td>
			</tr>
			<tr>
				<td colspan="4"><textarea rows="5" cols="40"><%=vo.getContent()%></textarea></td>
			</tr>
			<tr>
				<th>이미지</th>
				<td colspan="3">
				<% if(vo.getImage() != null) { %>
				<img style="align: center;" width="80px" src="images/<%=vo.getImage() %>"></td>
				<% } %>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=vo.getWriter()%></td>
				<th>조회수</th>
				<td><%=vo.getViewCnt()%></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<% if(logId != null && logId.equals(vo.getWriter())) { %>
					<input type="submit" class="btn btn-primary" value="수정">
					<input type="button" class="btn btn-warning" value="삭제">
				<% } else { %>
					<input type="submit" disabled="disabled" class="btn btn-primary" value="수정">
					<input type="button" disabled="disabled" class="btn btn-warning" value="삭제">
				<% } %>
				</td>
			</tr>
		</table>
	</form>
	<script>
		document.querySelector('input[type=button]').addEventListener('click', function(e){
			document.forms.myFrm.action = 'removeForm.do';
			document.forms.myFrm.submit();
		})
	</script>
<%@include file="../layout/footer.jsp"%>  
