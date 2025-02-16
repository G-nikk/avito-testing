package ru.shibanov.avito_testing.task_2_2.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.shibanov.avito_testing.task_2_2.pages.MainPage;

public class ItemCreationTest extends BaseTest {
    private final String itemName = "Мухаааааа";
    private final String itemPrice = "13";
    private final String itemDescription = "жжжжжж";
    private final String url = "https://i.pinimg.com/736x/48/d9/45/48d945c327fbcf97ceca93959545b2e3--keeping-flies-away-fly-reels.jpg";
    private final MainPage mainPage = new MainPage();

    @Test
    public void createItemTest() {
        mainPage.createItemButtonClick();
        mainPage.enterItemName(itemName);
        mainPage.enterItemPrice(itemPrice);
        mainPage.enterItemDescription(itemDescription);
        mainPage.enterUrl(url);
        mainPage.saveButtonClick();
        mainPage.search(itemName);
        Selenide.sleep(1000);
        Assertions.assertTrue(mainPage.checkIfItemExists(itemName));
    }
}
