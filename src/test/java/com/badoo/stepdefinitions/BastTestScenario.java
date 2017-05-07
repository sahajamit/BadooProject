package com.badoo.stepdefinitions;

import org.junit.BeforeClass;
import org.testng.annotations.*;

/**
 * Created by amit.rawat on 09/12/15.
 */
public class BastTestScenario {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("test");
    }
    @BeforeClass
    public void beforClass(){
        System.out.println("test");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("test");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("test");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("test");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("test");
    }
}
