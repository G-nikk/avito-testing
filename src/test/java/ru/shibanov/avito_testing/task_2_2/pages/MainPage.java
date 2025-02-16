package ru.shibanov.avito_testing.task_2_2.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    private final ElementsCollection items = $$x("//div[@class=\"css-1s2t5t1\"]");
    private final ElementsCollection itemsNames = $$x("//h4[@class=\"css-rkqtls\"]");
    private final ElementsCollection prices = $$x("//div[@class=\"css-1n43xc7\"]");
    private final ElementsCollection amountFilters = $$x("//div[@style=\"transform-origin: var(--popper-transform-origin); opacity: 1; visibility: visible; transform: none; will-change: auto;\"]//button");
    private final SelenideElement amountFiltersButton = $x("//div[@style=\"transform-origin: var(--popper-transform-origin); opacity: 0; visibility: hidden; transform: scale(0.8); will-change: auto;\"]");
    private final SelenideElement filtersButton = $x("//button[@class=\"chakra-button chakra-menu__menu-button css-15z6v49\"][1]");
    private final SelenideElement priceFilterButton = $x("//div[@style=\"transform-origin: var(--popper-transform-origin); opacity: 1; visibility: visible; transform: none; will-change: auto;\"]//button[text()='Цена']");
    private final SelenideElement orderFiltersButton = $x("//button[@class=\"chakra-button chakra-menu__menu-button css-15z6v49\"][2]");
    private final SelenideElement orderByIncreaseButton = $x("//button[text()=\"По возрастанию\"]");
    private final SelenideElement createButton = $x("//button[text()='Создать']");
    private final SelenideElement nameInput = $x("//input[@name=\"name\"]");
    private final SelenideElement priceInput = $x("//input[@name=\"price\"]");
    private final SelenideElement descriptionInput = $x("//input[@name=\"description\"]");
    private final SelenideElement urlInput = $x("//input[@name=\"imageUrl\"]");
    private final SelenideElement saveButton = $x("//button[text()='Сохранить']");
    private final SelenideElement searchInput = $x("//input[@placeholder=\"Поиск по объявлениям\"]");
    private final SelenideElement searchButton = $x("//button[text()='Найти']");

    public boolean checkAmountOfItemsOnPageForEachFilter() {
        for (SelenideElement filter : amountFilters) {
            openAmountFilters();
            int expectedItemsAmount = Integer.parseInt(filter.getText());
            int actualItemsAmount = countItems();
            if (actualItemsAmount != expectedItemsAmount) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPriceFilter() {
        openFilters();
        clickPriceFilterButton();
        orderFiltersButton.click();
        orderByIncreaseButton.click();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (Integer.parseInt(prices.get(i).getText().replaceAll("[^0-9\\-]", "")) > Integer.parseInt(prices.get(i + 1).getText().replaceAll("[^0-9\\-]", ""))) {
                return false;
            }
        }
        return true;
    }

    public void search(String searchText) {
        searchInput.setValue(searchText);
        searchButton.click();
    }

    public boolean checkIfItemExists(String itemName) {
        for (SelenideElement item : itemsNames) {
            if (item.getText().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void saveButtonClick() {
        saveButton.click();
    }

    public void createItemButtonClick() {
        createButton.click();
    }

    public void enterItemName(String itemName) {
        nameInput.setValue(itemName).pressEnter();
    }

    public void enterItemPrice(String itemPrice) {
        priceInput.setValue(itemPrice).pressEnter();
    }

    public void enterItemDescription(String itemDescription) {
        descriptionInput.setValue(itemDescription).pressEnter();
    }

    public void enterUrl(String url) {
        urlInput.setValue(url).pressEnter();
    }

    private void clickPriceFilterButton() {
        priceFilterButton.click();
    }

    private void openFilters() {
        filtersButton.click();
    }

    private void openAmountFilters() {
        amountFiltersButton.click();
    }

    private int countItems() {
        return items.size();
    }
}
