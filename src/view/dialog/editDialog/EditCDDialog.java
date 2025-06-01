package view.dialog.editDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.component.ButtonUI.*;
import model.dao.*;
import model.entity.CD;

import javax.swing.border.TitledBorder;


public class EditCDDialog extends JDialog {

    private JTextField titleTextField;
    private JTextField artistTextField;
    private JTextField recordLabelTextField;
    private JTextField genreTextField;
    private JFormattedTextField releaseDateTextField; // Sử dụng JFormattedTextField cho định dạng ngày
    private JTextArea trackListTextArea;
    private JFormattedTextField importDateTextField; // Sử dụng JFormattedTextField cho định dạng ngày
    private JTextField quantityTextField;
    private JTextField dimensionsTextField;
    private JTextField weightTextField;
    private JTextField sellingPriceTextField;
    private JTextField importPriceTextField;
    private JTextArea descriptionTextArea;
    private JButton cancelButton;
    private JButton saveButton;
    private boolean isSaveClicked = false;
    private CD cdToEdit; // Thêm biến để lưu trữ thông tin CD cần sửa
    private final Insets labelMargin = new Insets(10, 10, 10, 15);
    private final Insets fieldMargin = new Insets(10, 0, 10, 15);

    public EditCDDialog(JFrame parent, String title, boolean modal, CD cd) {
        super(parent, title, modal);
        this.cdToEdit = cd; // Lưu trữ đối tượng CD được truyền vào
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(contentPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(5, 5, 5, 5);

        // --- Thông tin CD ---
        JPanel cdInfoPanel = new JPanel(new GridBagLayout());
        cdInfoPanel.setBorder(new TitledBorder("Thông tin chung"));
        GridBagConstraints gbcCD = new GridBagConstraints();
        gbcCD.fill = GridBagConstraints.HORIZONTAL;
        gbcCD.weightx = 0.5;
        gbcCD.insets = new Insets(5, 5, 5, 5);

        // Hàng 1: Tiêu đề - Hãng thu
        gbcCD.gridx = 0;
        gbcCD.gridy = 0;
        gbcCD.insets = labelMargin;
        cdInfoPanel.add(new JLabel("Tiêu đề :"), gbcCD);

        gbcCD.gridx = 1;
        gbcCD.gridy = 0;
        gbcCD.insets = fieldMargin;
        cdInfoPanel.add(titleTextField = new JTextField(20), gbcCD);
        titleTextField.setText(cdToEdit.getTitle()); // Điền thông tin

        gbcCD.gridx = 2;
        gbcCD.gridy = 0;
        gbcCD.insets = labelMargin;
        cdInfoPanel.add(new JLabel("Hãng thu :"), gbcCD);

        gbcCD.gridx = 3;
        gbcCD.gridy = 0;
        gbcCD.insets = fieldMargin;
        recordLabelTextField = new JTextField(20);
        cdInfoPanel.add(recordLabelTextField, gbcCD);
        recordLabelTextField.setText(cdToEdit.getRecordLabel()); // Điền thông tin

        // Hàng 2: Nghệ sĩ - Thể loại
        gbcCD.gridx = 0;
        gbcCD.gridy = 1;
        gbcCD.insets = labelMargin;
        cdInfoPanel.add(new JLabel("Nghệ sĩ :"), gbcCD);

        gbcCD.gridx = 1;
        gbcCD.gridy = 1;
        gbcCD.insets = fieldMargin;
        cdInfoPanel.add(artistTextField = new JTextField(20), gbcCD);
        artistTextField.setText(cdToEdit.getArtists()); // Điền thông tin

        gbcCD.gridx = 2;
        gbcCD.gridy = 1;
        gbcCD.insets = labelMargin;
        cdInfoPanel.add(new JLabel("Thể loại :"), gbcCD);

        gbcCD.gridx = 3;
        gbcCD.gridy = 1;
        gbcCD.insets = fieldMargin;
        genreTextField = new JTextField(20);
        cdInfoPanel.add(genreTextField, gbcCD);
        genreTextField.setText(cdToEdit.getGenre()); // Điền thông tin

        // Hàng 3: Ngày phát hành - Danh sách bài hát
        gbcCD.gridx = 0;
        gbcCD.gridy = 2;
        gbcCD.insets = labelMargin;
        gbcCD.anchor = GridBagConstraints.NORTHWEST; // Neo label lên trên
        cdInfoPanel.add(new JLabel("Ngày phát hành :"), gbcCD);

        gbcCD.gridx = 1;
        gbcCD.gridy = 2;
        gbcCD.insets = fieldMargin;
        gbcCD.anchor = GridBagConstraints.NORTHWEST; // Neo textField lên trên
        releaseDateTextField = new JFormattedTextField();
        releaseDateTextField.setColumns(20);
        releaseDateTextField.setText(cdToEdit.getReleaseDate()); // Điền thông tin
        cdInfoPanel.add(releaseDateTextField, gbcCD);
        gbcCD.anchor = GridBagConstraints.WEST; // Trả lại anchor mặc định

        gbcCD.gridx = 2;
        gbcCD.gridy = 2;
        gbcCD.insets = labelMargin;
        gbcCD.anchor = GridBagConstraints.NORTHWEST; // Neo label lên trên
        cdInfoPanel.add(new JLabel("Danh sách bài hát :"), gbcCD);

        gbcCD.gridx = 3;
        gbcCD.gridy = 2;
        gbcCD.gridwidth = GridBagConstraints.REMAINDER; // Chiếm toàn bộ không gian còn lại bên phải
        gbcCD.gridheight = 2; // Cho phép JTextArea có chiều cao 2 hàng (hoặc hơn nếu cần)
        gbcCD.insets = fieldMargin;
        gbcCD.fill = GridBagConstraints.BOTH; // Lấp đầy cả chiều ngang và dọc
        cdInfoPanel.add(new JScrollPane(trackListTextArea = new JTextArea(3, 20)), gbcCD);
        trackListTextArea.setText(cdToEdit.getTracklist()); // Điền thông tin
        gbcCD.gridwidth = 1; // Trả lại gridwidth mặc định
        gbcCD.gridheight = 1; // Trả lại gridheight mặc định
        gbcCD.fill = GridBagConstraints.HORIZONTAL; // Trả lại fill mặc định

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH; // Fill cả chiều ngang và dọc cho panel thông tin CD
        gbc.weighty = 0.0; // Reset weighty
        contentPanel.add(cdInfoPanel, gbc);
        gbc.gridwidth = 1; // Reset gridwidth
        gbc.fill = GridBagConstraints.HORIZONTAL; // Trả lại fill mặc định

        // --- Thông tin nhập kho ---
        JPanel importInfoPanel = new JPanel(new GridBagLayout());
        importInfoPanel.setBorder(new TitledBorder("Thông tin nhập kho"));
        GridBagConstraints gbcImport = new GridBagConstraints();
        gbcImport.fill = GridBagConstraints.HORIZONTAL;
        gbcImport.weightx = 0.5;
        gbcImport.insets = new Insets(5, 5, 5, 5);

        // Hàng 1: Ngày nhập - Số lượng
        gbcImport.gridx = 0;
        gbcImport.gridy = 0;
        gbcImport.insets = labelMargin;
        importInfoPanel.add(new JLabel("Ngày nhập :"), gbcImport);

        gbcImport.gridx = 1;
        gbcImport.gridy = 0;
        gbcImport.insets = fieldMargin;
        importDateTextField = new JFormattedTextField();
        importDateTextField.setColumns(20);
        importDateTextField.setText(cdToEdit.getWarehouseEntryDate()); // Điền thông tin
        importInfoPanel.add(importDateTextField, gbcImport);

        gbcImport.gridx = 2;
        gbcImport.gridy = 0;
        gbcImport.insets = labelMargin;
        importInfoPanel.add(new JLabel("Số lượng :"), gbcImport);

        gbcImport.gridx = 3;
        gbcImport.gridy = 0;
        gbcImport.insets = fieldMargin;
        quantityTextField = new JTextField(10);
        quantityTextField.setText(String.valueOf(cdToEdit.getQuantity())); // Điền thông tin
        importInfoPanel.add(quantityTextField, gbcImport);

        // Hàng 2: Kích thước - Trọng lượng
        gbcImport.gridx = 0;
        gbcImport.gridy = 1;
        gbcImport.insets = labelMargin;
        importInfoPanel.add(new JLabel("Kích thước :"), gbcImport);

        gbcImport.gridx = 1;
        gbcImport.gridy = 1;
        gbcImport.insets = fieldMargin;
        dimensionsTextField = new JTextField(20);
        dimensionsTextField.setText(cdToEdit.getDimensions()); // Điền thông tin
        importInfoPanel.add(dimensionsTextField, gbcImport);

        gbcImport.gridx = 2;
        gbcImport.gridy = 1;
        gbcImport.insets = labelMargin;
        importInfoPanel.add(new JLabel("Trọng lượng :"), gbcImport);

        gbcImport.gridx = 3;
        gbcImport.gridy = 1;
        gbcImport.insets = fieldMargin;
        weightTextField = new JTextField(10);
        weightTextField.setText(cdToEdit.getWeight()); // Điền thông tin
        importInfoPanel.add(weightTextField, gbcImport);

        // Hàng 3: Giá bán - Giá nhập
        gbcImport.gridx = 0;
        gbcImport.gridy = 2;
        gbcImport.insets = labelMargin;
        importInfoPanel.add(new JLabel("Giá bán :"), gbcImport);

        gbcImport.gridx = 1;
        gbcImport.gridy = 2;
        gbcImport.insets = fieldMargin;
        sellingPriceTextField = new JTextField(10);
        sellingPriceTextField.setText(String.valueOf(cdToEdit.getValue())); // Điền thông tin
        importInfoPanel.add(sellingPriceTextField, gbcImport);

        gbcImport.gridx = 2;
        gbcImport.gridy = 2;
        gbcImport.insets = labelMargin;
        importInfoPanel.add(new JLabel("Giá nhập :"), gbcImport);

        gbcImport.gridx = 3;
        gbcImport.gridy = 2;
        gbcImport.insets = fieldMargin;
        importPriceTextField = new JTextField(10);
        importPriceTextField.setText(String.valueOf(cdToEdit.getPrice())); // Điền thông tin
        importInfoPanel.add(importPriceTextField, gbcImport);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(importInfoPanel, gbc);
        gbc.gridwidth = 1; // Reset gridwidth

        // --- Mô tả ---
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBorder(new TitledBorder("Mô tả"));
        descriptionTextArea = new JTextArea(5, 40);
        descriptionTextArea.setText(cdToEdit.getDescription()); // Điền thông tin
        descriptionPanel.add(new JScrollPane(descriptionTextArea), BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0; // Cho phép mô tả chiếm nhiều không gian dọc
        contentPanel.add(descriptionPanel, gbc);
        gbc.weighty = 0.0; // Reset weighty

        // Buttons Panel ở phía dưới
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        cancelButton = new RoundedButton("Hủy", 10, new Color(255, 77, 77), new Color(204, 0, 0), new Color(204, 0, 0));
        saveButton = new RoundedButton("Lưu", 10, new Color(0, 155, 229), new Color(0, 104, 190), new Color(0, 104, 190));
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners cho nút Lưu và Hủy
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEditedCD();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSaveClicked = false;
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isSaveClicked() { return isSaveClicked; }
    private void saveEditedCD() {
        try {
            // Lấy dữ liệu từ form
            String title = titleTextField.getText().trim();
            String artists = artistTextField.getText().trim();
            String recordLabel = recordLabelTextField.getText().trim();
            String genre = genreTextField.getText().trim();
            String releaseDate = releaseDateTextField.getText().trim(); // Định dạng: YYYY-MM-DD
            String tracklist = trackListTextArea.getText().trim();
            String warehouseEntryDate = importDateTextField.getText().trim(); // Định dạng: YYYY-MM-DD
            int quantity = Integer.parseInt(quantityTextField.getText().trim());
            String dimensions = dimensionsTextField.getText().trim();
            String weight = weightTextField.getText().trim();
            double value = Double.parseDouble(sellingPriceTextField.getText().trim());
            double price = Double.parseDouble(importPriceTextField.getText().trim());
            String description = descriptionTextArea.getText().trim();

            // Kiểm tra dữ liệu bắt buộc
            if (title.isEmpty() || artists.isEmpty() || recordLabel.isEmpty() || genre.isEmpty() || tracklist.isEmpty() || warehouseEntryDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ các trường bắt buộc.", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                return;
            }

            CD cd = new CD();
            cd.setProductId(cdToEdit.getProductId());
            cd.setTitle(title);
            cd.setCategory(cdToEdit.getCategory());
            cd.setPrice(price);
            cd.setValue(value);
            cd.setBarcode(cdToEdit.getBarcode());
            cd.setDescription(description);
            cd.setQuantity(quantity);
            cd.setWeight(weight);
            cd.setDimensions(dimensions);
            cd.setWarehouseEntryDate(warehouseEntryDate);
            cd.setArtists(artists);
            cd.setRecordLabel(recordLabel);
            cd.setTracklist(tracklist);
            cd.setGenre(genre);
            cd.setReleaseDate(releaseDate.isEmpty() ? null : releaseDate);
            
            // Gọi DAO để lưu vào database
            boolean success = CDDAO.getInstance().updateCD(cd);
            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin CD thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                isSaveClicked = true;
                dispose(); // Đóng dialog
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin CD vào cơ sở dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho số lượng, giá và trọng lượng.", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    