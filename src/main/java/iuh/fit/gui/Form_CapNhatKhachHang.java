package iuh.fit.gui;

import model.KhachHang;
import services.KhachHangService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.Naming;
import java.util.List;

public class Form_CapNhatKhachHang extends JPanel {
    private JTextField txtMaKH, txtTenKH, txtDienThoai, txtEmail;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnXoa;
    private JTextField txtDiaChi, txtNgaySinh;
    private List<KhachHang> dsKH;


    public Form_CapNhatKhachHang() {
        // Tiêu đề
        JLabel lblTitle = new JLabel("Khách hàng");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.BLUE);

        // Panel nhập liệu
        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBorder(new TitledBorder("Thông tin khách hàng"));
        pnlInput.setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mã KH
        gbc.weightx = 0.3;
        gbc.gridx = 0; gbc.gridy = 0;
        pnlInput.add(new JLabel("Mã KH:"), gbc);
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        txtMaKH = new JTextField(15);
        pnlInput.add(txtMaKH, gbc);

        // Tên KH
        gbc.weightx = 0.3;
        gbc.gridx = 0; gbc.gridy = 1;
        pnlInput.add(new JLabel("Tên KH:"), gbc);
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        txtTenKH = new JTextField(15);
        pnlInput.add(txtTenKH, gbc);

        // Điện thoại
        gbc.weightx = 0.3;
        gbc.gridx = 0; gbc.gridy = 2;
        pnlInput.add(new JLabel("Điện Thoại:"), gbc);
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        txtDienThoai = new JTextField(15);
        pnlInput.add(txtDienThoai, gbc);

        // Email
        gbc.weightx = 0.3;
        gbc.gridx = 0; gbc.gridy = 3;
        pnlInput.add(new JLabel("Email:"), gbc);
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        txtEmail = new JTextField(15);
        pnlInput.add(txtEmail, gbc);
        // Địa chỉ
        gbc.weightx = 0.3;
        gbc.gridx = 0; gbc.gridy = 4;
        pnlInput.add(new JLabel("Địa chỉ:"), gbc);
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        txtDiaChi = new JTextField(15);
        pnlInput.add(txtDiaChi, gbc);

// Ngày sinh
        gbc.weightx = 0.3;
        gbc.gridx = 0; gbc.gridy = 5;
        pnlInput.add(new JLabel("Ngày sinh (yyyy-mm-dd):"), gbc);
        gbc.weightx = 0.7;
        gbc.gridx = 1;
        txtNgaySinh = new JTextField(15);
        pnlInput.add(txtNgaySinh, gbc);

        // Các nút chức năng
        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(new Dimension(150, 30));
        btnThem.setBackground(new Color(100, 149, 237));
        btnThem.setForeground(Color.WHITE);

        btnXoa = new JButton("Xóa");
        btnXoa.setPreferredSize(new Dimension(150, 30));
        btnXoa.setBackground(new Color(100, 149, 237));
        btnXoa.setForeground(Color.WHITE);

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButton.setBackground(new Color(240, 248, 255));
        pnlButton.add(btnThem);
        pnlButton.add(btnXoa);

        // Bảng khách hàng
        String[] columnNames = {"Mã khách hàng", "Tên khách hàng", "Số Điện Thoại", "Email", "Địa chỉ", "Ngày sinh"};
        tableModel = new DefaultTableModel(columnNames, 0);
        updateTableKH();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Tổng thể layout
        setLayout(new BorderLayout());
        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.setBackground(new Color(255, 250, 240));
        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        pnlNorth.add(pnlInput, BorderLayout.CENTER);
        pnlNorth.add(pnlButton, BorderLayout.SOUTH);

        add(pnlNorth, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updateTableKH()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        tableModel.setRowCount(0);
        try {
            KhachHangService thuocService = (KhachHangService) Naming.lookup("rmi://localhost:9090/khachHangService");
            dsKH = thuocService.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ thuốc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // Thêm dữ liệu mới vào bảng thuốc
        for (KhachHang kh : dsKH) {
            Object[] rowData = {
                    kh.getMaKH(),
                    kh.getTenKH(),
                    kh.getSoDienThoai(),
                    kh.getEmail(),
                    kh.getDiaChi(),
                    kh.getNgaySinh()
            };
            tableModel.addRow(rowData);
        }
    }
}
