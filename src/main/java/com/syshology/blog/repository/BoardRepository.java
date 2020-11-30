
package com.syshology.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syshology.blog.model.Board;

public interface BoardRepository  extends JpaRepository<Board, Integer> {
	
}
