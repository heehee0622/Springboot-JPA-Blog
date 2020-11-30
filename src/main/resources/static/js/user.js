let index = {
	
	init: function(){
		$("#btn_save").on("click",() =>{ // function(){} => ()=> this 바인딩 하기 위해서 
			this.save();
		});
	$("#btn_update").on("click",() =>{ // function(){} => ()=> this 바인딩 하기 위해서 
			this.update();
		});
	},
		
	save: function(){
		/*alert("회원가입이 완료 되었습니다.");*/
		let data = {
			username:$("#username").val(),	
			email:$("#email").val(),		
			password:$("#password").val()
		}
			console.log(data);
			//ajax  호출 기본은 async 호출
			$.ajax({
				type:"POST",
				url:"/auth/joinProc",
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8", // request가 어떤 타입인지
				dataType:"json" // Response가 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 객체로 변경 해준다.
				//dataType 없어도 json으로 변경 해준다.
			}).done(function(res){
				if(res.status === 500){
					alert("회원가입에 실패 하였습니다.")
				}else{
					alert("회원 가입이 완료 되었습니다.");
					console.log(res)
					location.href="/";
				}
				
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		
	}
	
	,
	update: function(){
		let data = {
			id: $("#id").val(),
			username : $("#username").val(),	
			email: $("#email").val(),		
			password: $("#password").val()
		}
			$.ajax({
				type:"PUT",
				url:"/user",
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8", // request가 어떤 타입인지
				dataType:"json" // Response가 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 객체로 변경 해준다.
			}).done(function(res){
				alert("회원 수정이 완료 되었습니다.");
				console.log(res)
				location.href="/";
				
			}).fail(function(error){
				alert(JSON.stringify(error))
			});
		
	}
	
}

index.init();