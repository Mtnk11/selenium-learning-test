package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.DynamicLoading;

public class TestDynamicLoading extends BaseTest{
    DynamicLoading dynamicLoading;
    @Before
    public void setUp() {
        dynamicLoading = new DynamicLoading(driver);
    }

    @Test
    public void hiddenElementsLoads() {
        dynamicLoading.loadExample("1");
        Assert.assertTrue(dynamicLoading.finishTextPresent());
    }
    @Test
    public void elementAppears() {
        dynamicLoading.loadExample("2");
        Assert.assertTrue(dynamicLoading.finishTextPresent());
    }
}
