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
				
				<form role="form" method="post" id="registForm">
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