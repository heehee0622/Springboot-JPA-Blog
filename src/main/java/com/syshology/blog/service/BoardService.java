package com.syshology.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syshology.blog.model.Board;
import com.syshology.blog.model.Reply;
import com.syshology.blog.model.User;
import com.syshology.blog.repository.BoardRepository;
import com.syshology.blog.repository.ReplyRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ReplyRepository replyRepository;

	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException(" 장애 발생");
		});
	}

	@Transactional
	public void 삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Board reqBoard) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException(" 글찾기 실패");
		});
		board.setTitle(reqBoard.getTitle());
		board.setContent(reqBoard.getContent());
	}

	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply) {
		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
		});
		requestReply.setUser(user);
		requestReply.setBoard(board);
		replyRepository.save(requestReply);
	}

	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
