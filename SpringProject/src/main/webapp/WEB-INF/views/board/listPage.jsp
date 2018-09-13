<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../include/header.jsp" %>

<div class="box-header with-border">
	<h3 class="box-title">LIST PAGING</h3>
	<select>
		<option>10</option>
		<option>20</option>
		<option>30</option>
		<option>100</option>
		<option>500</option>
	</select>
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
				<td><a href="/board/read${pageMaker.makeParameter(pageMaker.cri.page) }&bno=${boardVO.bno }">${boardVO.title }</a></td>
				<td>${boardVO.writer }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate }"/></td>
				<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
<!-- /.box-body -->
<div class="box-footer">
	<div class="text-center"> 
		<ul class="pagination">
			<c:if test="${pageMaker.prev }">
				<li>
					<a href="listPage?page=${pageMaker.startPage-1 }">&laquo;</a> <!-- &laquo; 는 << 기호를 의미 -->
				</li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
				<li <c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
					<a href="listPage${pageMaker.makeParameter(idx) }">${idx }</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<li>
					<a href="listPage?page=${pageMaker.endPage+1 }">&raquo;</a> <!-- &raquo; 는 >> 기호를 의미 -->
				</li>
			</c:if>
		</ul>
	</div>
</div>


	
<%@ include file="../include/footer.jsp" %>

<script>
	var result = '${result}'; // BoardController.java 에서 redirectAtt.addFlashAttribute("result", "Success!!");로 보낸 패러미터 호출
	// result = 'success';
	
	if(result == 'Success!!'){
		alert("글이 작성 되었습니다!!");
	}
</script>