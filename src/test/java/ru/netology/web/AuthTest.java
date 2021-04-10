package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.RegistrationDto;
import ru.netology.data.DataGenerator;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class AuthTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test

    void ShouldAuthorizeForValidUser (){
        RegistrationDto activeValidUser = DataGenerator.activeValidUser();
        $("[data-test-id='login'] input").setValue(activeValidUser.getLogin());
        $("[data-test-id='password'] input").setValue(activeValidUser.getPassword());
        $("[data-test-id='action-login']").click();
        $(".heading").shouldHave(text("кабинет"));
    }
        @Test
    void ShouldNotAuthorizeForBlockedUser (){
        RegistrationDto blockedValidUser = DataGenerator.blockedValidUser();
        $("[data-test-id='login'] input").setValue(blockedValidUser.getLogin());
        $("[data-test-id='password'] input").setValue(blockedValidUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(text("Пользователь заблокирован"));

    }


    @Test
    void ShouldNotAuthorizeForIncorrectLogin(){
        RegistrationDto incorrectLogin = DataGenerator.incorrectLogin();
        $("[data-test-id='login'] input").setValue(incorrectLogin.getLogin());
        $("[data-test-id='password'] input").setValue(incorrectLogin.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void ShouldNotAuthorizeForIncorrectPassword(){
        RegistrationDto incorrectPassword = DataGenerator.incorrectPassword();
        $("[data-test-id='login'] input").setValue(incorrectPassword.getLogin());
        $("[data-test-id='password'] input").setValue(incorrectPassword.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldHave(text("Неверно указан логин или пароль"));
    }


    }





