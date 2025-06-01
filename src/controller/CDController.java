package controller;

import model.dao.*;
import model.entity.*;
import view.Panel.*;
import view.dialog.addDialog.*;

import javax.swing.*;
import java.util.List;

public class CDController {
    private final CDDAO CDDAO = new CDDAO();
    private final CDPanel CDPanel;

    public CDController(CDPanel CDPanel) {
        this.CDPanel = CDPanel;
    }

    public void loadCDs() {
        List<CD> CDs = CDDAO.getAllCDs();
        CDPanel.updateCDTable(CDs);
    }

    public void openAddCDDialog(JFrame parentFrame) {
        AddCDDialog dialog = new AddCDDialog(parentFrame, "Thêm CD", true);
        dialog.setVisible(true);
        if (dialog.isSaveClicked()) {
            loadCDs();
        }
    }
}
