package controller;

import model.dao.*;
import model.entity.*;
import view.Panel.*;
import view.dialog.addDialog.*;

import javax.swing.*;
import java.util.List;

public class DVDController {
    private final DVDDAO DVDDAO = new DVDDAO();
    private final DVDPanel DVDPanel;

    public DVDController(DVDPanel DVDPanel) {
        this.DVDPanel = DVDPanel;
    }

    public void loadDVDs() {
        List<DVD> DVDs = DVDDAO.getAllDVDs();
        DVDPanel.updateDVDTable(DVDs);
    }

    public void openAddDVDDialog(JFrame parentFrame) {
        AddDVDDialog dialog = new AddDVDDialog(parentFrame, "Thêm DVD", true);
        dialog.setVisible(true);
        if (dialog.isSaveClicked()) {
            loadDVDs();
        }
    }
}