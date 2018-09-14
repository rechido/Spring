<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->
				
				<!-- 
					게시글 번호 - bno
					현재 페이지 번호 - page
					데이터 갯수 - perPageNum
				 -->

				<form role="form" method="post">					
					<input type='hidden' name='page' value="${criteria.page}">
					<input type='hidden' name='perPageNum' value="${criteria.perPageNum}">
					<input type='hidden' name='bno' value="${boardVO.bno}">
				</form>

				<div class="box-body">
					<div class="form-group">
						<label for="title">Title</label> 
						<input type="text" id="title" name='title' class="form-control" value="${boardVO.title}" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="content">Content</label>
						<textarea id="content" name="content" class="form-control" rows="3" readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label for="writer">Writer</label> 
						<input type="text" id="writer" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
					<button type="submit" class="btn btn-danger" id="removeBtn">REMOVE</button>
					<button type="submit" class="btn btn-primary" id="goListBtn">LIST</button>
				</div>

			</div>
			<!-- /.box -->
		</div>
		<!-- /.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<%@include file="../include/footer.jsp"%>

<script>
$(document).ready(function(){
	var formObj = $("form[role='form']");
	console.log(formObj); // (크롬) 웹브라우저 개발자도구 콘솔창에서 로그 확인
	
	$("#modifyBtn").on("click", function(){		// 수정버튼을 클릭했을 때
		formObj.attr("action","/board/modifyPage");	// controller @RequestMapping(value = "/modify"
		formObj.attr("method","get");			//, method = RequestMethod.GET)
		formObj.submit();						// <input type='hidden' name
	});
	
	$("#removeBtn").on("click", function(){
		formObj.attr("action","/board/removePage");	// controller @RequestMapping(value = "/remove"
		//formObj.attr("method","get");		
		formObj.submit();
		//alert('${msg_remove}');
	});
	
	$("#goListBtn").on("click", function(){		
		formObj.attr("method","get");
		formObj.attr("action","/board/listPage");		
		formObj.submit();
	});
	
});
</script>
