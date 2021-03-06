<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../include/header.jsp" %>

<div class="box-header with-border">
	<h3 class="box-title">LIST PAGING</h3>
</div> 
<div class="box-body">
	<table class="table table-bordered">
		<tr>
			<th style="width: 10px">BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th style="width: 40px">VIEWCNT</th>
		</tr>
		
		<c:forEach items="${list }" var="boardVO">
			<tr>
				<td>${boardVO.bno }</td>
				<td><a href="/board/read?bno=${boardVO.bno}">${boardVO.title }</a></td>
				<td>${boardVO.writer }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate }"/></td>
				<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
	
<%@ include file="../include/footer.jsp" %>

<script>
	var result = '${result}'; // BoardController.java 에서 redirectAtt.addFlashAttribute("result", "Success!!");로 보낸 패러미터 호출
	// result = 'success';
	
	if(result == 'Success!!'){
		alert("글이 작성 되었습니다!!");
	}
</script>