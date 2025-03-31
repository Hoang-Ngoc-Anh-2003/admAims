package GUI.component.ButtonAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.component.ButtonUI.*;;

// xử lý sự kiện button update/deletedelete
public class EditButtonAction extends AbstractCellEditor implements TableCellEditor {
    private JPanel panel;
    @SuppressWarnings("unused")
    private JButton btnEdit, btnDelete;
    @SuppressWarnings("unused")
    private JTable table;
    private int row;

    public EditButtonAction(JTable table) {
        this.table = table;
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));

        RoundedButton btnEdit = new RoundedButton("Sửa", 10, new Color(0, 155, 229), new Color(0, 104, 190), new Color(0, 104, 190));
        RoundedButton btnDelete = new RoundedButton("Xóa", 10, new Color(255, 77, 77), new Color(204, 0, 0), new Color(204, 0, 0));

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chỉnh sửa dòng: " + row);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa dòng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ((DefaultTableModel) table.getModel()).removeRow(row);
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
