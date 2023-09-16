package il.ac.shenkar.java.costmanager.domain.model;

import il.ac.shenkar.java.costmanager.domain.repository.implementations.CategoryRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * The `Cost` class represents an expense with various attributes such as category, sum, currency, description, and date.
 */
public class Cost {
    private int id;
    private Category category;
    private double sum;
    private String currency;
    private String description;
    private Date date;

    /**
     * Constructs a new Cost with the specified attributes.
     *
     * @param category    The category of the expense.
     * @param sum         The sum of the expense.
     * @param currency    The currency of the expense.
     * @param description The description of the expense.
     * @param date        The date of the expense.
     * @throws SQLException If there's an issue with the category repository.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public Cost(Category category, double sum, String currency, String description, Date date) throws SQLException, IOException {
        setCategory(category);
        setSum(sum);
        setCurrency(currency);
        setDescription(description);
        setDate(date);
    }

    /**
     * Gets the unique ID of the cost.
     *
     * @return The ID of the cost.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the cost.
     * This method validates that the ID is non-negative.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        try {
            if (id >= 0) {
                this.id = id;
            } else {
                throw new IllegalArgumentException("ID should contain only non-negative integers.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid ID: " + e.getMessage());
        }
    }

    /**
     * Gets the category of the cost.
     *
     * @return The category of the cost.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the category name as a string.
     *
     * @return The name of the category.
     */
    public String getCategoryString() {
        return category.getName();
    }

    /**
     * Sets the category of the cost.
     * This method validates that the category exists in the DB and is therefore valid.
     *
     * @param category The category to set.
     * @throws SQLException If there's an issue with the category repository.
     * @throws IOException  If there's an issue with I/O operations.
     */
    public void setCategory(Category category) throws SQLException, IOException {
        CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
        try {
            // Validate that the category exists in the DB and is therefore valid
            categoryRepository.getCategory(category.getName());
            this.category = category;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the sum of the cost.
     *
     * @return The sum of the cost.
     */
    public double getSum() {
        return sum;
    }

    /**
     * Sets the sum of the cost.
     * This method validates that the sum is non-negative.
     *
     * @param sum The sum to set.
     */
    public void setSum(double sum) {
        try {
            if (sum >= 0) {
                this.sum = sum;
            } else {
                throw new IllegalArgumentException("Sum should contain only positive numbers.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid sum: " + e.getMessage());
        }
    }

    /**
     * Gets the currency of the cost.
     *
     * @return The currency of the cost.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of the cost.
     * This method validates that the currency contains only alphabetic characters (a-z, A-Z).
     *
     * @param currency The currency to set.
     */
    public void setCurrency(String currency) {
        try {
            if (currency.matches("^[a-zA-Z]+$")) {
                this.currency = currency;
            } else {
                throw new IllegalArgumentException("Currency should contain only alphabetic characters (a-z, A-Z).");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid currency: " + e.getMessage());
        }
    }

    /**
     * Gets the description of the cost.
     *
     * @return The description of the cost.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the cost.
     * This method validates that the description contains only alphabetic characters (a-z, A-Z).
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        try {
            if (description.matches("^[a-zA-Z]+$")) {
                this.description = description;
            } else {
                throw new IllegalArgumentException("Description should contain only alphabetic characters (a-z, A-Z).");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid description: " + e.getMessage());
        }
    }

    /**
     * Gets the date of the cost.
     *
     * @return The date of the cost.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the cost.
     * This method validates that the date is not null.
     *
     * @param date The date to set.
     */
    public void setDate(Date date) {
        if (date != null) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("Date cannot be null.");
        }
    }

    /**
     * Generates a string representation of the cost.
     *
     * @return A string representing the cost's details.
     */
    @Override
    public String toString() {
        return "Category: " + getCategory().getName() +
                " Sum: " + getSum() +
                " Currency: " + getCurrency() +
                " Description: " + getDescription() +
                " Date: " + getDate().toString();
    }
}
