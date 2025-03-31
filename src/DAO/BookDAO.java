package DAO;

import Model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends ProductDAO {

    public BookDAO() {
        super();
    }

    // lay danh sach san pham de hien thi tren bang "Panel"
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.book_id, b.authors, b.cover_type, b.publisher, b.publication_date, " +
                     "b.num_pages, b.language, b.genre, " +
                     "p.product_id, p.title, p.category, p.value, p.price, p.barcode, " +
                     "p.description, p.quantity, p.weight, p.dimensions, p.warehouse_entry_date " +
                     "FROM Books b " +
                     "INNER JOIN Products p ON b.book_id = p.product_id";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("product_id"),
                    rs.getString("title"),
                    rs.getBigDecimal("value"),
                    rs.getBigDecimal("price"),
                    rs.getString("barcode"),
                    rs.getString("description"),
                    rs.getInt("quantity"),
                    rs.getBigDecimal("weight"),
                    rs.getString("dimensions"),
                    rs.getDate("warehouse_entry_date"),
                    null, // Chưa xử lý image
                    rs.getString("authors"),
                    rs.getString("cover_type"),
                    rs.getString("publisher"),
                    rs.getDate("publication_date"),
                    rs.getInt("num_pages"),
                    rs.getString("language"),
                    rs.getString("genre")
                );
                books.add(book);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return books;
    }

    // Thêm sách mới
    public boolean addBook(Book book) {
        String productSQL = "INSERT INTO Products (title, category, value, price, barcode, description, quantity, weight, dimensions, warehouse_entry_date) " +
                            "VALUES (?, 'book', ?, ?, ?, ?, ?, ?, ?, ?) RETURNING product_id";
        String bookSQL = "INSERT INTO Books (book_id, authors, cover_type, publisher, publication_date, num_pages, language, genre) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement productStmt = conn.prepareStatement(productSQL);
             PreparedStatement bookStmt = conn.prepareStatement(bookSQL)) {

            // Thêm vào Products
            productStmt.setString(1, book.getTitle());
            productStmt.setBigDecimal(2, book.getValue());
            productStmt.setBigDecimal(3, book.getPrice());
            productStmt.setString(4, book.getBarcode());
            productStmt.setString(5, book.getDescription());
            productStmt.setInt(6, book.getQuantity());
            productStmt.setBigDecimal(7, book.getWeight());
            productStmt.setString(8, book.getDimensions());
            productStmt.setDate(9, new java.sql.Date(book.getWarehouseEntryDate().getTime()));

            ResultSet rs = productStmt.executeQuery();
            if (rs.next()) {
                int productId = rs.getInt(1);

                // Thêm vào Books
                bookStmt.setInt(1, productId);
                bookStmt.setString(2, book.getAuthors());
                bookStmt.setString(3, book.getCoverType());
                bookStmt.setString(4, book.getPublisher());
                bookStmt.setDate(5, new java.sql.Date(book.getPublicationDate().getTime()));
                bookStmt.setInt(6, book.getNumPages());
                bookStmt.setString(7, book.getLanguage());
                bookStmt.setString(8, book.getGenre());

                int rowsInserted = bookStmt.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật sách
    public boolean updateBook(Book book) {
        String productSQL = "UPDATE Products SET title = ?, value = ?, price = ?, barcode = ?, description = ?, quantity = ?, weight = ?, dimensions = ?, warehouse_entry_date = ? " +
                            "WHERE product_id = ?";
        String bookSQL = "UPDATE Books SET authors = ?, cover_type = ?, publisher = ?, publication_date = ?, num_pages = ?, language = ?, genre = ? " +
                         "WHERE book_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement productStmt = conn.prepareStatement(productSQL);
             PreparedStatement bookStmt = conn.prepareStatement(bookSQL)) {

            // Cập nhật Products
            productStmt.setString(1, book.getTitle());
            productStmt.setBigDecimal(2, book.getValue());
            productStmt.setBigDecimal(3, book.getPrice());
            productStmt.setString(4, book.getBarcode());
            productStmt.setString(5, book.getDescription());
            productStmt.setInt(6, book.getQuantity());
            productStmt.setBigDecimal(7, book.getWeight());
            productStmt.setString(8, book.getDimensions());
            productStmt.setDate(9, new java.sql.Date(book.getWarehouseEntryDate().getTime()));
            productStmt.setInt(10, book.getProductId());

            // Cập nhật Books
            bookStmt.setString(1, book.getAuthors());
            bookStmt.setString(2, book.getCoverType());
            bookStmt.setString(3, book.getPublisher());
            bookStmt.setDate(4, new java.sql.Date(book.getPublicationDate().getTime()));
            bookStmt.setInt(5, book.getNumPages());
            bookStmt.setString(6, book.getLanguage());
            bookStmt.setString(7, book.getGenre());
            bookStmt.setInt(8, book.getProductId());

            int productUpdated = productStmt.executeUpdate();
            int bookUpdated = bookStmt.executeUpdate();
            return productUpdated > 0 && bookUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Book getBookById(int productId) {
        String sql = "SELECT * FROM Products JOIN Books ON Products.product_id = Books.product_id WHERE Products.product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBook(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // so luong sach
    public static int getBookCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Books";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        return new Book(
            rs.getInt("product_id"),
            rs.getString("title"),
            rs.getBigDecimal("value"),
            rs.getBigDecimal("price"),
            rs.getString("barcode"),
            rs.getString("description"),
            rs.getInt("quantity"),
            rs.getBigDecimal("weight"),
            rs.getString("dimensions"),
            rs.getDate("warehouse_entry_date"),
            rs.getString("image_url"),
            rs.getString("authors"),
            rs.getString("cover_type"),
            rs.getString("publisher"),
            rs.getDate("publication_date"),
            rs.getInt("num_pages"),
            rs.getString("language"),
            rs.getString("genre")
        );
    }
}
