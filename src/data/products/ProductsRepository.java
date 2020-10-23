package data.products;

import data.products.models.Product;

import java.util.List;

public interface ProductsRepository {

    List<Product> getProductsFromFirstFile();

    List<Product> getProductsFromSecondFile();
}
