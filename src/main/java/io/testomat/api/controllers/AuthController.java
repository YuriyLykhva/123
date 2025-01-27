package io.testomat.api.controllers;

import io.restassured.http.ContentType;
import io.testomat.api.dtos.LoginDto;

public class AuthController extends BaseController<AuthController> {
    public LoginDto loginUser(String login, String password) {
        return baseClient()
                .contentType(ContentType.URLENC)
                .formParams(
                        "email", "criyixubrica-8216@yopmail.net",
                        "password", "PRe8!cKa3BavZPS"
                )
                .post("/login")
                .as(LoginDto.class);

    }
}
