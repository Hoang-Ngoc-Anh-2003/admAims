package DAO;

import Model.LP;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LPDAO extends ProductDAO {
    
    // lay danh sach san pham de hien thi tren bang "Panel"
    public List<LP> getAllLPs() {
        List<LP> lps = new ArrayList<>();
        String sql = "SELECT p.product_id, p.title, p.category, p.value, p.price, p.barcode, " +
                     "p.description, p.quantity, p.weight, p.dimensions, p.warehouse_entry_date, " +
                     "l.artists, l.record_label, l.tracklist, l.genre, l.release_date " +
                     "FROM Products p " +
                     "INNER JOIN LPs l ON p.product_id = l.lp_id " +
                     "WHERE p.category = 'LP'";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                LP lp = new LP(
                    rs.getInt("product_id"),
                    rs.getString("title"),
                    rs.getString("category"), // Đảm bảo bạn chọn cột 'category' trong truy vấn
                    rs.getDouble("value"),
                    rs.getDouble("price"),
                    rs.getString("barcode"),
                    rs.getString("description"),
                    rs.getInt("quantity"),
                    rs.getString("weight"),
                    rs.getString("dimensions"),
                    rs.getString("warehouse_entry_date"),
                    rs.getString("artists"),
                    rs.getString("record_label"),
                    rs.getString("tracklist"),
                    rs.getString("genre"),
                    rs.getString("release_date")
                );
                lps.add(lp);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return lps;
    }
    

    public LP getLPById(int productId) {
        String sql = "SELECT * FROM Products JOIN LPs ON Products.product_id = LPs.product_id WHERE Products.product_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToLP(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // so luong LP
    public static int getLPCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM LPs";
    
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
    

    private LP mapResultSetToLP(ResultSet rs) throws SQLException {
        return new LP(
            rs.getInt("product_id"),
            rs.getString("title"),
            rs.getString("category"), 
            rs.getDouble("value"),
            rs.getDouble("price"),
            rs.getString("barcode"),
            rs.getString("description"),
            rs.getInt("quantity"),
            rs.getString("weight"),
            rs.getString("dimensions"),
            rs.getString("warehouse_entry_date"),
            rs.getString("artists"),
            rs.getString("record_label"),
            rs.getString("tracklist"),
            rs.getString("genre"),
            rs.getString("release_date")
        );
    }
}
