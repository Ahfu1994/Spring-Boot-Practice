package com.chiradet.training.backend.service;

import com.chiradet.training.backend.entity.Address;
import com.chiradet.training.backend.entity.Social;
import com.chiradet.training.backend.entity.User;
import com.chiradet.training.backend.reposity.AddressRepository;
import com.chiradet.training.backend.reposity.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> findByUser(User user){
       return  repository.findByUser(user);
    }

    public Address create(User user, String line1, String line2, String zipcode){
        //TODO: validate
        Address entity = new Address();
        entity.setUser(user);
        entity.setLine1(line1);
        entity.setLine2(line2);
        entity.setZipcode(zipcode);

        return repository.save(entity);
    }

}
