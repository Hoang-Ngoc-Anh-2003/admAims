package controller.PanelControler;

import model.dao.BookDAO;
import model.entity.Book;
import view.dialog.addDialog.AddBookDialog;
import view.Panel.BookPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.AddProductControler.*;

import java.util.List;

public class BookController {
    private final BookDAO bookDAO;
    private final BookPanel bookPanel;

    public BookController(BookPanel bookPanel) {
        this.bookDAO = new BookDAO();
        this.bookPanel = bookPanel;
        setupEventListeners();
        loadBooks();
    }

    private void setupEventListeners() {
        bookPanel.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy JFrame cha của BookPanel để hiển thị dialog
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(bookPanel);
                openAddBookDialog(parentFrame);
            }
        });
    }

    public void loadBooks() {
        List<Book> books = bookDAO.getAllBooks();
        bookPanel.updateBookTable(books);
    }

    public void openAddBookDialog(JFrame parentFrame) {
        AddBookDialog dialog = new AddBookDialog(parentFrame, "Thêm Sách", true);
        AddBookController addBookController = new AddBookController(dialog);
        dialog.setVisible(true);
        loadBooks();
    }

    public void openEditBookDialog(int bookId) {
    }
}
