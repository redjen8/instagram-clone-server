package com.redjen.instagram.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class MemberRegisterParam {

    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @Past
    private Date birthDate;
    private String phoneNumber;
}
