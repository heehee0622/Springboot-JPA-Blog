<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="username">UserName</label> <input type="text" class="form-control" placeholder="Enter userame" id="username">
		</div>

		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter mail" id="email">
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> <input type="pasword" class="form-control" placeholder="Enter password" id="password">
		</div>

	</form>
	<button id="btn_save" class="btn btn-primary">회원가입완료</button>
</div>
<script src="/js/user.js">
	
</script>
<%@ include file="../layout/footer.jsp"%>
