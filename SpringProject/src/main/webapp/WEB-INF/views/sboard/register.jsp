<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../include/header.jsp" %>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				
				<form role="form" method="post" id="registerForm">
					<div class="box-body">
						<div class="form-group">
							<label for="inputTitle">Title</label>
							<input type="text" name="title" id="inputTitle" class="form-control" placeholder="Enter Title">
						</div>
						<div class="form-group">
							<label for="inputContent">Content</label>
							<textarea name="content" id="inputContent" rows="3" class="form-control" placeholder="Enter ..."></textarea>
						</div>
						<div class="form-group">
							<label for="inputWriter">Writer</label>
							<input type="text" name="writer" id="inputWriter" class="form-control" placeholder="Enter Writer">
						</div>
					</div>
					<!-- /.box body -->
					
					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>					
				</form>
			</div>
			<!-- /.box -->		
		</div>
		<!-- /.col (left) -->
	</div>
	<!-- /.row -->

</section>
<!-- /.content -->
<!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>

<script>

$(document).ready(function () {
    // 이벤트를 연결합니다.
    $('#registerForm').submit(function (event) {
        // input 태그의 값을 추출합니다.
        var title = $('#inputTitle').val();
        var content = $('#inputContent').val();
        var writer = $('#inputWriter').val();

        // 유효성을 검사합니다.
        if (title.length != 0) {
        	if(content.length != 0){
        		if(writer.length != 0){
        			// 유효성 검사 통과
                    console.log("글 등록 - 유효성 검사 통과");
        		}else{
        			// 유효성 검사 실패
                    alert('작성자명을 입력해주세요.');
                    event.preventDefault(); // submit 전송을 지워버림.
        		}
        	}else{
        		// 유효성 검사 실패
                alert('내용을 입력해주세요.');
                event.preventDefault(); // submit 전송을 지워버림.
        	}            
        } else {
            // 유효성 검사 실패
            alert('제목을 입력해주세요.');
            event.preventDefault(); // submit 전송을 지워버림.
        }
    });
});

</script>