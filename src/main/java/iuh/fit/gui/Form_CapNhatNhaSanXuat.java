package iuh.fit.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_CapNhatNhaSanXuat extends JPanel implements ActionListener {
    private JTextField txtMaNSX;
    private JTextField txtTenNSX;
    private JTextField txtWebsite;
    private JComboBox<String> cboQuocGia;
    private JButton btnCapNhat;
    private JButton btnHuy;
    private JButton btnThem;
    private JButton btnXoa;
    private JTable table;
    private DefaultTableModel tableModel;

    public Form_CapNhatNhaSanXuat() {
        // Giao diện chính
        setLayout(new BorderLayout());


        JLabel lblTitle = new JLabel("Cập Nhật Nhà Sản Xuất");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.BLUE);

        // Panel nhập dữ liệu
        JPanel pnlInput = new JPanel();
        pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.Y_AXIS)); // Dùng BoxLayout theo chiều dọc
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin nhà sản xuất"));
        pnlInput.setBackground(new Color(240, 248, 255));

        // Mã NSX
        JLabel lblMaNSX = new JLabel("Mã NSX:");
        lblMaNSX.setPreferredSize(new Dimension(100, 30));
        txtMaNSX = new JTextField(20);
        txtMaNSX.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPanel pnlMaNSX = new JPanel();
        pnlMaNSX.setLayout(new BoxLayout(pnlMaNSX, BoxLayout.X_AXIS));
        pnlMaNSX.setBackground(new Color(240, 248, 255));
        pnlMaNSX.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlMaNSX.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlMaNSX.add(lblMaNSX);
        pnlMaNSX.add(Box.createHorizontalGlue());
        
        pnlMaNSX.add(txtMaNSX);
        pnlMaNSX.add(Box.createRigidArea(new Dimension(200, 0)));

        // Tên NSX
        JLabel lblTenNSX = new JLabel("Tên NSX:");
        lblTenNSX.setPreferredSize(new Dimension(100, 30));
        txtTenNSX = new JTextField(20);
        txtTenNSX.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPanel pnlTenNSX = new JPanel();
        pnlTenNSX.setLayout(new BoxLayout(pnlTenNSX, BoxLayout.X_AXIS));
        pnlTenNSX.setBackground(new Color(240, 248, 255));
        pnlTenNSX.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlTenNSX.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlTenNSX.add(lblTenNSX);
        pnlTenNSX.add(Box.createHorizontalGlue());
        pnlTenNSX.add(txtTenNSX);
        pnlTenNSX.add(Box.createRigidArea(new Dimension(200, 0)));

        // Website
        JLabel lblWebsite = new JLabel("Website:");
        lblWebsite.setPreferredSize(new Dimension(100, 30));
        txtWebsite = new JTextField(20);
        txtWebsite.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPanel pnlWebsite = new JPanel();
        pnlWebsite.setLayout(new BoxLayout(pnlWebsite, BoxLayout.X_AXIS));
        pnlWebsite.setBackground(new Color(240, 248, 255));
        pnlWebsite.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlWebsite.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlWebsite.add(lblWebsite);
        pnlWebsite.add(Box.createHorizontalGlue());
        pnlWebsite.add(txtWebsite);
        pnlWebsite.add(Box.createRigidArea(new Dimension(200, 0)));

        // Quốc Gia
        JLabel lblQuocGia = new JLabel("Quốc Gia:");
        lblQuocGia.setPreferredSize(new Dimension(100, 30));
        cboQuocGia = new JComboBox<>(new String[]{"Việt Nam", "Mỹ", "Nhật Bản", "Hàn Quốc"});
        cboQuocGia.setMaximumSize(new Dimension(150, 30));

        JPanel pnlQuocGia = new JPanel();
        pnlQuocGia.setLayout(new BoxLayout(pnlQuocGia, BoxLayout.X_AXIS));
        pnlQuocGia.setBackground(new Color(240, 248, 255));
        pnlQuocGia.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlQuocGia.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlQuocGia.add(lblQuocGia);
        pnlQuocGia.add(Box.createHorizontalGlue());
        pnlQuocGia.add(cboQuocGia);
        pnlQuocGia.add(Box.createRigidArea(new Dimension(570, 0)));

        // Thêm các thành phần vào panel nhập liệu
        pnlInput.add(pnlMaNSX);
        pnlInput.add(pnlTenNSX);
        pnlInput.add(pnlWebsite);
        pnlInput.add(pnlQuocGia);

        // Buttons Panel
        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));
        pnlButton.setBackground(new Color(240, 248, 255));
        pnlButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        
        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(new Dimension(150, 30));
        btnThem.setBackground(new Color(100, 100, 240));
        btnThem.setForeground(Color.WHITE);
        pnlButton.add(Box.createHorizontalGlue());
        pnlButton.add(btnThem);
        pnlButton.add(Box.createRigidArea(new Dimension(10, 0)));

        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setPreferredSize(new Dimension(150, 30));
        btnCapNhat.setBackground(new Color(100, 149, 237));
        btnCapNhat.setForeground(Color.WHITE);
        
        pnlButton.add(btnCapNhat);
        pnlButton.add(Box.createRigidArea(new Dimension(10, 0)));

        btnHuy = new JButton("Hủy");
        btnHuy.setPreferredSize(new Dimension(150, 30));
        btnHuy.setBackground(new Color(255, 69, 0));
        btnHuy.setForeground(Color.WHITE);
        pnlButton.add(btnHuy);
        pnlButton.add(Box.createRigidArea(new Dimension(10, 0)));
        
        btnXoa = new JButton("Xóa");
        btnXoa.setPreferredSize(new Dimension(150, 30));
        btnXoa.setBackground(new Color(250, 75, 0));
        btnXoa.setForeground(Color.WHITE);
        pnlButton.add(btnXoa);
        pnlButton.add(Box.createHorizontalGlue());

        pnlInput.add(pnlButton);

        // Table for displaying data
        String[] columnNames = {"Mã NSX", "Tên NSX", "Website", "Quốc Gia"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(800, 300));

        // Add components to layout
        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.setBackground(new Color(255, 250, 240));
        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        pnlNorth.add(pnlInput, BorderLayout.CENTER);

        add(pnlNorth, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        // Add button actions
        btnCapNhat.addActionListener(this);
        btnHuy.addActionListener(this);
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();
        if (o.equals(btnCapNhat)) {
            // Xử lý cập nhật dữ liệu

        }
        
        if (o.equals(btnHuy)) {
            // Làm sạch các trường nhập liệu

        }
        
        if(o.equals(btnThem)) {

        }
        
        if(o.equals(btnXoa)) {

        }
    }
    
    private void xoaNSX() {

    }
    
    private void themNSX() {

    }
    
    private void capNhat() {

    }

    private void clearInputFields() {
        txtMaNSX.setText("");
        txtTenNSX.setText("");
        txtWebsite.setText("");
        cboQuocGia.setSelectedIndex(0);
    }

}