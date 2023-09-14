package il.ac.shenkar.java.costmanager.domain.util;

import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public class ConfigurationManager {
    private String[] starterCategoryNames;
    private String[] supportedCurrencies;
    private String jdbcURL;


    public ConfigurationManager() {
        Properties properties = new Properties();

        try (InputStream inputStream = ConfigurationManager.class.getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);

                String starterCategoryNamesProperty = properties.getProperty("starterCategoryNames");
                String supportedCurrenciesProperty = properties.getProperty("supportedCurrencies");

                setJdbcURL(properties.getProperty("jdbc.url"));
                // Split the comma-separated values into arrays
                setStarterCategoryNames(starterCategoryNamesProperty.split(","));
                setSupportedCurrencies(supportedCurrenciesProperty.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getStarterCategoryNames() {
        return starterCategoryNames;
    }

    public void setStarterCategoryNames(String[] starterCategoryNames) {
        this.starterCategoryNames = starterCategoryNames;
    }

    public String[] getSupportedCurrencies() {
        return supportedCurrencies;
    }

    public void setSupportedCurrencies(String[] supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }
}

