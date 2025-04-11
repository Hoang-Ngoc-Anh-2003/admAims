package DAO;

import Model.CD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CDDAO extends ProductDAO {
    
    // lay danh sach san pham de hien thi tren bang "Panel"
    public List<CD> getAllCDs() {
        List<CD> cds = new ArrayList<>();
        String sql = "SELECT p.product_id, p.title, p.category, p.value, p.price, p.barcode, " +
                     "p.description, p.quantity, p.weight, p.dimensions, p.warehouse_entry_date, " +
                     "c.artists, c.record_label, c.tracklist, c.genre, c.release_date " +
                     "FROM products p " +
                     "INNER JOIN cds c ON p.product_id = c.cd_id " +
                     "WHERE p.category = 'CD'";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                CD cd = new CD(
                    rs.getInt("product_id"),
                    rs.getString("title"),
                    rs.getString("category"), 
                    rs.getBigDecimal("value"),
                    rs.getBigDecimal("price"),
                    rs.getString("barcode"),
                    rs.getString("description"),
                    rs.getInt("quantity"),
                    rs.getString("weight"),
                    rs.getString("dimensions"),
                    rs.getDate("warehouse_entry_date"),
                    rs.getString("artists"),
                    rs.getString("record_label"),
                    rs.getString("tracklist"),
                    rs.getString("genre"),
                    rs.getDate("release_date")
                );
                cds.add(cd);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return cds;
    }
    

    public CD getCDById(int productId) {
        String sql = "SELECT * FROM Products JOIN CDs ON Products.product_id = CDs.product_id WHERE Products.product_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
    
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCD(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // so luong CD
    public static int getCDCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM CDs";
    
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
    

    private CD mapResultSetToCD(ResultSet rs) throws SQLException {
        return new CD(
            rs.getInt("product_id"),
            rs.getString("title"),
            rs.getString("category"),
            rs.getBigDecimal("value"),
            rs.getBigDecimal("price"),
            rs.getString("barcode"),
            rs.getString("description"),
            rs.getInt("quantity"),
            rs.getString("weight"),
            rs.getString("dimensions"),
            rs.getDate("warehouse_entry_date"),
            rs.getString("artists"),
            rs.getString("record_label"),
            rs.getString("tracklist"),
            rs.getString("genre"),
            rs.getDate("release_date")
        );
    }
}
