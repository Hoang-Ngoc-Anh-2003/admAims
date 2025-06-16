package controller;

import model.dao.*;
import model.entity.*;
import view.dialog.editDialog.*;
import Interface.ReloadablePanel;

import javax.swing.*;

public class EditProductController {

    public static void openEditDialog(int productId, JFrame parentFrame, ReloadablePanel reloadablePanel) {
        String category = ProductDAO.getInstance().getProductCategory(productId);

        if (category == null) {
            JOptionPane.showMessageDialog(null, "Không xác định được loại sản phẩm.");
            return;
        }

        switch (category.toLowerCase()) {
            case "book":
                Book book = BookDAO.getInstance().getBookInfor(productId);
                EditBookDialog editBookDialog = new EditBookDialog(parentFrame, "Book Edit", true, book);
                editBookDialog.setVisible(true);
                if (editBookDialog.isSaveClicked()) {
                    reloadablePanel.reloadData();
                }
                break;
            case "cd":
                CD cd = CDDAO.getInstance().getCDInfor(productId);
                EditCDDialog editCDDialog = new EditCDDialog(parentFrame, "CD Edit", true, cd);
                editCDDialog.setVisible(true);
                if (editCDDialog.isSaveClicked()) {
                    reloadablePanel.reloadData();
                }
                break;
            case "dvd":
                DVD dvd = DVDDAO.getInstance().getDVDInfor(productId);
                EditDVDDialog editDVDDialog = new EditDVDDialog(parentFrame, "DVD Edit", true, dvd);
                editDVDDialog.setVisible(true);
                if (editDVDDialog.isSaveClicked()) {
                    reloadablePanel.reloadData();
                }
                break;
            case "lp":
                LP lp = LPDAO.getInstance().getLPInfor(productId);
                EditLPDialog editLPDialog = new EditLPDialog(parentFrame, "LP Edit", true, lp);
                editLPDialog.setVisible(true);
                if (editLPDialog.isSaveClicked()) {
                    reloadablePanel.reloadData();
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Loại sản phẩm không hợp lệ.");
                break;
        }
    }
}
