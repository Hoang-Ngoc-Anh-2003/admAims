package Interface;

import model.entity.Book;

import java.util.List;

public interface IBookDAO {
    // Các phương thức lấy thông tin sách
    Book getBookInfor(int product_id);
    List<Book> getAllBooks();
    Book getBookById(int productId);

    // Các phương thức thay đổi dữ liệu
    boolean addBook(Book book);
    boolean updateBook(Book book);

    // Phương thức đếm số lượng sách
    int getBookCount();
}
