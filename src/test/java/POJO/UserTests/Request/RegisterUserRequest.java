package POJO.UserTests.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

public class RegisterUserRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("password_confirmation")
    private String passwordConfirmation;

    //konstruktor nije neophodan jer imamo getere i setere
//    public RegisterUserRequest (String name, String email, String password, String passwordConfirmation) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.passwordConfirmation = passwordConfirmation;
//    }

    public String getName () {
        return name;
    }
    public RegisterUserRequest setName (String name) {   //FLUIDAN SETTER, moze jedan na drugi da se nastavlja
        this.name = name;

        return this;
    }

    public String getEmail () {
        return email;
    }

    public RegisterUserRequest setEmail (String email) {
        this.email = email;

        return this;
    }

    public String getPassword () {
        return password;
    }

    public RegisterUserRequest setPassword (String password) {
        this.password = password;

        return this;
    }

    public String getPasswordConfirmation (String passwordConfirmation) {
        return passwordConfirmation;
    }

   public RegisterUserRequest setPasswordConfirmation (String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;

        return this;
   }

   public static RegisterUserRequest fake () {
        Faker faker = new Faker();
        RegisterUserRequest user = new RegisterUserRequest();

        user
                .setName(faker.name().fullName())
                .setEmail(faker.internet().safeEmailAddress())
                .setPassword(faker.internet().password())
                .setPasswordConfirmation(user.getPassword());

        return user;
   }

}
