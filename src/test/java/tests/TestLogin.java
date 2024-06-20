package tests;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pageobjects.Login;
import tests.groups.Deep;
import tests.groups.Shallow;

@Category(Deep.class)
public class TestLogin extends BaseTest {
    private Login login;

    @Before
    public void setUp() throws Exception {
        login = new Login(driver);
    }

    @Test
    @Category(Shallow.class)
    public void succeeded() {
        login.with("tomsmith","SuperSecretPassword!");
        Assert.assertTrue(login.successLoginMessage());
    }
    @Test
    @Category(Shallow.class)
    public void failed() {
        login.with("tomsmith","SuperSecretPassword");
        Assert.assertTrue(login.failureLoginMessage());
    }
    @Test
    @Category(Shallow.class)
    public void forcedFailure() {
        login.with("tomsmith","SuperSecretPassword");
        Assert.assertTrue(false);
    }
}
