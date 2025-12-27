package service.Impl;

import Repository.ProductRepository;
import Repository.Impl.ProductRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modle.dto.Product;
import modle.dto.Supplier;
import modle.entity.ProductEntity;
import service.ProductService;
import service.SupplierService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    private ObservableList<Product> productObservableListForSearchProduct = FXCollections.observableArrayList();
    private List<ProductEntity> productEntityList = null;
    private List<String> supplierIds = new ArrayList<>();
    private ObservableList<Supplier> suppliers = FXCollections.observableArrayList();


    private ProductRepository productRepository = new ProductRepositoryImpl();
    private SupplierService supplierService = new SupplierServiceImpl();

    @Override
    public ObservableList<Product> getAllProducts() {
        productObservableList.clear();
        productEntityList = productRepository.getAllproduct();

        for (ProductEntity productEntity: productEntityList){
            productObservableList.add(new Product(
                    productEntity.getPId(),
                    productEntity.getPName(),
                    productEntity.getCatagory(),
                    productEntity.getSize(),
                    productEntity.getPrice(),
                    productEntity.getAvailibleQty(),
                    productEntity.getSid()
            ));
        }
        return productObservableList;
    }

    @Override
    public void addProduct(Product product) {
        if (product == null){return;}

        productRepository.addProduct(new ProductEntity(
                product.getPId(),
                product.getPName(),
                product.getCatagory(),
                product.getSize(),
                product.getPrice(),
                product.getAvailibleQty(),
                product.getSid()
        ));
    }

    @Override
    public String getNewProductId() {
        ProductEntity lastProduct = productRepository.getLastProductId();
        if(lastProduct==null) {
            return "P001";
        }
        String lastSupId = lastProduct.getPId();
        int number = Integer.parseInt(lastSupId.split("P")[1]); //1
        number++;
        return String.format("P%03d", number);
    }

    @Override
    public void updateProduct(Product product) {
        if (product == null){
            return;
        }
        productRepository.updateProduct(new ProductEntity(
                product.getPId(),
                product.getPName(),
                product.getCatagory(),
                product.getSize(),
                product.getPrice(),
                product.getAvailibleQty(),
                product.getSid()
        ));
    }

    @Override
    public void deleteProduct(Product selectedProduct) {
        if (selectedProduct == null){
            return;
        }
        productRepository.deleteProduct(selectedProduct.getPId());
    }

    @Override
    public Product getProductById(String text) {
        ProductEntity productEntity = productRepository.getProductById(text);
        return new Product(
                productEntity.getPId(),
                productEntity.getPName(),
                productEntity.getCatagory(),
                productEntity.getSize(),
                productEntity.getPrice(),
                productEntity.getAvailibleQty(),
                productEntity.getSid()
        );
    }

    @Override
    public List<String> getAllSupplierIds() {
        supplierIds.clear();
        suppliers = supplierService.getAllSuppliers();
        for (Supplier supplier: suppliers){
            supplierIds.add(supplier.getSId());
        }
        return supplierIds;
    }

    @Override
    public ObservableList<Product> searchProductByName(String newText) {
        if (!productObservableListForSearchProduct.isEmpty()){productObservableListForSearchProduct.clear();}

        if(newText == null || newText.trim().length()<2){
            return null;
        }

        productEntityList = productRepository.searchProductByName(newText);
        productObservableListForSearchProduct.clear();
        for (ProductEntity productEntity: productEntityList){
            productObservableListForSearchProduct.add(new Product(
                    productEntity.getPId(),
                    productEntity.getPName(),
                    productEntity.getCatagory(),
                    productEntity.getSize(),
                    productEntity.getPrice(),
                    productEntity.getAvailibleQty(),
                    productEntity.getSid()
            ));
        }
        return productObservableListForSearchProduct;
    }
}
