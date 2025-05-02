package com.yunsseong.ai_ex_server.member.application.dto;

public record SignUpRequest(String nickname, String email, String password1, String password2) {
}
