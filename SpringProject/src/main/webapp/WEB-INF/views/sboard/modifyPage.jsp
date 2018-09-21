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
					<h3 class="box-title">MODIFY BOARD</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" method="post">
					<input type='hidden' name='page' value="${criteria.page}">
					<input type='hidden' name='perPageNum' value="${criteria.perPageNum}">
					<%-- <input type='hidden' name='searchType' value="${criteria.searchType}">
					<input type='hidden' name='keyword' value="${criteria.keyword}"> --%>
					<!-- box-body -->
					<div class="box-body">
						<div class="form-group">
							<label for="bno">BNO</label> 
							<input type="text" id="bno" name='bno' class="form-control" value="${boardVO.bno}" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="title">Title</label> 
							<input type="text" id="title" name='title' class="form-control" value="${boardVO.title}" >
						</div>
						<div class="form-group">
							<label for="content">Content</label>
							<textarea id="content" name="content" class="form-control" rows="3" >${boardVO.content}</textarea>
						</div>
						<div class="form-group">
							<label for="writer">Writer</label> 
							<input type="text" id="writer" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
						</div>
					</div>
					<!-- /.box-body -->
				</form>
				

				<div class="box-footer">
					<button type="submit" class="btn btn-primary" id="">SAVE</button>
					<button type="submit" class="btn btn-warning" id="">CANCEL</button>					
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
	
	$(".btn-primary").on("click", function(){		// SAVE버튼을 클릭했을 때
		formObj.submit();						
	});
	
	$(".btn-warning").on("click", function(){		// CANCEL버튼을 클릭했을 때
		self.location = "/sboard/list?"
						+ "page=${criteria.page}"
						+ "&perPageNum=${criteria.perPageNum}"
						+ "&searchType=${criteria.searchType}"
						+ "&keyword=${criteria.keyword}";
	});
	
});
</script>
