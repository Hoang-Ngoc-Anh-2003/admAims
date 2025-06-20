package controller.PanelControler;

import model.dao.*;
import model.entity.*;
import view.Panel.*;
import view.dialog.addDialog.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.AddProductControler.*;

import java.util.List;

public class CDController {
    private final CDDAO CDDAO;
    private final CDPanel CDPanel;

    public CDController(CDPanel CDPanel) {
        this.CDDAO = new CDDAO();
        this.CDPanel = CDPanel;
        setupEventListeners();
        loadCDs();
    }

    private void setupEventListeners() {
        CDPanel.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy JFrame cha của LPPanel để hiển thị dialog
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(CDPanel);
                openAddCDDialog(parentFrame);
            }
        });
    }

    public void loadCDs() {
        List<CD> CDs = CDDAO.getAllCDs();
        CDPanel.updateCDTable(CDs);
    }

    public void openAddCDDialog(JFrame parentFrame) {
        AddCDDialog dialog = new AddCDDialog(parentFrame, "Thêm CD", true);
        AddCDController addCDController = new AddCDController(dialog);
        dialog.setVisible(true);
        loadCDs();
    }

    public void openEditBookDialog(int CDId) {
    }
}