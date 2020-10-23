package execution;

import data.dateFormatter.DateFormatter;
import data.products.models.Product;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Printer {

    private final DateFormatter datesFormatter = DateFormatter.getInstance();

    public static Printer getInstance() {
        if (instance == null) {
            instance = new Printer();
        }
        return instance;
    }

    private static Printer instance = null;


    public void printMap(Map<Date, List<Product>> map) {

        map.forEach((date, products) -> {
            System.out.print(datesFormatter.formatDate(date) + " ::::: ");

            products.forEach(product -> System.out.print(product.toString() + " \\/\\/\\/\\/ "));

            System.out.println();
        });
    }

    public void printList(List<Product> products, String title) {
        System.out.println(title.toUpperCase());
        products.forEach(product -> printLine(product.toString()));
    }

    public void printStep(String text) {
        System.out.println();
        printLine(text);
        System.out.println();
    }

    public void printLine(String text) {
        System.out.println(text);
    }

}
