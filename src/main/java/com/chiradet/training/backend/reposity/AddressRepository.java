package com.chiradet.training.backend.reposity;

import com.chiradet.training.backend.entity.Address;
import com.chiradet.training.backend.entity.Social;
import com.chiradet.training.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, String> {

    List<Address> findByUser(User user);



}
