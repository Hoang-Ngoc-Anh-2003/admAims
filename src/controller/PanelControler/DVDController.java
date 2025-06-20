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

public class DVDController {
    private final DVDDAO DVDDAO ;
    private final DVDPanel DVDPanel;

    public DVDController(DVDPanel DVDPanel) {
        this.DVDDAO = new DVDDAO();
        this.DVDPanel = DVDPanel;
        setupEventListeners();
        loadDVDs();
    }
    private void setupEventListeners() {
        DVDPanel.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy JFrame cha của LPPanel để hiển thị dialog
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(DVDPanel);
                openAddDVDDialog(parentFrame);
            }
        });
    }
    
    public void loadDVDs() {
        List<DVD> DVDs = DVDDAO.getAllDVDs();
        DVDPanel.updateDVDTable(DVDs);
    }

    public void openAddDVDDialog(JFrame parentFrame) {
        AddDVDDialog dialog = new AddDVDDialog(parentFrame, "Thêm DVD", true);
        AddDVDController addDVDController = new AddDVDController(dialog);
        dialog.setVisible(true);
        loadDVDs();
    }

    public void openEditBookDialog(int DVDId) {
    }
}