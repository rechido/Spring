<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>

<style type="">

#modDiv{
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}

</style>

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
	
	<div id="modDiv" style="display: none;">
		<div class="modal-title"></div>
		<div>
			<input type="text" id="replytext"/>
		</div>
		<div>
			<button type="button" id="replyModBtn">Modify</button>
			<button type="button" id="replyDelBtn">Delete</button>
			<button type="button" id="CloseBtn">Close</button>
		</div>
	</div>

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
							+ this.rno + ":" + "<span>" + this.replytext + "</span>"
							+ "<button>MOD</button>"
							+ "</li>";
				});
				
				$("#replies").html(str); // html문서에 문구 내용 삽입
			});
		}
		
		getAllList();
		
		// 수정 버튼을 눌렀을 때 해당 댓글 내용 받아오기
		$("#replies").on("click", ".replyLi button", function(){
			var reply = $(this).parent();
			var rno = reply.attr("data-rno");
			var replytext = reply.children("span").text();
			//alert(rno + " : " + replytext);
			 
			$(".modal-title").html(rno); // class가 modal-title인 태그의 html 텍스트에 rno 값을 집어넣는다. set 메서드
			$("#replytext").val(replytext);
			$("#modDiv").show("slow");
		});
		
		// 댓글 등록 후 리스트 다시 받아오기
		$("#replyAddBtn").on("click", function(){
			var replyer = $("#newReplyWriter").val();
			var replytext = $("#newReplyText").val();
			
			$.ajax({
				type : 'post', // @RequestMapping(value = "", method = RequestMethod.POST) 리퀘스트 메소드 타입에 맞춘다.
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
		
		// 삭제 버튼 액션 이벤트
		$("#replyDelBtn").on("click", function(){
			var rno = $(".modal-title").html(); // class가 modal-title인 태그의 html 텍스트에서 값을 가져온다. html()의 매개변수를 비우면 get 메서드
			//alert("rno= " + rno);
			
			$.ajax({
				type : 'delete', // @RequestMapping(value = "/{rno}", method = RequestMethod.DELETE)
				url : '/replies/' + rno,
				headers : {
					"Content-Type" : "application/json", 
					"X-HTTP-Method-Override" : "DELETE"					
				},
				dataType : 'text',
				success : function(result){
					if(result=='success'){
						alert("삭제되었습니다.");
						$("#modDiv").hide("slow");
						getAllList(); // 새로 고침
					}
				}
			});
		});
		
		// 댓글 수정 버튼 액션 이벤트
		$("#replyModBtn").on("click", function(){
			var rno = $(".modal-title").html();
			var replytext = $("#replytext").val();
			alert("rno: " + rno + "\nreplytext: " + replytext);
			$.ajax({
				type : 'put', 
				url : '/replies/' + rno,
				headers : {
					"Content-Type" : "application/json", 
					"X-HTTP-Method-Override" : "PUT"					
				},
				dataType : 'text',
				data : JSON.stringify({
					rno : rno,
					replytext : replytext
				}),
				success : function(result){
					if(result=='success'){
						console.log("result: " + result)
						alert("수정되었습니다.");
						$("#modDiv").hide("slow");
						getAllList(); // 새로 고침
					}
				}
			});
		});
	});
</script>