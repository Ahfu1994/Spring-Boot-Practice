package com.chiradet.training.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name="m_social")
public class Social extends BaseEntity{

    @Column(name="facebook" , length = 120)
    private String facebook;

    @Column(name="line", length = 120)
    private String line;

    @Column(name="instagram", length = 120)
    private String instagram;

    @Column(name="tiktok", length = 120)
    private String tiktok;

    @OneToOne
    @JoinColumn(name = "m_user_id")
    private User user;

}
