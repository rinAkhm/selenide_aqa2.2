import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDelivery {

    SelenideElement form = $("form");
    SelenideElement city = form.$("input[placeholder='Город'");
    SelenideElement date = form.$("input[placeholder='Дата встречи']");
    SelenideElement name = form.$("input[name='name']");
    SelenideElement phone = form.$("input[name='phone']");
    SelenideElement checkMark = form.$(".checkbox__text");
    SelenideElement button = $$(".button__icon~.button__text").find(exactText("Забронировать"));
    SelenideElement notification = $(withText("Успешн"));

    private String getFutureDate(int plusDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        LocalDate currentDate = LocalDate.now();
        LocalDate controlDate = currentDate.plusDays(plusDate);
        String formattedControlDate = controlDate.format(format);
        return formattedControlDate;
    }


    @BeforeEach
    void openLocalhost() {
        open("http://localhost:9999");
    }

    @Test
    void shouldReservationCard() {
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







