package Group2A.SDMS.Services;

import Group2A.SDMS.Entities.Products;
import Group2A.SDMS.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Products getProductByProductID(int productID){
        return productRepository.findByProductID(productID);
    }

    public List<Products> getAllProducts(){
        return productRepository.getAllProducts();
    }

    public void saveProduct(Products product){
        productRepository.save(product);
    }

    public void deleteProductByID(int productID){
        productRepository.deleteProductByID(productID);
    }

    public void editProduct(Products product){ productRepository.save(product); }
}
