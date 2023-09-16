package il.ac.shenkar.java.costmanager.domain.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The `ConfigurationManager` class manages configuration settings for the application.
 */
public class ConfigurationManager {
    private String[] starterCategoryNames;
    private String[] supportedCurrencies;
    private String jdbcURL;

    /**
     * Constructs a new `ConfigurationManager` and loads configuration settings from a properties file.
     */
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

    /**
     * Get the starter category names.
     *
     * @return An array of starter category names.
     */
    public String[] getStarterCategoryNames() {
        return starterCategoryNames;
    }

    /**
     * Set the starter category names.
     *
     * @param starterCategoryNames An array of starter category names.
     */
    public void setStarterCategoryNames(String[] starterCategoryNames) {
        this.starterCategoryNames = starterCategoryNames;
    }

    /**
     * Get the supported currencies.
     *
     * @return An array of supported currencies.
     */
    public String[] getSupportedCurrencies() {
        return supportedCurrencies;
    }

    /**
     * Set the supported currencies.
     *
     * @param supportedCurrencies An array of supported currencies.
     */
    public void setSupportedCurrencies(String[] supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    /**
     * Get the JDBC URL for database connection.
     *
     * @return The JDBC URL.
     */
    public String getJdbcURL() {
        return jdbcURL;
    }

    /**
     * Set the JDBC URL for database connection.
     *
     * @param jdbcURL The JDBC URL.
     */
    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }
}
