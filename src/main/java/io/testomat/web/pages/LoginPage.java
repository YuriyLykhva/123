package io.testomat.web.pages;

import com.codeborne.selenide.Condition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class LoginPage extends BasePage {

    public LoginPage isLoaded() {
        f("h2").shouldHave(Condition.text("Sign in"));
        return this;
    }

    public LoginPage loginUser(String mail, String password) {
        fillEmail(mail);
        fillPassword(password);
        submitLogin();
        return this;
    }

    public LoginPage loginUser(CredsWithRoles targetUser) {
        fillEmail(targetUser.mail);
        fillPassword(targetUser.password);
        submitLogin();
        return this;
    }

    public LoginPage submitLogin() {
        f("[name='commit']").click();
        return this;
    }

    public LoginPage fillPassword(String password) {
        f("#user_password").setValue(password);
        return this;
    }

    public LoginPage fillEmail(String mail) {
        f("#user_email").setValue(mail);
        return this;
    }

    @Data
    @AllArgsConstructor
    public static class Creds {
        public String mail;
        public String password;
    }

    @AllArgsConstructor
    public enum CredsWithRoles {

        MANAGER("mmax68955@gmail.com", "d#6m@$MnPzEyg7Z"),
        YUKO("yu1.0710@yopmail.com", "ZSx5EN!FHFvrubH"),
        YULU_KOTU("criyixubrica-8216@yopmail.net", "PRe8!cKa3BavZPS");

        public final String mail;
        public final String password;

    }

    //just for example
    @AllArgsConstructor
    @Getter
    public enum CredsWithRolesAnother {

        MANAGER(new Creds("mmax68955@gmail.com", "d#6m@$MnPzEyg7Z"));

        private final Creds creds;

    }
}