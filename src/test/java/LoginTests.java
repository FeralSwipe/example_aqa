import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTests {
    @Test
    void successfulLoginTest() {
        open("https://login.qa.studio/");
        $("[name=mail]").setValue("german@dolnikov.ru");
        $("[name=pass]").setValue("iLoveqastudio1").pressEnter();

        $("[class=message").shouldHave(text("Авторизация прошла успешно"));
    }

    @Test
    void negativeLoginTest() {
        open("https://login.qa.studio/");
        $("[name=mail]").setValue("germa@dolnikov.ru");
        $("[name=pass]").setValue("iLoveqastudio").pressEnter();

        $("[class=message").shouldHave(text("Такого логина или пароля нет"));
    }


    @Test
    void noDogLoginTest() {
        open("https://login.qa.studio/");
        $("[name=mail]").setValue("germadolnikov.ru");
        $("[name=pass]").setValue("iLoveqastudio").pressEnter();

        $("[class=message").shouldHave(text("Нужно исправить проблему валидации"));

    }
    @Test
    void emailLoginTest() {
        open("https://login.qa.studio/");

        $(byText("Забыли пароль?")).click();
        $("#mailForgot").setValue("test@example.com");
        $(byText("Отправить код")).click();

        $("[class=message").shouldHave(text("Успешно отправили пароль на e-mail"));


    }

    @Test
    void notemailLoginTest() {
        open("https://login.qa.studio/");

        $(byText("Забыли пароль?")).click();
        $("#mailForgot").setValue("testexample.com");
        $(byText("Отправить код")).click();

        $("[class=message").shouldHave(text("Нужно исправить проблему валидации"));


    }

}