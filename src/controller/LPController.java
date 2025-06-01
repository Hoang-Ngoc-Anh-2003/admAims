package controller;

import model.dao.*;
import model.entity.*;
import view.Panel.*;
import view.dialog.addDialog.*;

import javax.swing.*;
import java.util.List;

public class LPController {
    private final LPDAO LPDAO = new LPDAO();
    private final LPPanel LPPanel;

    public LPController(LPPanel LPPanel) {
        this.LPPanel = LPPanel;
    }

    public void loadLPs() {
        List<LP> LPs = LPDAO.getAllLPs();
        LPPanel.updateLPTable(LPs);
    }

    public void openAddLPDialog(JFrame parentFrame) {
        AddLPDialog dialog = new AddLPDialog(parentFrame, "Thêm LP", true);
        dialog.setVisible(true);
        if (dialog.isSaveClicked()) {
            loadLPs();
        }
    }
}
