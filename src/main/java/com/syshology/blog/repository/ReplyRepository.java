package com.syshology.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syshology.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
