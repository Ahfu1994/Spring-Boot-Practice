package com.chiradet.training.backend.service;

import com.chiradet.training.backend.entity.User;
import com.chiradet.training.backend.exception.UserException;
import com.chiradet.training.backend.model.MLoginRequest;
import com.chiradet.training.backend.reposity.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService{

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) { //, PasswordEncoder passwordEncoder
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public Optional<User> findByEmail(String email){
       return  repository.findByEmail(email);
    }

    public User update(User user){
        return repository.save(user);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public User updateName(String id, String name) throws UserException{
        Optional<User> opt = repository.findById(id);
        if(opt.isEmpty()){
            throw UserException.userNotFound();
        }

        User user = opt.get();
        user.setName(name);
        return repository.save(user);
    }

    public boolean matchPassword(String rqaPassword, String encodedPassword){
        return passwordEncoder.matches(rqaPassword, encodedPassword);
    }


    public User create(String email, String password, String name){

        //validate
        if(Objects.isNull(email)){
            //throw email
            throw UserException.emailNull();
        }
        if(Objects.isNull(password)){
            //throw password
            throw  UserException.passwordNull();
        }
        if(Objects.isNull(name)){
            //throw name
            throw  UserException.nameNull();
        }

        //verify
        if(repository.existsByEmail(email)){
            throw UserException.createEmailDuplicated();
        }

        //save
        User entity = new User();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setName(name);
        return repository.save(entity);
    }


}
