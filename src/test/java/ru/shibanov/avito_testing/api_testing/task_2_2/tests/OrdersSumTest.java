package ru.shibanov.avito_testing.api_testing.task_2_2.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.shibanov.avito_testing.api_testing.task_2_2.pages.OrderPage;

public class OrdersSumTest extends BaseTest {

    @Test
    public void CorrectOrdersSumCheck() {
        OrderPage orderPage = new OrderPage();
        orderPage.goToOrdersPage();
        orderPage.showItems();
        int itemsSum = orderPage.getItemsSum();
        int ordersSum = orderPage.getOrdersSum();
        Assertions.assertEquals(ordersSum, itemsSum);
    }
}
