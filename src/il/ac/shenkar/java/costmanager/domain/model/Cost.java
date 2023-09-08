package il.ac.shenkar.java.costmanager.domain.model;

import java.util.Date;

public class Cost {
    private int id;
    private Category category;
    private double sum;
    private String currency;
    private String description;
    private Date date;

    public Cost(Category category, double sum, String currency, String description, Date date)
    {
        this.category = category;
        this.sum = sum;
        this.currency = currency;
        this.description = description;
        this.date = date;
    }

    public Cost(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public double getSum()
    {
        return sum;
    }

    public void setSum(double sum)
    {
        this.sum = sum;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

}