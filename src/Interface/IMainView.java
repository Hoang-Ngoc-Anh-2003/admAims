package Interface;
import java.awt.event.ActionListener;

public interface IMainView {
    void setMainPanel(String panelName, String title);
    void updateProductCounts(int book, int dvd, int cd, int lp);

    void setHomeButtonListener(ActionListener listener);
    void setBooksButtonListener(ActionListener listener);
    void setDVDsButtonListener(ActionListener listener);
    void setCDsButtonListener(ActionListener listener);
    void setLPsButtonListener(ActionListener listener);
}