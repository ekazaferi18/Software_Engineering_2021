package Group2A.SDMS.Repositories;

import Group2A.SDMS.Entities.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<ProductCategories, Integer> {

    ProductCategories findByCategoryID(int categoryID);

    ProductCategories findByName(String name);

    @Query("FROM ProductCategories WHERE deleted = false")
    List<ProductCategories> getAllCategories();

    @Modifying
    @Query("update ProductCategories set deleted = true where categoryID = :categoryID")
    void deleteCategoryByID(@Param("categoryID") int categoryID);

    @Modifying
    @Query(value = "update ProductCategories set name = :name, description = :description where categoryID = :categoryID")
    void editCategory( @Param("name") String name, @Param("description") String description, @Param("categoryID") int categoryID);
}
