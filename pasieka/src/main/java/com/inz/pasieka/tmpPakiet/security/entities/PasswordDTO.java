package com.inz.pasieka.tmpPakiet.security.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDTO {
    private String oldPassword;
    private String newPassword;
}
