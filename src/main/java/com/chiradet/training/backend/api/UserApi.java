package com.chiradet.training.backend.api;

import com.chiradet.training.backend.business.UserBusiness;
import com.chiradet.training.backend.entity.User;
import com.chiradet.training.backend.exception.BaseException;
import com.chiradet.training.backend.model.MLoginRequest;
import com.chiradet.training.backend.model.MRegisterRequest;
import com.chiradet.training.backend.model.MRegisterResponse;
import com.chiradet.training.backend.model.TestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserApi {

    //Dependency injection Method1
    /*
    @Autowired
    private TestBusiness business;
     */

    //Method2
    private final UserBusiness business;

    public UserApi(UserBusiness business) {
        this.business = business;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MLoginRequest request){
        String response = business.login(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<MRegisterResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse response = business.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file){
        String response = business.uploadProfilePicture(file);
        return ResponseEntity.ok(response);
    }

}
