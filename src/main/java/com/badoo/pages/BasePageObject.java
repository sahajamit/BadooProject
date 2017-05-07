package com.badoo.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by amit.rawat on 08/12/15.
 */
public class BasePageObject {
    public static WebDriver Driver;
    protected final WebDriver driver;
    public BasePageObject() {
        // TODO Auto-generated constructor stub
        this(Driver);
    }

    public BasePageObject(WebDriver driver2) {
        // TODO Auto-generated constructor stub
        this.driver = driver2;
    }

    public void throwIFPageTitleDoesnotContain(String... titleFragments) {
        String actualTitle = driver.getTitle();

        for (String titleFragment : titleFragments) {

            if (!actualTitle.contains(titleFragment)) {
                throw new IllegalStateException(
                        "incorrect page: expected to contain <" + titleFragment
                                + ">" + " but was: <" + actualTitle + ">");
            }
        }

    }

}

