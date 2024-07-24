package api;


import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import Data.User;


import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String AUTH_HEADER = "Authorization";

    @Step("Регистрация пользователя по ручке")
    public Response createUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(user)
                .when()
                .post("/auth/register");
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return RestAssured
                .given()
                .header("Content-Type", "json")
                .header("Authorization", accessToken)
                .when()
                .delete("/auth/user");
    }


}