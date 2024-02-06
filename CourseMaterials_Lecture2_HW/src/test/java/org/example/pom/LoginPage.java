package org.example.pom;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;

public class LoginPage {

    private SelenideElement usernameField = $("form#login input[type='text']");
    private SelenideElement passwordField = $("form#login input[type='password']");
    private SelenideElement loginButton = $("form#login button");
    private SelenideElement errorBlock = $("div.error-block");

    public LoginPage() {

    }

    public void login(String username, String password) {
        typeUsernameInField("GB202306611b511");
        typePasswordInField("2ee4e216d5");
        clickLoginButton();
    }

    public void typeUsernameInField(String username) {
        usernameField.shouldBe(visible).setValue(username);
    }

    public void typePasswordInField(String password) {
        passwordField.shouldBe(visible).setValue(password);
    }

    public void clickLoginButton() {
        loginButton.shouldBe(visible).click();
    }

    public String getErrorBlockText() {
        return errorBlock.shouldBe(visible).getText().replace("\n", " ");
    }

}

