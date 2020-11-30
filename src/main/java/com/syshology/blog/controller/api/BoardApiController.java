package com.syshology.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.syshology.blog.config.auth.PrincipalDetail;
import com.syshology.blog.dto.ResposeDto;
import com.syshology.blog.model.Board;
import com.syshology.blog.model.Reply;
import com.syshology.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	@PostMapping("/api/board")
	public ResposeDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board, principal.getUser());
		return new ResposeDto<Integer>(HttpStatus.OK.value(), 1);
	}
	@DeleteMapping("/api/board/{id}")
	public ResposeDto<Integer> deleteById(@PathVariable int id){
		boardService.삭제하기(id);
		return new ResposeDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	@PutMapping("/api/board/{id}")
	public ResposeDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.글수정하기(id, board);
		return new ResposeDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	@PostMapping("/api/board/{boardId}/reply")
	public ResposeDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.댓글쓰기(principal.getUser(), boardId ,reply);
		return new ResposeDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResposeDto<Integer> replyDelete(@PathVariable int replyId) {
		boardService.댓글삭제(replyId);
		return new ResposeDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
