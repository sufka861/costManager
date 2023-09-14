package il.ac.shenkar.java.costmanager.domain.model;

public class Category {
    private int id;
    private String name;

    public Category(String name) {
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getName() {
        return name;
    }

    private void setName(String name) {
        try {
            if (name.matches("^[a-zA-Z]+[0-9]*$")) {
                this.name = name;
            } else {
                throw new IllegalArgumentException("Name should contain only alphabetic characters (a-z, A-Z).");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid name: " + e.getMessage());
        }
    }
}
