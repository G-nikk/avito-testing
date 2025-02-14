package ru.shibanov.avito_testing.api_testing.task_2_2.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.shibanov.avito_testing.api_testing.task_2_2.pages.MainPage;

public class ItemFiltersTests extends BaseTest {

    @Test
    public void ItemsAmountFilterCheck() {
        MainPage mainPage = new MainPage();
        Assertions.assertTrue(mainPage.checkAmountOfItemsOnPageForEachFilter());
    }
}
