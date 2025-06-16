package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import view.Panel.*;
import Interface.*;

public class MainFrame extends JFrame implements IMainView {
	private static final long serialVersionUID = 1L; 

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JLabel lblTitle;

    private int bookCount;
    private int dvdCount;
    private int cdCount;
    private int lpCount;

    private JButton btnHome;
    private JButton btnBooks;
    private JButton btnCDs;
    private JButton btnDVDs;
    private JButton btnLPs;

    private JButton btnBook;
    private JButton btnCD;
    private JButton btnDVD;
    private JButton btnLP;

    private BookPanel bookPanel;
    private CDPanel cdPanel;
    private DVDPanel dvdPanel;
    private LPPanel lpPanel;
    private JPanel homePanel;
    
    public MainFrame() {

        setTitle("AIMS");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);
        
        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(250, getHeight()));
        sidebar.setBackground(new Color(22, 33, 53));
        
        Font sidebarFont = new Font("Arial", Font.BOLD, 14);

        btnHome = createSidebarButton("     \uD83C\uDFE0    Trang chủ", false);
        
        JLabel lblProductManagement = new JLabel("Quản lý sản phẩm");
        lblProductManagement.setFont(sidebarFont);
        lblProductManagement.setForeground(Color.WHITE);
        lblProductManagement.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
        
        btnBooks = createSidebarButton("     📚     Danh sách Book", false);
        btnDVDs = createSidebarButton("     🎬     Danh sách DVD", false);
        btnCDs = createSidebarButton("     🎶     Danh sách CD", false);
        btnLPs = createSidebarButton("     📻     Danh sách LP", false);
        
        sidebar.add(btnHome);
        sidebar.add(lblProductManagement);
        sidebar.add(btnBooks);
        sidebar.add(btnDVDs);
        sidebar.add(btnCDs);
        sidebar.add(btnLPs);
        
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(950, 70)); // Chỉ chiếm phần còn lại sau sidebar
        
        // AIMS Title Panel in Header
        JPanel aimsHeaderPanel = new JPanel();
        aimsHeaderPanel.setPreferredSize(new Dimension(250, 70));
        aimsHeaderPanel.setBackground(new Color(22, 33, 53));
        aimsHeaderPanel.setLayout(new GridBagLayout());
        
        JLabel lblAims = new JLabel("AIMS", SwingConstants.CENTER);
        lblAims.setForeground(Color.WHITE);
        lblAims.setFont(new Font("Arial", Font.BOLD, 20));
        aimsHeaderPanel.add(lblAims);
        
        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        titlePanel.setBackground(new Color(0, 123, 255));
        
        lblTitle = new JLabel("Trang chủ");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        
        titlePanel.add(lblTitle);
        
        header.add(aimsHeaderPanel, BorderLayout.WEST);
        header.add(titlePanel, BorderLayout.CENTER);
        
        // Main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(240, 242, 245));
        
        homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(2, 2, 20, 20));
        homePanel.setBackground(new Color(240, 242, 245));
        
        btnBook = createCategoryButton("Book", bookCount);
        btnDVD = createCategoryButton("DVD", dvdCount);
        btnCD = createCategoryButton("CD", cdCount);
        btnLP = createCategoryButton("LP", lpCount);
        
        homePanel.add(btnBook);
        homePanel.add(btnDVD);
        homePanel.add(btnCD);
        homePanel.add(btnLP);
        
        // them trang danh sach
        bookPanel = new BookPanel();
        cdPanel = new CDPanel();
        dvdPanel = new DVDPanel();
        lpPanel = new LPPanel();
        
        mainPanel.add(homePanel, "Home");
        mainPanel.add(bookPanel, "Books");
        mainPanel.add(dvdPanel, "DVDs");
        mainPanel.add(cdPanel, "CDs");
        mainPanel.add(lpPanel, "LPs");
        
        contentPane.add(sidebar, BorderLayout.WEST);
        contentPane.add(header, BorderLayout.NORTH);
        contentPane.add(mainPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private JButton createSidebarButton(String name, boolean hasIcon) {
        JButton button = new JButton(name);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(250, 40));
        button.setBackground(new Color(33, 43, 54));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        if (!hasIcon) {
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
        }
        return button;
    }
    
    private JButton createCategoryButton(String category, int count) {
        JButton button = new JButton("<html><center>" + category + "<br>" + count + "</center></html>");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(180, 120));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        return button;
    }
    
    // ---------------- Interface methods -------------
    @Override
    public void setBooksButtonListener(ActionListener listener) {
        btnBooks.addActionListener(listener);
    }

    @Override
    public void setDVDsButtonListener(ActionListener listener) {
        btnDVDs.addActionListener(listener);
    }

    @Override
    public void setCDsButtonListener(ActionListener listener) {
        btnCDs.addActionListener(listener);
    }

    @Override
    public void setLPsButtonListener(ActionListener listener) {
        btnLPs.addActionListener(listener);
    }

    @Override
    public void setHomeButtonListener(ActionListener listener) {
        btnHome.addActionListener(listener);
    }

    @Override
    public void setMainPanel(String panelName, String title) {
        cardLayout.show(mainPanel, panelName);
        lblTitle.setText(title);
    }

    @Override
    public void updateProductCounts(int book, int dvd, int cd, int lp){
        this.bookCount = book;
        this.dvdCount = dvd;
        this.cdCount = cd;
        this.lpCount = lp;        
        
        btnBook.setText("<html><center>Book<br>" + bookCount + "</center></html>");
        btnDVD.setText("<html><center>DVD<br>" + dvdCount + "</center></html>");
        btnCD.setText("<html><center>CD<br>" + cdCount + "</center></html>");
        btnLP.setText("<html><center>LP<br>" + lpCount + "</center></html>");

        //reload view
        homePanel.revalidate();
        homePanel.repaint();
    }

}
