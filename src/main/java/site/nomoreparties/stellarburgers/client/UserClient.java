package site.nomoreparties.stellarburgers.client;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.model.user.User;
import site.nomoreparties.stellarburgers.model.user.UserCredentials;
import site.nomoreparties.stellarburgers.client.base.StellarBurgerRestClient;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;
import io.qameta.allure.Step;

public class UserClient extends StellarBurgerRestClient {

    private static final String USER_URI = BASE_URI + "/auth";

    @Step("Создание пользователя")
    public String createUser(User user) {
        return given()
                .spec(getBaseReqSpec())
                .body(user)
                .when()
                .post(USER_URI + "/register")
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .assertThat()
                .body("success", is(true))
                .extract().path("accessToken");
    }

    @Step("Авторизация пользователя")
    public String loginUser(UserCredentials userCredentials) {
        return given()
                .spec(getBaseReqSpec())
                .body(userCredentials)
                .when()
                .post(USER_URI + "/login")
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .assertThat()
                .body("success", is(true))
                .extract().path("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
            given()
                .spec(getBaseReqSpec())
                .header("authorization", accessToken)
                .when()
                .delete(USER_URI + "/user")
                .then()
                .assertThat()
                .statusCode(SC_ACCEPTED)
                .and()
                .body("success", is(true))
                .body("message", is("User successfully removed"));
    }
}
