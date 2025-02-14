package ru.shibanov.avito_testing.api_testing.task_2_2.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final ElementsCollection items = $$x("//div[@class=\"css-1s2t5t1\"]");
    private final ElementsCollection filters = $$x("//div[@style=\"transform-origin: var(--popper-transform-origin); opacity: 1; visibility: visible; transform: none; will-change: auto;\"]//button");
    private final SelenideElement openFiltersButton = $x("//div[@style=\"transform-origin: var(--popper-transform-origin); opacity: 0; visibility: hidden; transform: scale(0.8); will-change: auto;\"]");

    public int countItems() {
        return items.size();
    }

    public boolean checkAmountOfItemsOnPage() {
        for (SelenideElement filter : filters) {
            filter.click();
            int expectedItemsAmount = Integer.parseInt(filter.getText());
            int actualItemsAmount = countItems();
            if (actualItemsAmount != expectedItemsAmount) {
                return false;
            }
        }
        return true;
    }

    public void openFilters() {
        openFiltersButton.click();
    }
}
