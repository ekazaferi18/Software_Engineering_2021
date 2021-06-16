package Group2A.SDMS.Services;

import Group2A.SDMS.Entities.ProductCategories;
import Group2A.SDMS.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductCategories getCategoryID(int categoryID){
        return categoryRepository.findByCategoryID(categoryID);
    }

    public ProductCategories getCategoryName(String name){
        return categoryRepository.findByName(name);
    }

    public List<ProductCategories> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

    public void saveCategory(ProductCategories productCategories){
        categoryRepository.save(productCategories);
    }

    public void deleteCategoryByID(int categoryID){
        categoryRepository.deleteCategoryByID(categoryID);
    }

}
