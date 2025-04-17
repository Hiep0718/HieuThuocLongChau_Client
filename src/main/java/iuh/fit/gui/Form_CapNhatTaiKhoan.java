package iuh.fit.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_CapNhatTaiKhoan extends JPanel implements ActionListener {
    private JTextField txtID;
    private JTextField txtMaTaiKhoan;
    private JPasswordField txtMatKhau;
    private JComboBox<String> cboTrangThai;
    private JButton btnCapNhat;
    private JButton btnHuy;
    private JButton btnThem;
    private JButton btnXoa;
    private JTable table;
    private DefaultTableModel tableModel;

    public Form_CapNhatTaiKhoan() {
        // Giao diện chính
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Cập Nhật Tài Khoản");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.BLUE);

        // Panel nhập dữ liệu
        JPanel pnlInput = new JPanel();
        pnlInput.setLayout(new BoxLayout(pnlInput, BoxLayout.Y_AXIS)); // Dùng BoxLayout theo chiều dọc
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin tài khoản"));
        pnlInput.setBackground(new Color(240, 248, 255));

        // ID
        JLabel lblID = new JLabel("ID:");
        lblID.setPreferredSize(new Dimension(100, 30));
        txtID = new JTextField(20);
        txtID.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPanel pnlID = new JPanel();
        pnlID.setLayout(new BoxLayout(pnlID, BoxLayout.X_AXIS));
        pnlID.setBackground(new Color(240, 248, 255));
        pnlID.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlID.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlID.add(lblID);
        pnlID.add(Box.createHorizontalGlue());
        pnlID.add(txtID);
        pnlID.add(Box.createRigidArea(new Dimension(200, 0)));

        // Mã tài khoản
        JLabel lblMaTaiKhoan = new JLabel("Mã tài khoản:");
        lblMaTaiKhoan.setPreferredSize(new Dimension(100, 30));
        txtMaTaiKhoan = new JTextField(20);
        txtMaTaiKhoan.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPanel pnlMaTaiKhoan = new JPanel();
        pnlMaTaiKhoan.setLayout(new BoxLayout(pnlMaTaiKhoan, BoxLayout.X_AXIS));
        pnlMaTaiKhoan.setBackground(new Color(240, 248, 255));
        pnlMaTaiKhoan.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlMaTaiKhoan.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlMaTaiKhoan.add(lblMaTaiKhoan);
        pnlMaTaiKhoan.add(Box.createHorizontalGlue());
        pnlMaTaiKhoan.add(txtMaTaiKhoan);
        pnlMaTaiKhoan.add(Box.createRigidArea(new Dimension(200, 0)));

        // Mật khẩu
        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setPreferredSize(new Dimension(100, 30));
        txtMatKhau = new JPasswordField(20);
        txtMatKhau.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPanel pnlMatKhau = new JPanel();
        pnlMatKhau.setLayout(new BoxLayout(pnlMatKhau, BoxLayout.X_AXIS));
        pnlMatKhau.setBackground(new Color(240, 248, 255));
        pnlMatKhau.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlMatKhau.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlMatKhau.add(lblMatKhau);
        pnlMatKhau.add(Box.createHorizontalGlue());
        pnlMatKhau.add(txtMatKhau);
        pnlMatKhau.add(Box.createRigidArea(new Dimension(200, 0)));

        // Trạng thái
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        lblTrangThai.setPreferredSize(new Dimension(100, 30));
        cboTrangThai = new JComboBox<>(new String[]{"Hoạt động", "Vô hiệu hóa"});
        cboTrangThai.setMaximumSize(new Dimension(150, 30));

        JPanel pnlTrangThai = new JPanel();
        pnlTrangThai.setLayout(new BoxLayout(pnlTrangThai, BoxLayout.X_AXIS));
        pnlTrangThai.setBackground(new Color(240, 248, 255));
        pnlTrangThai.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlTrangThai.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlTrangThai.add(lblTrangThai);
        pnlTrangThai.add(Box.createHorizontalGlue());
        pnlTrangThai.add(cboTrangThai);
        pnlTrangThai.add(Box.createRigidArea(new Dimension(570, 0)));

        // Thêm các thành phần vào panel nhập liệu
        pnlInput.add(pnlID);
        pnlInput.add(pnlMaTaiKhoan);
        pnlInput.add(pnlMatKhau);
        pnlInput.add(pnlTrangThai);

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
        String[] columnNames = {"ID", "Mã tài khoản", "Mật khẩu", "Trạng thái"};
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
            capNhat();
        } 
        
        if (o.equals(btnHuy)) {
            // Làm sạch các trường nhập liệu
            clearInputFields();
        }
        if(o.equals(btnThem)) {
        	themVaoBang();
        }
        if(o.equals(btnXoa)) {
        	xoaTaiKhoan();
        }
    }
    
    private void themVaoBang(){

    }
    
    private void capNhat() {

    }
    
    private void xoaTaiKhoan() {
    	
    }

    private void clearInputFields() {
        txtID.setText("");
        txtMaTaiKhoan.setText("");
        txtMatKhau.setText("");
        cboTrangThai.setSelectedIndex(0);
    }

}
