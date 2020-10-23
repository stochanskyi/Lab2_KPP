package data.products;

import data.dateFormatter.DateFormatter;
import data.products.models.Product;
import data.reader.FileConstants;
import data.reader.FileReader;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsRepositoryImpl implements ProductsRepository {

    private final DateFormatter dateFormatter = new DateFormatter();

    @Override
    public List<Product> getProductsFromFirstFile() {
        List<String> data = new FileReader().readFromFile(FileConstants.FIRST_FILE_NAME);
        return parseProducts(data);
    }

    @Override
    public List<Product> getProductsFromSecondFile() {
        List<String> data = new FileReader().readFromFile(FileConstants.SECOND_FILE_NAME);
        return parseProducts(data);
    }


    private List<Product> parseProducts(List<String> inputData) {
        return inputData.stream().map(this::parseProduct).collect(Collectors.toList());
    }

    private Product parseProduct(String product) {
        String[] input = product.split("\\s+");
        String name = input[0];

        Date manufacturedDate = null;
        Date endDate = null;
        try {
            manufacturedDate = dateFormatter.parseDate(input[1]);
            endDate = dateFormatter.parseDate(input[2]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        float price = Float.parseFloat(input[3]);

        return new Product(name, manufacturedDate, endDate, price);
    }
}
