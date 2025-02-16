package ru.shibanov.avito_testing.task_2_2.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class OrderPage {
    private final ElementsCollection ordersSumCollection = $$x("//p[@class='chakra-text css-0']");
    private final ElementsCollection itemsPriceCollection = $$x("//p[contains(text(),\"Цена\")]");
    private final ElementsCollection showItemsButtonCollection = $$x("//button[contains(text(),\"Показать\")]");
    private final SelenideElement ordersButton = $x("//div[contains(text(),\"Заказы\")]");

    public OrderPage(){}

    public void goToOrdersPage() {
        ordersButton.click();
    }

    public void showItems() {
        showItemsButtonCollection.stream().forEach(SelenideElement::click);
    }

    public int getOrdersSum() {
        return ordersSumCollection.stream()
                .map(SelenideElement::getText)
                .map(text -> text.replaceAll("[^0-9]", ""))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }

    public int getItemsSum() {
        return itemsPriceCollection.stream()
                .map(SelenideElement::getText)
                .map(text -> text.replaceAll("[^0-9]", ""))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
