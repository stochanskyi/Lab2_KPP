package data.products.models;

import data.dateFormatter.DateFormatter;

import java.util.Date;

public class Product implements Comparable<Product> {
    private String name;
    private Date manufacturedDate;
    private Date endDate;
    private float price;

    public Product(String name, Date manufacturedDate, Date endDate, float price) {
        this.name = name;
        this.manufacturedDate = manufacturedDate;
        this.endDate = endDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Date getManufacturedDate() {
        return manufacturedDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price < 0) return;
        this.price = price;
    }

    @Override
    public String toString() {
        DateFormatter df = DateFormatter.getInstance();
        return
                "name='" + name + '\'' +
                ", manufacturedDate=" + df.formatDate(manufacturedDate) +
                ", endDate=" + df.formatDate(endDate) +
                ", price=" + price;
    }

    @Override
    public int compareTo(Product o) {
        return name.compareTo(o.name);
    }
}
