package com.yunsseong.ai_ex_server.auth.dto;

public record SignUpRequest(String nickname, String email, String password1, String password2) {
}
