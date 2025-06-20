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

public class LPController {
    private final LPDAO LPDAO;
    private final LPPanel LPPanel;

    public LPController(LPPanel LPPanel) {
        this.LPDAO = new LPDAO();
        this.LPPanel = LPPanel;
        setupEventListeners();
        loadLPs();
    }

    private void setupEventListeners() {
        LPPanel.addAddButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy JFrame cha của LPPanel để hiển thị dialog
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(LPPanel);
                openAddLPDialog(parentFrame);
            }
        });
    }

    public void loadLPs() {
        List<LP> LPs = LPDAO.getAllLPs();
        LPPanel.updateLPTable(LPs);
    }

    public void openAddLPDialog(JFrame parentFrame) {
        AddLPDialog dialog = new AddLPDialog(parentFrame, "Thêm LP", true);
        AddLPController addLPController = new AddLPController(dialog);
        dialog.setVisible(true);
        loadLPs();
    }

    public void openEditBookDialog(int LPId) {
    }
}
