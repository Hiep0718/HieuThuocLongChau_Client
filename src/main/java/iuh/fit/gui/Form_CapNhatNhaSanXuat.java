package iuh.fit.gui;

import model.NhaCungCap;
import services.NhaSanXuatService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

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
    private JTextField txtEmail;
    private JTextField txtDiaChi;
    private JTextField txtSoDienThoai;
    private List<NhaCungCap> dsNSX;

    public Form_CapNhatNhaSanXuat() {
        // Giao diện chính
        setLayout(new BorderLayout());

        dsNSX = new ArrayList<>();

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
        txtMaNSX.setEditable(false);
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

        // Số Điện Thoại
        JLabel lblSoDienThoai = new JLabel("Số Điện Thoại:");
        lblSoDienThoai.setPreferredSize(new Dimension(100, 30));
        txtSoDienThoai = new JTextField(20);
        txtSoDienThoai.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPanel pnlSoDienThoai = new JPanel();
        pnlSoDienThoai.setLayout(new BoxLayout(pnlSoDienThoai, BoxLayout.X_AXIS));
        pnlSoDienThoai.setBackground(new Color(240, 248, 255));
        pnlSoDienThoai.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlSoDienThoai.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlSoDienThoai.add(lblSoDienThoai);
        pnlSoDienThoai.add(Box.createHorizontalGlue());
        pnlSoDienThoai.add(txtSoDienThoai);
        pnlSoDienThoai.add(Box.createRigidArea(new Dimension(200, 0)));

// Add the new panel for Số Điện Thoại


        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setPreferredSize(new Dimension(100, 30));
        txtEmail = new JTextField(20);
        txtEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPanel pnlEmail = new JPanel();
        pnlEmail.setLayout(new BoxLayout(pnlEmail, BoxLayout.X_AXIS));
        pnlEmail.setBackground(new Color(240, 248, 255));
        pnlEmail.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlEmail.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlEmail.add(lblEmail);
        pnlEmail.add(Box.createHorizontalGlue());
        pnlEmail.add(txtEmail);
        pnlEmail.add(Box.createRigidArea(new Dimension(200, 0)));

// Address
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setPreferredSize(new Dimension(100, 30));
        txtDiaChi = new JTextField(20);
        txtDiaChi.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JPanel pnlDiaChi = new JPanel();
        pnlDiaChi.setLayout(new BoxLayout(pnlDiaChi, BoxLayout.X_AXIS));
        pnlDiaChi.setBackground(new Color(240, 248, 255));
        pnlDiaChi.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlDiaChi.add(Box.createRigidArea(new Dimension(200, 0)));
        pnlDiaChi.add(lblDiaChi);
        pnlDiaChi.add(Box.createHorizontalGlue());
        pnlDiaChi.add(txtDiaChi);
        pnlDiaChi.add(Box.createRigidArea(new Dimension(200, 0)));

// Add these panels to the pnlInput panel
        pnlInput.add(pnlEmail);
        pnlInput.add(pnlDiaChi);

        // Thêm các thành phần vào panel nhập liệu
        pnlInput.add(pnlMaNSX);
        pnlInput.add(pnlTenNSX);
        pnlInput.add(pnlWebsite);
        pnlInput.add(pnlSoDienThoai);

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
        String[] columnNames = {"Mã NSX", "Tên NSX", "Website", "Email", "Địa chỉ", "Số điện thoại"};
        tableModel = new DefaultTableModel(columnNames, 0);
        updateTableNCC();
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


        }
        
        if (o.equals(btnHuy)) {
            // Làm sạch các trường nhập liệu

        }
        
        if(o.equals(btnThem)) {
            String tenNSX = txtTenNSX.getText();
            String website = txtWebsite.getText();
            String email = txtEmail.getText();
            String diaChi = txtDiaChi.getText();
            String sdt = txtSoDienThoai.getText();

            // Tạo đối tượng Nhà Sản Xuất
            NhaCungCap nsx = new NhaCungCap();
            nsx.setTenNCC(tenNSX);
            nsx.setWebsite(website);
            nsx.setEmail(email);
            nsx.setDiaChi(diaChi);
            nsx.setSoDienThoai(sdt);

            try {
                NhaSanXuatService nsxService = (NhaSanXuatService) Naming.lookup("rmi://localhost:9090/nhaSanXuatService");

                // Gọi phương thức thêm nhà sản xuất từ dịch vụ
                if (nsxService.themNhaSanXuat(nsx)) {
                    JOptionPane.showMessageDialog(this, "Lưu Nhà Sản Xuất thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    // Cập nhật lại bảng nhà sản xuất sau khi thêm
                    updateTableNCC();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm Nhà Sản Xuất thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi kết nối RMI!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        }
        
        if(o.equals(btnXoa)) {

        }
    }


    
    private void xoaNSX() {

    }

    
    private void capNhat() {

    }

    private void clearInputFields() {
        txtMaNSX.setText("");
        txtTenNSX.setText("");
        txtWebsite.setText("");
        cboQuocGia.setSelectedIndex(0);
    }

    private void updateTableNCC()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        tableModel.setRowCount(0);
        try {
            NhaSanXuatService thuocService = (NhaSanXuatService) Naming.lookup("rmi://localhost:9090/nhaSanXuatService");
            dsNSX = thuocService.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ thuốc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // Thêm dữ liệu mới vào bảng thuốc
        for (NhaCungCap ncc : dsNSX) {
            Object[] rowData = {
                    ncc.getMaNCC(),
                    ncc.getTenNCC(),
                    ncc.getWebsite(),
                    ncc.getEmail(),
                    ncc.getDiaChi(),
                    ncc.getSoDienThoai()
            };
            tableModel.addRow(rowData);
        }
    }

}