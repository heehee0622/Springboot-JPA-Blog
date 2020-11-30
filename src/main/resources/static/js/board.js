let index = {
	
	init: function(){
		$("#btn_save").on("click",() =>{ // function(){} => ()=> this 바인딩 하기 위해서 
			this.save();
		});
		
	$("#btn-delete").on("click",() =>{ // function(){} => ()=> this 바인딩 하기 위해서 
			this.deleteById();
		});
		
			$("#btn-update").on("click",() =>{ // function(){} => ()=> this 바인딩 하기 위해서 
			this.update();
		});
		
		$("#btn-reply-save").on("click",() =>{ // function(){} => ()=> this 바인딩 하기 위해서 
			this.replySave();
		});
	},
		
	save: function(){
		/*alert("회원가입이 완료 되었습니다.");*/
		let data = {
			title:$("#title").val(),	
			content:$("#content").val()
		}
			console.log(data);
			//ajax  호출 기본은 async 호출
			$.ajax({
				type:"POST",
				url:"/api/board",
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8", // request가 어떤 타입인지
				dataType:"json" // Response가 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 객체로 변경 해준다.
				//dataType 없어도 json으로 변경 해준다.
			}).done(function(res){
				alert("글쓰기 완료 되었습니다.");
				console.log(res)
				location.href="/";
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		
	},
	
		replySave: function(){
		/*alert("회원가입이 완료 되었습니다.");*/
		let data = {
			content:$("#reply-content").val()
		}
		let boardId = $("#boardId").val();
			console.log(data);
			//ajax  호출 기본은 async 호출
			$.ajax({
				type:"POST",
				url:`/api/board/${boardId}/reply`,
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8", // request가 어떤 타입인지
				dataType:"json" // Response가 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 객체로 변경 해준다.
				//dataType 없어도 json으로 변경 해준다.
			}).done(function(res){
				alert("댓글 작성이 완료 되었습니다.");
				console.log(res)
				location.href=`/board/${boardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		
	},
	
		update: function(){
		/*alert("회원가입이 완료 되었습니다.");*/
			let id = $("#id").val();
			let data = {
				title:$("#title").val(),	
				content:$("#content").val()
			}
			console.log(data);
			//ajax  호출 기본은 async 호출
			$.ajax({
				type:"PUT",
				url:"/api/board/"+id,
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8", // request가 어떤 타입인지
				dataType:"json" // Response가 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 객체로 변경 해준다.
				//dataType 없어도 json으로 변경 해준다.
			}).done(function(res){
				alert("글수정 완료 되었습니다.");
				console.log(res)
				location.href="/";
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		
	},
			replyDelete: function(boardId, replyId){
		/*alert("회원가입이 완료 되었습니다.");*/
			$.ajax({
				type:"DELETE",
				url:`/api/board/${boardId}/reply/${replyId}`,
				dataType:"json" // Response가 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 객체로 변경 해준다.
				//dataType 없어도 json으로 변경 해준다.
			}).done(function(res){
				alert("댓글 삭제 성공.");
				location.href=`/board/${boardId}`;
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		
	},
	
	deleteById: function(){
		var id = $("#id").text();
			$.ajax({
				type:"DELETE",
				url:"/api/board/"+id,
				dataType:"json" // Response가 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 객체로 변경 해준다.
				//dataType 없어도 json으로 변경 해준다.
			}).done(function(res){
				alert("삭제 완료 되었습니다.");
				location.href="/";
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		
	}
	
}

index.init();