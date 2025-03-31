package DAO;

import Model.DVD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DVDDAO extends ProductDAO {
    
    // lay danh sach san pham de hien thi tren bang "Panel"
    public List<DVD> getAllDVDs() {
        List<DVD> dvds = new ArrayList<>();
        String sql = "SELECT p.product_id, p.title, p.category, p.value, p.price, p.barcode, " +
                     "p.description, p.quantity, p.weight, p.dimensions, p.warehouse_entry_date, " +
                     "d.disc_type, d.director, d.runtime, d.studio, d.language, " +
                     "d.subtitles, d.release_date, d.genre " +
                     "FROM Products p " +
                     "INNER JOIN DVDs d ON p.product_id = d.dvd_id " +
                     "WHERE p.category = 'DVD'";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                DVD dvd = new DVD(
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
                    null, // Image chưa xử lý
                    rs.getString("disc_type"),
                    rs.getString("director"),
                    rs.getInt("runtime"),
                    rs.getString("studio"),
                    rs.getString("language"),
                    rs.getString("subtitles"),
                    rs.getDate("release_date"),
                    rs.getString("genre")
                );
                dvds.add(dvd);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return dvds;
    }
    

    public DVD getDVDById(int productId) {
        String sql = "SELECT * FROM Products JOIN DVDs ON Products.product_id = DVDs.product_id WHERE Products.product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDVD(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // so luong DVD
    public static int getDVDCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM DVDs";
    
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
    

    private DVD mapResultSetToDVD(ResultSet rs) throws SQLException {
        return new DVD(
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
            rs.getString("disc_type"),
            rs.getString("director"),
            rs.getInt("runtime"),
            rs.getString("studio"),
            rs.getString("language"),
            rs.getString("subtitles"),
            rs.getDate("release_date"),
            rs.getString("genre")
        );
    }
}
