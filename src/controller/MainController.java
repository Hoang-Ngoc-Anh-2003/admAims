package controller;

import model.dao.*;
import view.MainFrame;

public class MainController {
    private MainFrame mainFrame;

    public MainController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public int getBookCount() {
        return BookDAO.getProductCount("Books");
    }

    public int getDVDCount() {
        return DVDDAO.getProductCount("DVDs");
    }

    public int getCDCount() {
        return CDDAO.getProductCount("CDs");
    }

    public int getLPCount() {
        return LPDAO.getProductCount("LPs");
    }

    public void showPanel(String panelName, String title) {
        mainFrame.setMainPanel(panelName, title);
    }
}
