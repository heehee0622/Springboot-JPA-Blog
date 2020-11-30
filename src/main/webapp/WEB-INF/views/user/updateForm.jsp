<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<input type="hidden"  id = "id" value ="${principal.user.id}" >
		<div class="form-group">
			<label for="username">UserName</label> 
			<input type="text"  value ="${principal.user.username}" class="form-control" placeholder="Enter userame" id="username" realdonly>
		</div>
		
		<div class="form-group">
			<label for="email">Email</label> <input type="email"  value ="${principal.user.email}" class="form-control" placeholder="Enter mail" id="email"  readonly>
		</div>
		<c:if test="${empty principal.user.oauth}">
			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
			</div>	
		</c:if>
	</form>
	<button id="btn_update" class="btn btn-primary">회원 수정 완료</button>
</div>
<script src="/js/user.js">  </script>
<%@ include file="../layout/footer.jsp"%>