package controller;

import Interface.IMainView;
import model.dao.*;

public class MainController {
    private final IMainView mainView;

    public MainController(IMainView mainView) {
        this.mainView = mainView;
    }
    
    public void setupEventListeners() {
        mainView.setHomeButtonListener(e -> showPanel("Home", "Trang chủ"));
        mainView.setBooksButtonListener(e -> showPanel("Books", "Danh sách Book"));
        mainView.setDVDsButtonListener(e -> showPanel("DVDs", "Danh sách DVD"));
        mainView.setCDsButtonListener(e -> showPanel("CDs", "Danh sách CD"));
        mainView.setLPsButtonListener(e -> showPanel("LPs", "Danh sách LP"));
        updateProductCounts();
    }

    private void showPanel(String panelName, String title) {
        mainView.setMainPanel(panelName, title);
        updateProductCounts();
    }

    private void updateProductCounts() {
        int books = BookDAO.getProductCount("Books");
        int dvds = DVDDAO.getProductCount("DVDs");
        int cds = CDDAO.getProductCount("CDs");
        int lps = LPDAO.getProductCount("LPs");
        mainView.updateProductCounts(books, dvds, cds, lps);
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
}
