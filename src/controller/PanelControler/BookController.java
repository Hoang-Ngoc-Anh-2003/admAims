package controller.PanelControler;

import model.dao.BookDAO;
import model.entity.Book;
import view.dialog.addDialog.AddBookDialog;
import view.Panel.BookPanel;

import javax.swing.*;
import java.util.List;

public class BookController {
    private final BookDAO bookDAO = new BookDAO();
    private final BookPanel bookPanel;

    public BookController(BookPanel bookPanel) {
        this.bookPanel = bookPanel;
    }

    public void loadBooks() {
        List<Book> books = bookDAO.getAllBooks();
        bookPanel.updateBookTable(books);
    }

    public void openAddBookDialog(JFrame parentFrame) {
        AddBookDialog dialog = new AddBookDialog(parentFrame, "Thêm Sách", true);
        dialog.setVisible(true);
        if (dialog.isSaveClicked()) {
            loadBooks();
        }
    }
}
