package Repository;

import modle.entity.ProductEntity;

import java.util.List;

public interface ProductRepository {
    List<ProductEntity> getAllproduct();

    void addProduct(ProductEntity productEntity);

    ProductEntity getLastProductId();

    void updateProduct(ProductEntity productEntity);

    void deleteProduct(String pId);

    ProductEntity getProductById(String text);

    List<ProductEntity> searchProductByName(String newText);
}
