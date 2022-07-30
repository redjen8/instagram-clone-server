package com.redjen.instagram.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long memberId;

    private String id;
    private String password;

    @Column(name="phone_number")
    private String phoneNumber;
    private String name;

    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="profile_img_url")
    private String profileImageUrl;

    @Column(name="is_active_flag")
    private Boolean isActive;

    @Column(name="is_public_flag")
    private Boolean isPublic;

    @Column(name="is_social_flag")
    private Boolean isSSO;

    @Column(name="refresh_token")
    private String refreshToken;
}
