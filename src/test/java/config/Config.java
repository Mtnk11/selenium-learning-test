package config;

public class Config {
    public static final String baseUrl = System.getProperty("baseUrl", "https://the-internet.herokuapp.com");
    public static final String browserName = System.getProperty("browserName", "chrome");
    public static final String host = System.getProperty("host", "localhost");
    public static final String browserVersion = System.getProperty("browserVersion", "latest");
    public static final String saucelabsUser = System.getenv("oauth-madinatawkenbai-63d8b");
    public static final String saucelabsPassword = System.getenv("1f162546-5940-4a11-802b-414b4a6a5695");
    public static final String platformName = System.getProperty("platformName","Linux");
}
