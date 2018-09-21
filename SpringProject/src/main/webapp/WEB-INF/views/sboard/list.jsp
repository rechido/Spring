<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../include/header.jsp" %>

<div class="box-header with-border">
	<h3 class="box-title">LIST PAGING</h3>
	<select name="${criteria.perPageNum }" id="perPageNumCntSelect">
		<option value="10" <c:out value="${criteria.perPageNum == 10 ? 'selected':'' }" />>10개씩 보기</option> 
		<option value="20" <c:out value="${criteria.perPageNum == 20 ? 'selected':'' }" />>20개씩 보기</option>
		<option value="30" <c:out value="${criteria.perPageNum == 30 ? 'selected':'' }" />>30개씩 보기</option>
		<option value="100" <c:out value="${criteria.perPageNum == 100 ? 'selected':'' }" />>100개씩 보기</option>
		<option value="500" <c:out value="${criteria.perPageNum == 500 ? 'selected':'' }" />>500개씩 보기</option>		
	</select>
</div> 

<form role="form" method="post">
	<input type='hidden' name='page' value="${criteria.page}">
	<input type='hidden' name='perPageNum' value="${criteria.perPageNum}">
</form>

<div class="box-body">

	<select name="searchType" id="searchSelect">
		<option value="n" <c:out value="${criteria.searchType == null? 'selected':''}" />>---------------------------</option>
		<option value="t" <c:out value="${criteria.searchType eq 't'? 'selected':''}" />>Title</option> <!-- 문자열 비교는 eq로 한다. -->
		<option value="c" <c:out value="${criteria.searchType eq 'c'? 'selected':''}" />>Contents</option>
		<option value="w" <c:out value="${criteria.searchType eq 'w'? 'selected':''}" />>Writer</option>
		<option value="tc" <c:out value="${criteria.searchType eq 'tc'? 'selected':''}" />>Title+Contents</option>
		<option value="cw" <c:out value="${criteria.searchType eq 'cw'? 'selected':''}" />>Contents+Writer</option>
		<option value="tcw" <c:out value="${criteria.searchType eq 'tcw'? 'selected':''}" />>Title+Contents+Writer</option>
	</select>
	<input type="text" name="keyword" id="keywordInput" value="${criteria.keyword }">
	<button id="searchBtn">Search</button>
	<button id="newBtn">New Board</button>

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
				<td><a href="/sboard/readPage${pageMaker.makeParameter(pageMaker.cri.page) }&bno=${boardVO.bno }">${boardVO.title }</a></td>
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
					<a href="list?page=${pageMaker.startPage-1 }">&laquo;</a> <!-- &laquo; 는 << 기호를 의미 -->
				</li>
			</c:if>
			<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
				<li <c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
					<a href="list${pageMaker.makeParameter(idx) }">${idx }</a>
				</li>
			</c:forEach>
			<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
				<li>
					<a href="list?page=${pageMaker.endPage+1 }">&raquo;</a> <!-- &raquo; 는 >> 기호를 의미 -->
				</li>
			</c:if>
		</ul>
	</div>
</div>


	
<%@ include file="../include/footer.jsp" %>

<script>

var formObj = $("form[role='form']");
console.log(formObj); // (크롬) 웹브라우저 개발자도구 콘솔창에서 로그 확인


$(document).ready(function(){
	$('#searchBtn').on('click', function(event){
		self.location = "list?"
		+ "page=1"
		+ "&perPageNum=${criteria.perPageNum}"
		+ "&searchType=" + $('#searchSelect option:selected').val()
		+ "&keyword=" + $('#keywordInput').val();
	});
	
	$('#newBtn').on('click', function(event){
		self.location = "register";
	});
	
	$('#perPageNumCntSelect').on('change', function(event){
		self.location = "list?"
			+ "page=1"
			+ "&perPageNum=" + $("#perPageNumCntSelect option:selected").val()
			+ "&searchType=${criteria.searchType}"
			+ "&keyword=${criteria.keyword}";
	});
	
	var result = '${result}'; // BoardController.java 에서 redirectAtt.addFlashAttribute("result", "Success!!");로 보낸 패러미터 호출
	// result = 'success';

	if(result == 'write'){
		alert("글이 등록되었습니다.");
	} else if(result == 'remove'){
		alert("글을 삭제했습니다.");
	} else if(result == 'modify'){
		alert("글을 수정했습니다.");
	}
});

</script>