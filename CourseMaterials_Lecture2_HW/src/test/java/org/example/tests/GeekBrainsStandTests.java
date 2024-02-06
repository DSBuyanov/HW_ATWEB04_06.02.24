package org.example.tests;


import org.example.pom.LoginPage;
import org.example.pom.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.qameta.allure.*;
import org.openqa.selenium.By;


public class GeekBrainsStandTests {

    private LoginPage loginPage;
    private MainPage mainPage;

    private static String USERNAME;
    private static String PASSWORD;

    @BeforeAll
    public static void setupClass() {
        USERNAME = "GB202306611b511";
        PASSWORD = "2ee4e216d5";
        }

    @BeforeEach
    public void setupTest() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "121";
        Configuration.headless = false;

        open("https://test-stand.gb.ru/login");

        loginPage = new LoginPage();
        mainPage = new MainPage();
    }

    @Test
    @Epic("Авторизация")
    @Feature("Логин")
    @Story("Пустые поля логина")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка логина с пустыми полями. Ожидается ошибка '401 Invalid credentials.'")
    public void testLoginWithEmptyFields() {

        loginPage.clickLoginButton();
        assertEquals("401 Invalid credentials.", loginPage.getErrorBlockText());
    }

    @Test
    @Epic("Управление группами")
    @Feature("Создание группы")
    @Story("Создание новой группы")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка создания новой группы и отображения ее в списке групп.")
    public void testAddingGroupOnMainPage() {

        loginPage.login(USERNAME, PASSWORD);
        mainPage = new MainPage();
        assertTrue(mainPage.getUsernameLabelText().contains(USERNAME));

        String groupTestName = "New Test Group " + System.currentTimeMillis();
        mainPage.createGroup(groupTestName);
        mainPage.firstGroupNameCell.shouldHave(text(groupTestName));
    }

    @Test
    @Epic("Профиль пользователя")
    @Feature("Редактирование профиля")
    @Story("Изменение даты рождения")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тестирование функционала изменения даты рождения в профиле пользователя.")
    public void testProfileBirthdateEditing() {
        loginPage.login(USERNAME, PASSWORD);
        mainPage = new MainPage();
        assertTrue(mainPage.getUsernameLabelText().contains(USERNAME));

        // Навигация на страницу профиля пользователя
        $(By.xpath("//*[@id='app']/main/nav/ul/li[3]/a")).click();
        // Открыть модальное окно Editing
        $(By.xpath("//*[@id='app']/main/nav/ul/li[3]/div/ul/li[1]/span[2]")).click();
        // Открыть форму редактирования даты рождения
        $(By.xpath("//*[@id='app']/main/div/div/div[1]/div/div[1]/div/div/div/div[3]/div/button[2]")).click();
        // Изменить значение даты рождения
        String script = "document.querySelector('input[type=\"date\"]').value='1992-01-02';";
        executeJavaScript(script);
        // Нажать на кнопку Save и закрыть модальное окно
        $(By.xpath("//*[@id='update-item']/div[9]/button/span")).click();
        // Закрыть модальное окно редактирования профиля
        $(By.xpath("//*[@id='app']/main/div/div/div[2]/div[2]/div/div[1]/button")).click();
        // Проверить, что изменения применились в поле Date of Birth в секции Additional Info
        $(By.xpath("//*[@id='app']/main/div/div/div[1]/div/div[2]/div/div[2]/div[2]"))
                .shouldHave(text("01.02.1992"));
    }

    @AfterEach
    public void teardown() {
        closeWebDriver();
    }

}

