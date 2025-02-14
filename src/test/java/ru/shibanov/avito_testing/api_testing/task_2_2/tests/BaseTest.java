package ru.shibanov.avito_testing.api_testing.task_2_2.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.webdriverLogsEnabled = true;
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
        System.setProperty("chromeoptions.args", "\"--no-sandbox\",\"--disable-dev-shm-usage\",\"--remote-debugging-port=9222\",\"--headless\"");
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
