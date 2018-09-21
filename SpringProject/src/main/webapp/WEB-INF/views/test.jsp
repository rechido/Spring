<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<h2>Ajax Test Page</h2>
	
	<div>
		<div>
			Replyer <input type="text" name="replyer" id="newReplyWriter"/>
		</div>
		<div>
			ReplyerText <input type="text" name="replytext" id="newReplyText"/>
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>
	
	<ul id="replies">

	</ul>

	<!-- jQuery 2.1.4 -->
	<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="/resources/plugins/jQuery/jquery.validate.js"></script>



</body>
</html>

<script>
	$(document).ready(function() {
		
		var bno = 1055;
		var str="";
		
		function getAllList(){
			str="";
			
			$.getJSON("/replies/all/" + bno, function(data) { // json 데이터 받아오기 메소드, bno = 1055인 게시글의 모든 댓글 조회
				console.log(data.length);
				//alert(data.length);
				
				$(data).each(function(){ // jquery 반복문 
					str += "<li data-rno='" + this.rno + "' class='replyLi'>"
							+ this.rno + ":" + this.replytext
							+ "</li>";
				});
				
				$("#replies").html(str); // html문서에 문구 내용 삽입
			});
		}
		
		
		
		
		getAllList();
		
		// 댓글 등록 후 리스트 다시 받아오기
		$("#replyAddBtn").on("click", function(){
			var replyer = $("#newReplyWriter").val();
			var replytext = $("#newReplyText").val();
			
			$.ajax({
				type : 'post',
				url : '/replies',
				headers : {
					"Content-Type" : "application/json", 
					"X-HTTP-Method-Override" : "POST"					
				},
				dataType : 'text',
				data : JSON.stringify({
					bno : bno,
					replyer : replyer,
					replytext : replytext
				}),
				success : function(result){
					if(result=='success'){
						alert("등록되었습니다.");
						getAllList(); // 새로 고침
					}
				}
			});
		});
	});
</script>