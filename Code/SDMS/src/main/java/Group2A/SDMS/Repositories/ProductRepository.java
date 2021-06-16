package Group2A.SDMS.Repositories;

import Group2A.SDMS.Entities.ProcurementOrder;
import Group2A.SDMS.Entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    Products findByProductID(int productID);

    @Query("FROM Products WHERE deleted = false")
    List<Products> getAllProducts();

    @Modifying
    @Query("update Products set deleted = true where productID = :productID")
    void deleteProductByID(@Param("productID") int productID);

    @Modifying
    @Query(value = "update Products set name = :name, description = :description, quantity = :quantity," +
            "price = :price, categoryID = :categoryID, expirationDate = :expirationDate where productID = :productID")
    void editProduct( @Param("name") String name, @Param("description") String description, @Param("quantity") int quantity,
                   @Param("price") double price, @Param("categoryID") int categoryID, @Param("expirationDate") Timestamp expirationDate,
                   @Param("productID") int productID);
}
