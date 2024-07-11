import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DragOnDropTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;
    }

    @Test
    void  dragAndDropTestAction() {
        open("/drag_and_drop");
        SelenideElement cubeA = $("#column-a");
        SelenideElement cubeB = $("#column-b");
        WebDriver driver = getWebDriver();
        Actions actions = new Actions(driver);
        actions.clickAndHold(cubeA)
                .moveToElement(cubeB)
                .release()
                .perform();
        $("#column-b").shouldHave(text("A"));
    }
    @Test
    void  dragAndDropTest() {
        open("/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        $("#column-a").dragAndDrop(DragAndDropOptions.to($("#column-b")));
        $("#column-b").shouldHave(text("A"));
    }
}
