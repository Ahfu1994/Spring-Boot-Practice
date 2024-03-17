package com.chiradet.training.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name="m_address")
public class Address extends BaseEntity{

    @Column(name="line1" , length = 120)
    private String line1;

    @Column(name="line2", length = 120)
    private String line2;

    @Column(name="zipcode", length = 120)
    private String zipcode;

    @ManyToOne
    @JoinColumn(name="m_user_id", nullable = false)
    private User user;


}
