package Model;

import java.math.BigDecimal;
import java.util.Date;

public class Book extends Product {
    private String authors;     // Danh sách tác giả
    private String coverType;   // Loại bìa (paperback, hardcover)
    private String publisher;   // Nhà xuất bản
    private Date publicationDate; // Ngày xuất bản
    private int numPages;       // Số trang
    private String language;    // Ngôn ngữ
    private String genre;       // Thể loại

    public Book(int productId, String title, BigDecimal value, BigDecimal price, String barcode,
                String description, int quantity, BigDecimal weight, String dimensions,
                Date warehouseEntryDate, String imageUrl, String authors, String coverType,
                String publisher, Date publicationDate, int numPages, String language, String genre) {
        super(productId, title, "book", value, price, barcode, description, quantity, weight,
              dimensions, warehouseEntryDate, imageUrl);
        this.authors = authors;
        this.coverType = coverType;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.numPages = numPages;
        this.language = language;
        this.genre = genre;
    }

    public Book( int product_id, String title, String authors, BigDecimal price, int quantity) {
        super( product_id, title, price, quantity); // Gọi constructor của Product
        this.authors = authors;
    }

    public Book( String title, String authors, BigDecimal price, int quantity) {
        super( title, price, quantity); // Gọi constructor của Product
        this.authors = authors;
    }
    

    // Getters & Setters
    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
