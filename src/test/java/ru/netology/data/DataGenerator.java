package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import lombok.Value;
import ru.netology.data.RegistrationDto;

import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

@Data

public class DataGenerator {
    public DataGenerator() {

    }

    private static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void setUpAll (RegistrationDto registrationDto){
        given()
                .spec(requestSpecification)
                .body(registrationDto)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);

    }
        public static RegistrationDto activeValidUser (){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName().toLowerCase();
        String password = faker.internet().password();
        String status = "active";
        RegistrationDto registrationDto = new RegistrationDto(login, password, status);
        setUpAll(registrationDto);
        return registrationDto;
    }
    public static RegistrationDto blockedValidUser (){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName().toLowerCase();
        String password = faker.internet().password();
        String status = "blocked";
        RegistrationDto registrationDto = new RegistrationDto(login, password, status);
        setUpAll(registrationDto);
        return registrationDto;
    }

    public static RegistrationDto incorrectLogin (){
        Faker faker = new Faker(new Locale("en"));
        String login = "vasya";
        String password = faker.internet().password();
        String status = "active";
        RegistrationDto registrationDto = new RegistrationDto(login, password, status);
        setUpAll(registrationDto);
        return new RegistrationDto ("abrvalg", password, status);
    }

    public static RegistrationDto incorrectPassword (){
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName().toLowerCase();
        String password = "password";
        String status = "active";
        RegistrationDto registrationDto = new RegistrationDto(login, password, status);
        setUpAll(registrationDto);
        return new RegistrationDto (login, "12345", status);
    }


        }