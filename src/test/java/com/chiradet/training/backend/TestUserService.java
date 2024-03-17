package com.chiradet.training.backend;

import com.chiradet.training.backend.entity.Address;
import com.chiradet.training.backend.entity.Social;
import com.chiradet.training.backend.entity.User;
import com.chiradet.training.backend.exception.BaseException;
import com.chiradet.training.backend.service.AddressService;
import com.chiradet.training.backend.service.SocialService;
import com.chiradet.training.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {
    @Autowired
    private UserService userService;
    @Autowired
    private SocialService socialService;
    @Autowired
    private AddressService addressService;


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
    void testCreateSocial() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();

        Social social = user.getSocial();
        Assertions.assertNull(social);

        social = socialService.create(user,
                SocialTestCreateData.facebook,
                SocialTestCreateData.line,
                SocialTestCreateData.instagram,
                SocialTestCreateData.tiktok);


        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());
        Assertions.assertEquals(SocialTestCreateData.line, social.getLine());
        Assertions.assertEquals(SocialTestCreateData.tiktok, social.getTiktok());
        Assertions.assertEquals(SocialTestCreateData.instagram, social.getInstagram());

    }

    @Order(3)
    @Test
    void testCreateAddress() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();
        List<Address> addresses = user.getAddress();
        Assertions.assertTrue(addresses.isEmpty());

        createAddress(user, AddressTestCreateData.line1, AddressTestCreateData.line2, AddressTestCreateData.zipcode);
        createAddress(user, AddressTestCreateData1.line1, AddressTestCreateData1.line2, AddressTestCreateData1.zipcode);
        createAddress(user, AddressTestCreateData2.line1, AddressTestCreateData2.line2, AddressTestCreateData2.zipcode);
        createAddress(user, AddressTestCreateData3.line1, AddressTestCreateData3.line2, AddressTestCreateData3.zipcode);

    }

    private void createAddress(User user , String line1, String line2, String zipcode){
        Address address = addressService.create(user, line1, line2, zipcode);
        Assertions.assertNotNull(address);
        Assertions.assertEquals(line1, address.getLine1());
        Assertions.assertEquals(line2,  address.getLine2());
        Assertions.assertEquals(zipcode, address.getZipcode());
    }

    @Order(4)
    @Test
    void testDelete() {
        Optional<User> opt = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();

        //check social
        Social social = user.getSocial();
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreateData.facebook, social.getFacebook());
        //check address
        List<Address> addresses = user.getAddress();
        Assertions.assertFalse(addresses.isEmpty());
        Assertions.assertEquals(4, addresses.size());

        userService.deleteById(user.getId());
        Optional<User> optDelete = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(optDelete.isEmpty());
    }

    interface SocialTestCreateData {
        String facebook = "chiradet_facebook";
        String line = "chiradet_line";
        String tiktok = "chiradet_tiktok";
        String instagram = "chiradet_instagram";
    }

    interface TestCreateData {
        String email = "chiradet@email.com";
        String password = "chiradet123";
        String name = "chiradet";
    }

    interface TestUpdateData {
        //		String email = "chiradetkh123@email.com";
//		String password = "chiradet123";
        String name = "Khositanon";
    }

    interface AddressTestCreateData{
        String line1 = "line1";
        String line2 = "line2";
        String zipcode = "67000";
    }

    interface AddressTestCreateData3{
        String line1 = "line1";
        String line2 = "line2";
        String zipcode = "67000";
    }

    interface AddressTestCreateData1{
        String line1 = "line1";
        String line2 = "line2";
        String zipcode = "67000";
    }

    interface AddressTestCreateData2{
        String line1 = "line1";
        String line2 = "line2";
        String zipcode = "67000";
    }


}
