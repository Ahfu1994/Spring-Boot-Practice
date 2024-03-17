package com.chiradet.training.backend.mapper;

import com.chiradet.training.backend.entity.User;
import com.chiradet.training.backend.model.MRegisterResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MRegisterResponse toRegisterResponse(User user);
}
