package com.chiradet.training.backend.business;

import com.chiradet.training.backend.entity.User;
import com.chiradet.training.backend.exception.BaseException;
import com.chiradet.training.backend.exception.FileException;
import com.chiradet.training.backend.exception.UserException;
import com.chiradet.training.backend.mapper.UserMapper;
import com.chiradet.training.backend.model.MLoginRequest;
import com.chiradet.training.backend.model.MRegisterRequest;
import com.chiradet.training.backend.model.MRegisterResponse;
import com.chiradet.training.backend.service.TokenService;
import com.chiradet.training.backend.service.UserService;
import com.chiradet.training.backend.util.SecurityUtil;
import org.antlr.v4.runtime.Token;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserBusiness {

    private final UserService userService;

    private final TokenService tokenService;
    private final UserMapper userMapper;


    public UserBusiness(UserService userService, TokenService tokenService, UserMapper userMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    public String login(MLoginRequest request){
        //validate request

        //verrify database
        Optional<User> opt = userService.findByEmail(request.getEmail());
        if(opt.isEmpty()){
            //throw login fail, email not found
            throw UserException.loginFailEmailNotFound();
        }

        User user = opt.get();
        if(!userService.matchPassword(request.getPassword(),user.getPassword())){
            //throw login fail , password incorrect
            throw UserException.loginFailPasswordIncorrect();
        }

        //TODO generate JWT
        String token = tokenService.tokenize(user);

        return token;

    }


    public MRegisterResponse register(MRegisterRequest request) throws BaseException {

        User user = userService.create(request.getEmail(), request.getPassword(), request.getName());

        //TODO mapper
        return userMapper.toRegisterResponse(user);
    }


    public String uploadProfilePicture(MultipartFile file) throws FileException {
        if (null == file) {
            //throw error
            throw FileException.fileNull();
        }

        if (file.getSize() > 1048576 * 2) {
            //throw error
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (null == contentType) {
            //throw error
            throw FileException.unsupported();
        }

        List<String> supportedTypes = Arrays.asList("image/jpeg", "image/png");
        if (!supportedTypes.contains(contentType)) {
            //throw error
            throw FileException.unsupported();
        }

        //TODO upload file Storage (AWS S3, etc ...)
        try {
            byte[] bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public String refreshToken() throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if (opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String userId = opt.get();

        Optional<User> optUser = userService.findById(userId);
        if (optUser.isEmpty()) {
            throw UserException.notFound();
        }

        User user = optUser.get();
        return tokenService.tokenize(user);
    }
}
