package il.ac.shenkar.java.costmanager.domain.util;

import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;

public class ConfigurationManager {
    private String[] starterCategoryNames;
    private String[] supportedCurrencies;

    public ConfigurationManager() {
        Properties properties = new Properties();

        try (InputStream inputStream = ConfigurationManager.class.getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);

                String starterCategoryNamesProperty = properties.getProperty("starterCategoryNames");
                String supportedCurrenciesProperty = properties.getProperty("supportedCurrencies");

                // Split the comma-separated values into arrays
                this.starterCategoryNames = starterCategoryNamesProperty.split(",");
                this.supportedCurrencies = supportedCurrenciesProperty.split(",");
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
}

