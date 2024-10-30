package com.techradar.polls.techradar_polls.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationInfo {
    private long userId;
    private String role;
}
