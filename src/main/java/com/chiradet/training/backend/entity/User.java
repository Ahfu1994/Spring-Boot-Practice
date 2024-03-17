package com.chiradet.training.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name="m_user")
public class User extends BaseEntity{

    @Column(name="email", nullable = false, unique = true, length = 60)
    private String email;

//    @JsonIgnore
    @Column(name="password",nullable = false, length = 120)
    private String password;

    @Column(name="name",nullable = false, length = 120)
    private String name;


    private String civilId;

}
