package com.chiradet.training.backend.service;

import com.chiradet.training.backend.entity.Social;
import com.chiradet.training.backend.entity.User;
import com.chiradet.training.backend.exception.UserException;
import com.chiradet.training.backend.reposity.SocialRepository;
import com.chiradet.training.backend.reposity.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SocialService {

    private final SocialRepository repository;
    public SocialService(SocialRepository repository) {
        this.repository = repository;
    }

    public Optional<Social> findByUser(User user){
       return  repository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram, String tiktok){
        //TODO: validate
        Social entity = new Social();
        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);
        entity.setTiktok(tiktok);

        return repository.save(entity);
    }

}
