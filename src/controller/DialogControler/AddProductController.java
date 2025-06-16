package controller.DialogControler;

import model.dao.*;
import model.entity.*;

import javax.swing.JOptionPane;

public class AddProductController {
    public boolean addBook(String title, String authors, String publisher,
        int numPages, String language, String genre, String publicationDate,
        String coverType, String warehouseEntryDate, int quantity, String dimensions,
        String weight, double value, double price, String description) {

        try {
            // Kiểm tra dữ liệu bắt buộc (có thể mở rộng validation tại đây)
            if (title.isEmpty() || authors.isEmpty() || publicationDate.isEmpty() || warehouseEntryDate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ các trường bắt buộc (Tiêu đề, Tác giả, Ngày xuất bản, Ngày nhập kho).", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Tạo đối tượng Book
            Book book = new Book(0, title, "book", value, price, "0", description,
                quantity, weight, dimensions, warehouseEntryDate,authors,
                coverType, publisher, publicationDate, numPages,
                language, genre);

            // Gọi DAO để lưu vào database            // Gọi DAO để lưu vào database
            boolean success = BookDAO.getInstance().addBook(book);
            if (success) {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu vào cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho số trang, số lượng, giá và trọng lượng.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean addCD(String title, String warehouseEntryDate, String dimensions,
        String weight, String description, double value, double price,
        int quantity, String artists, String recordLabel, String tracklist,
        String genre, String releaseDate) {

        try {
            // Kiểm tra dữ liệu bắt buộc (có thể mở rộng validation tại đây)
            if (title.isEmpty() || title.isEmpty() || artists.isEmpty() || warehouseEntryDate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ các trường bắt buộc (Tiêu đề, Tác giả, Ngày xuất bản, Ngày nhập kho).", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            CD cd = new CD(0, title, "CD", value, price, title, description, quantity, weight, dimensions, warehouseEntryDate, artists, artists, tracklist, genre, releaseDate);

            // Gọi DAO để lưu vào database
            boolean success = CDDAO.getInstance().addCD(cd);
            if (success) {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu vào cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho số trang, số lượng, giá và trọng lượng.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean addDVD(String title, String warehouseEntryDate, String dimensions,
        String weight, String description, double value, double price,
        int quantity, String discType, String director, int runtime,
        String studio, String language, String subtitles, String releaseDate, String genre) {

        try {
            // Kiểm tra dữ liệu bắt buộc (có thể mở rộng validation tại đây)
            if (title.isEmpty() || studio.isEmpty() || description.isEmpty() || warehouseEntryDate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ các trường bắt buộc (Tiêu đề, Tác giả, Ngày xuất bản, Ngày nhập kho).", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Tạo đối tượng DVD
            DVD DVD = new DVD(0, title, "DVD", value, price, "0", description,
            quantity, weight, dimensions, warehouseEntryDate,discType,
            director, runtime, studio, language, subtitles, releaseDate, genre);

            // Gọi DAO để lưu vào database
            boolean success = DVDDAO.getInstance().addDVD(DVD);
            if (success) {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu vào cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho số trang, số lượng, giá và trọng lượng.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean addLP(String title, String warehouseEntryDate, String dimensions,
        String weight, String description, double value, double price,
        int quantity, String artists, String recordLabel, String tracklist,
        String genre, String releaseDate) {

        try {
            // Kiểm tra dữ liệu bắt buộc (có thể mở rộng validation tại đây)
            if (title.isEmpty() || title.isEmpty() || warehouseEntryDate.isEmpty() || warehouseEntryDate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ các trường bắt buộc (Tiêu đề, Tác giả, Ngày xuất bản, Ngày nhập kho).", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Tạo đối tượng LP
            LP LP = new LP(0, title, "LP", value, price, "0", description,
            quantity, weight, dimensions, warehouseEntryDate,artists, recordLabel, tracklist, genre, releaseDate);

            // Gọi DAO để lưu vào database
            boolean success = LPDAO.getInstance().addLP(LP);
            if (success) {
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu vào cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số cho số trang, số lượng, giá và trọng lượng.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
