package execution;

import data.dateFormatter.DateFormatter;
import data.products.ProductsRepository;
import data.products.ProductsRepositoryImpl;
import data.products.models.Product;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Executor {

    private final ProductsRepository repository = new ProductsRepositoryImpl();

    private final DateFormatter datesFormatter = DateFormatter.getInstance();

    private final Printer printer = Printer.getInstance();
    private final AppScanner scanner = new AppScanner();

    public void execute() {
        printer.printStep("First step");
        Map<Date, List<Product>> map = performFirstStep();
        printer.printMap(map);

        printer.printStep("Second step");
        performSecondStep(map);
        printer.printMap(map);

        printer.printStep("Third step");
        List<Product> products = performThirdStep();
        printer.printList(products, "Combined and filtered");
    }

    private Map<Date, List<Product>> performFirstStep() {
        List<Product> products = repository.getProductsFromFirstFile();

        products.forEach(product -> {
            long currentTime = System.currentTimeMillis();
            if (datesFormatter.daysBetweenTimestamps(product.getEndDate().getTime(), currentTime) < 3) {
                product.setPrice((float) (product.getPrice() - product.getPrice() * 0.1));
            }
        });

        return products.stream().collect(Collectors.groupingBy(Product::getEndDate));
    }

    private void performSecondStep(Map<Date, List<Product>> input) {

        printer.printLine("Please enter product name to delete");

        String nameToDelete = scanner.getLine();

        input.forEach((date, products) -> products.removeIf(product -> product.getName().equals(nameToDelete)));

        List<Date> keysToRemove = input.entrySet().stream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        keysToRemove.forEach(input::remove);
    }

    private List<Product> performThirdStep() {
        List<Product> firstList = repository.getProductsFromFirstFile();
        List<Product> secondList = repository.getProductsFromSecondFile();

        printer.printList(firstList, "First list");
        printer.printList(secondList, "Second list");

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        return Stream.concat(firstList.stream(), secondList.stream())
                .filter(item -> !(listContainsItem(firstList, item) && listContainsItem(secondList, item)))
                .filter(item -> {
                            calendar.setTime(item.getManufacturedDate());
                            return calendar.get(Calendar.YEAR) == currentYear;
                        }
                ).collect(Collectors.toList());

    }
    private boolean listContainsItem(List<Product> list, Product product) {
        return list.stream().anyMatch(item -> item.compareTo(product) == 0);
    }
}
