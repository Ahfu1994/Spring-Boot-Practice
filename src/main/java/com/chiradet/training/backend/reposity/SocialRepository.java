package com.chiradet.training.backend.reposity;

import com.chiradet.training.backend.entity.Social;
import com.chiradet.training.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SocialRepository extends CrudRepository<Social, String> {

    Optional<Social> findByUser(User user);



}
