package view.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;

import view.component.ButtonAction.EditButtonAction;
import view.component.ButtonUI.*;
import view.component.CustomTable.CustomTableCellRenderer;
import Interface.ReloadablePanel;
import model.entity.*;

public class DVDPanel extends JPanel implements ReloadablePanel{
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnAdd;

    public DVDPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAdd = new RoundedButton("+ Thêm DVD", 15, new Color(0, 155, 229), new Color(0, 104, 190), new Color(0, 104, 190));
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setBackground(new Color(0, 155, 229));
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setPreferredSize(new Dimension(140, 35));
        headerPanel.add(btnAdd);

        String[] columnNames = {"ID", "Tên DVD", "Đạo diễn", "Giá", "Số lượng", "Hành động"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setIntercellSpacing(new Dimension(10, 5));
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.getTableHeader().setReorderingAllowed(false);

        // Cố định kích thước cột Hành động
        TableColumn actionColumn = table.getColumnModel().getColumn(5);
        actionColumn.setPreferredWidth(150);
        actionColumn.setMaxWidth(180);
        actionColumn.setMinWidth(180);

        JScrollPane scrollPane = new JScrollPane(table);

        // Đặt renderer để giữ màu nguyên bản ngay cả khi chọn
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableCellRenderer());
        }

        // Loại bỏ hiệu ứng nền khi chọn
        table.setSelectionBackground(table.getBackground());
        table.setSelectionForeground(table.getForeground());


        // Đặt renderer cho cột cuối cùng (Hành động)
        // Áp dụng EditButtonRenderer và EditButtonAction
        table.getColumnModel().getColumn(5).setCellRenderer(new EditButtonRenderer());
        table.getColumnModel().getColumn(5).setCellEditor(new EditButtonAction(table, this));


        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount() - 1; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Phương thức để Controller đăng ký sự kiện cho nút "DVD"
    public void addAddButtonListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void updateDVDTable(List<DVD> DVDs) {
        tableModel.setRowCount(0);
        for (DVD dvd : DVDs) {
            tableModel.addRow(new Object[]{
                    dvd.getProductId(), dvd.getTitle(), dvd.getDirector(),
                    dvd.getPrice(), dvd.getQuantity(), ""
            });
        }
        System.out.println("Số lượng DVD: " + DVDs.size());
    }


    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    @Override
    public void reloadData() {

    }
}
