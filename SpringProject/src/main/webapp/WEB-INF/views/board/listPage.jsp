<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../include/header.jsp" %>

<div class="box-header with-border">
	<h3 class="box-title">LIST PAGING</h3>
	<select name="">
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="30">30</option>
		<option value="100">100</option>
		<option value="500">500</option>
	</select>
</div> 

<form role="form" method="post">
	<input type='hidden' name='page' value="${criteria.page}">
	<input type='hidden' name='perPageNum' value="${criteria.perPageNum}">
</form>

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
				<td><a href="/board/readPage${pageMaker.makeParameter(pageMaker.cri.page) }&bno=${boardVO.bno }">${boardVO.title }</a></td>
				<td>${boardVO.writer }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate }"/></td>
				<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
			</tr>
		</c:forEach>
		
	</table>
</div>
<!-- /.box-body -->
<div class="box-footer">
	<div class="col-md-3"></div>
	<div class="text-center col-md-6"> 
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
	<div class="text-right col-md-3">
		<button type="submit" class="btn btn-primary" id="registerBtn">Write</button>
	</div>
</div>


	
<%@ include file="../include/footer.jsp" %>

<script>
	var result = '${result}'; // BoardController.java 에서 redirectAtt.addFlashAttribute("result", "Success!!");로 보낸 패러미터 호출
	// result = 'success';
	
	if(result == 'write'){
		alert("글을 작성했습니다.");
	} else if(result == 'remove'){
		alert("글을 삭제했습니다.");
	} else if(result == 'modify'){
		alert("글을 수정했습니다.");
	}
	
	var formObj = $("form[role='form']");
	console.log(formObj); // (크롬) 웹브라우저 개발자도구 콘솔창에서 로그 확인
	
	$("#registerBtn").on("click", function(){		
		formObj.attr("action","/board/registerPage");	
		formObj.attr("method","get");			
		formObj.submit();						
	});
</script>