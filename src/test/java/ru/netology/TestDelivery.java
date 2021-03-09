package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class TestDelivery {

    LocalDate date = LocalDate.now();
    LocalDate newDays = date.plusDays(4);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Test
    void shouldNewDelivery() {
        Configuration.headless=true;
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Уфа").click();
        $(("[placeholder='Дата встречи']")).sendKeys("\b\b\b\b\b\b\b\b");
        $("[placeholder='Дата встречи']").sendKeys(format.format(newDays));
        $("[name='name']").setValue("Иванов Павел");
        $("[name='phone']").setValue("+79122593411");
        $("[class = 'checkbox__box']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(exist);
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(14));

    }
}
