package ru.shibanov.avito_testing.task_2_2.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.shibanov.avito_testing.task_2_2.pages.MainPage;

public class ItemFiltersTests extends BaseTest {

    private final MainPage mainPage = new MainPage();

    @Test
    public void ItemsAmountFilterCheck() {
        Assertions.assertTrue(mainPage.checkAmountOfItemsOnPageForEachFilter());
    }

    @Test
    public void ItemsPriceFilterCheck() {
        Assertions.assertTrue(mainPage.checkPriceFilter());
    }
}
