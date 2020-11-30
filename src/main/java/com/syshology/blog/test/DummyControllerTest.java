package com.syshology.blog.test;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.syshology.blog.model.RoleType;
import com.syshology.blog.model.User;
import com.syshology.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입
	private UserRepository userRepository;

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		User findUser = userRepository.findById(id).get();
		if (null == findUser) {
			throw new IllegalArgumentException("사용자가 존재 하지 않습니다.");
		}
		User user = new User();
		user.setId(id);
		userRepository.delete(user);
		return "삭제 성공:" +id;
		 
	}
	@PutMapping("/dummy/user/{id}")
	@Transactional
//	save함수는 id를 전달하지 않으면 insert를 해주고
//	save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
//	save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
//
//	하지만 위에 방식은 권장되지 않습니다. @Transactional 어노테이션을 사용한 
//	더티 체킹을 해야합니다.
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id:"+ id);
		System.out.println("passwd:"+ requestUser.getPassword());
		System.out.println("email:"+ requestUser.getEmail());
		
		User user = userRepository.findById(id).get();
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		user.setUsername("김상희5");
//		userRepository.save(user);
		return user;
	}
	@GetMapping("/dummy/user")
	public List<User> pagelist(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		
		
		return users;
	}

	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// null을 리턴 하지 않게 하기위 해 Optional을 리턴 한다.
		User list = userRepository.findById(id).get();

		// 웹브라우저가 이해 할 수 있게 변환 -> json
		// 스프링부트는 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 Message Convert가 jackson라이브러리를 통해
		// user 오브젝트를 json으로 변화해서 브라우저에게 던저 줍니다.
		return list;
	}

	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료 되었습니다.";
	}
}
