package il.ac.shenkar.java.costmanager.domain.repository.interfaces;

import il.ac.shenkar.java.costmanager.domain.model.Category;
import java.util.List;

public interface CategoryRepository {
    void addCategory(Category category);
    Category getCategory(String wantedName);
    List<Category> getAllCategories();
}
