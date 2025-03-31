package Model;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Product {
    protected int productId;
    protected String title;
    protected String category;
    protected BigDecimal value;
    protected BigDecimal price;
    protected String barcode;
    protected String description;
    protected int quantity;
    protected BigDecimal weight;
    protected String dimensions;
    protected Date warehouseEntryDate;
    protected String imageUrl;

    // Constructor
    public Product(int productId, String title, String category, BigDecimal value, BigDecimal price,
                   String barcode, String description, int quantity, BigDecimal weight,
                   String dimensions, Date warehouseEntryDate, String imageUrl) {
        this.productId = productId;
        this.title = title;
        this.category = category;
        this.value = value;
        this.price = price;
        this.barcode = barcode;
        this.description = description;
        this.quantity = quantity;
        this.weight = weight;
        this.dimensions = dimensions;
        this.warehouseEntryDate = warehouseEntryDate;
        this.imageUrl = imageUrl;
    }

    public Product(int product_id, String title, BigDecimal price, int quantity) {
        this.productId = product_id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public Product( String title, BigDecimal price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }
    
    public Product(int productId) {  // Constructor cần thiết
        this.productId = productId;
    }

    // Getters & Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {  // Thêm getter cho category
        return category;
    }

    public void setCategory(String category) {  // Thêm setter cho category
        this.category = category;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public Date getWarehouseEntryDate() {
        return warehouseEntryDate;
    }

    public void setWarehouseEntryDate(Date warehouseEntryDate) {
        this.warehouseEntryDate = warehouseEntryDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
