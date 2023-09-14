package il.ac.shenkar.java.costmanager.domain.model;
import il.ac.shenkar.java.costmanager.domain.repository.implementations.CategoryRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class Cost {
    private int id;
    private Category category;
    private double sum;
    private String currency;
    private String description;
    private Date date;

    public Cost(Category category, double sum, String currency, String description, Date date) throws SQLException, IOException {
        setCategory(category);
        setSum(sum);
        setCurrency(currency);
        setDescription(description);
        setDate(date);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        try {
            if (id >= 0) {
                this.id = id;
            } else {
                throw new IllegalArgumentException("ID should contain only integers.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid ID: " + e.getMessage());
        }
    }

    public Category getCategory()
    {
        return category;
    }
    public String getCategoryString()
    {
        return category.getName();
    }


    public void setCategory(Category category) throws SQLException, IOException {
        CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
        try {
            // Validate that category exists in the DB and therefore it is valid
            categoryRepository.getCategory(category.getName());
            this.category = category;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double getSum()
    {
        return sum;
    }

    public void setSum(double sum)
    {
        try {
            if (sum >= 0) {
                this.sum = sum;
            } else {
                throw new IllegalArgumentException("Sum should contain only positive integers.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid sum: " + e.getMessage());
        }    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        try {
            if (currency.matches("^[a-zA-Z]+$")) {
                this.currency = currency;
            } else {
                throw new IllegalArgumentException("currency should contain only alphabetic characters (a-z, A-Z).");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid currency: " + e.getMessage());
        }
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        try {
            if (description.matches("^[a-zA-Z]+$")) {
                this.description = description;
            } else {
                throw new IllegalArgumentException("description should contain only alphabetic characters (a-z, A-Z).");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid description: " + e.getMessage());
        }        }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("Date cannot be null.");
        }
    }


    @Override
    public String toString() {

        return "Category: " + getCategory().getName() +
                " Sum: " + getSum() +
                " Currency: " + getCurrency() +
                " Description: " + getDescription() +
                " Date: " + getDate().toString();
    }
}


