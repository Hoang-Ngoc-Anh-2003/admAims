package view.component.ButtonAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Interface.ReloadablePanel;
import controller.EditProductController;
import view.component.ButtonUI.*;

// xử lý sự kiện button update/deletedelete
public class EditButtonAction extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    private int row;
    
    public EditButtonAction(JTable table, ReloadablePanel reloadablePanel) {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));

        RoundedButton btnEdit = new RoundedButton("Sửa", 10, new Color(0, 155, 229), new Color(0, 104, 190), new Color(0, 104, 190));
        RoundedButton btnDelete = new RoundedButton("Xóa", 10, new Color(255, 77, 77), new Color(204, 0, 0), new Color(204, 0, 0));

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                int selectedRow = table.convertRowIndexToModel(row);
                int productId = (int) table.getModel().getValueAt(selectedRow, 0);
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(table);

                EditProductController.openEditDialog(productId, parentFrame, reloadablePanel);
            }
        });


        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.convertRowIndexToModel(row);
                int productId = (int) table.getModel().getValueAt(selectedRow, 0);
                String category = (String) table.getModel().getValueAt(selectedRow, 2);

                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm ID " + productId + " thuộc loại " + category + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Gọi DAO tương ứng để xóa sản phẩm khỏi database
                    boolean deleted = false;
                    if (category.equalsIgnoreCase("book")) {
                        // deleted = bookDAO.deleteBook(productId); // Cần triển khai
                        JOptionPane.showMessageDialog(null, "Chức năng xóa sách chưa được triển khai cho database.");
                    } else if (category.equalsIgnoreCase("cd")) {
                        // Xóa CD
                        JOptionPane.showMessageDialog(null, "Chức năng xóa CD chưa được triển khai cho database.");
                    } else if (category.equalsIgnoreCase("dvd")) {
                        // Xóa DVD
                        JOptionPane.showMessageDialog(null, "Chức năng xóa DVD chưa được triển khai cho database.");
                    } else if (category.equalsIgnoreCase("lp")) {
                        // Xóa LP
                        JOptionPane.showMessageDialog(null, "Chức năng xóa LP chưa được triển khai cho database.");
                    }

                    if (deleted) {
                        ((DefaultTableModel) table.getModel()).removeRow(row);
                        JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi khi xóa.");
                    }
                }
            }
        });


        panel.add(btnEdit);
        panel.add(btnDelete);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}
