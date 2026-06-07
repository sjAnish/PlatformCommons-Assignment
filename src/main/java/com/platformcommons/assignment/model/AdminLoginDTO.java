package com.platformcommons.assignment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminLoginDTO {

    private String username;
    private String password;
}