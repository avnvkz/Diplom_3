package site.nomoreparties.stellarburgers.model.user;

import java.util.Locale;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public static User getRandom() {
        Faker faker = Faker.instance(new Locale("en-GB"));
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String name = faker.name().username();
        return new User(email, password, name);
    }

    public static String getRandomInvalidPassword() {
        String password = RandomStringUtils.randomAlphanumeric(5).toString();
        return password;
    }
}
