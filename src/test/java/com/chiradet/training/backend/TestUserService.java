package com.chiradet.training.backend;

import com.chiradet.training.backend.entity.User;
import com.chiradet.training.backend.exception.BaseException;
import com.chiradet.training.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {
	@Autowired
	private UserService userService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
		User user = userService.create(
				TestCreateData.email,
				TestCreateData.password,
				TestCreateData.name
		);

		//check not null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());

		//check equal
		Assertions.assertEquals(TestCreateData.name, user.getName());
		Assertions.assertEquals(TestCreateData.email, user.getEmail());
		boolean isMatched = userService.matchPassword(TestCreateData.password, user.getPassword());
		Assertions.assertTrue(isMatched);
	}

	@Order(2)
	@Test
	void testUpdate() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		User updateddUser = userService.updateName(user.getId(), TestUpdateData.name);
		Assertions.assertNotNull(updateddUser);
		Assertions.assertEquals(TestUpdateData.name, updateddUser.getName());
	}

	@Order(3)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		userService.deleteById(user.getId());
		Optional<User>optDelete = userService.findByEmail(TestCreateData.email);
		Assertions.assertTrue(optDelete.isEmpty());
	}

	interface TestCreateData{
		String email = "chiradet@email.com";
		String password = "chiradet123";
		String name = "chiradet";
	}

	interface TestUpdateData{
//		String email = "chiradetkh123@email.com";
//		String password = "chiradet123";
		String name = "Khositanon";
	}
}
