import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDelivery {

    @Test
    void test2() {
        open("http://localhost:7777");
        $("input[placeholder='Город']").setValue("Мо");
        SelenideElement element = $$("div>[class='popup__inner']>[class='popup__content']").last();
        element.$(byText("Москва")).click();
        $("input[name='name']").setValue("Ринат Ринатов");
        $("input[placeholder='Дата встречи']").setValue(getFutureDate(3));
        $("input[placeholder='Дата встречи']~[class]").click();
        $("input[name='phone']").setValue("+79100000000");
        $(".checkbox__text").click();
        $(".button__icon~.button__text").click();
        $(withText("Успешн")).waitUntil(Condition.visible, 11000);
    }

    SelenideElement form = $("form");
    SelenideElement city = form.$("input[placeholder='Город'");
    SelenideElement date = form.$("input[placeholder='Дата встречи']");
    SelenideElement name = form.$("input[name='name']");
    SelenideElement phone = form.$("input[name='phone']");
    SelenideElement checkMark = form.$(".checkbox__text");
    SelenideElement button = $$(".button__icon~.button__text").find(exactText("Забронировать"));
    SelenideElement notification = $(withText("Успешн"));
   // private Condition visible;

    private String getFutureDate(int plusDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        LocalDate currentDate = LocalDate.now();
        LocalDate controlDate = currentDate.plusDays(plusDate);
        String formattedControlDate = controlDate.format(format);
        return formattedControlDate;
    }



    @BeforeEach
    void openLocalhost() {
        open("http://localhost:7777");
    }

    @Test
    void shouldTest() {
        city.setValue("Москва");
        date.doubleClick().sendKeys(Keys.BACK_SPACE);
        date.setValue(getFutureDate(3));
        name.setValue("Ринат Ринатов");
        phone.setValue("+79134567890");
        checkMark.click();
        button.click();
        notification.waitUntil(exist, 15000);
    }
}





