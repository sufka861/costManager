package il.ac.shenkar.java.costmanager.domain.model;

public class Category {
    private int id;
    private String name;

    public Category(String name)
    {
        this.name = name;
        this.id = 0;
        // TODO FIX ID MATCH WITH DB
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
