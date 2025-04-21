package iuh.fit.gui;

import model.NhanVien;
import model.Thuoc;
import services.NhanVienService;
import services.ThuocService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Form_CapNhatNhanVien extends JPanel implements ActionListener {
    private JTextField txtMaNV, txtTenNV, txtTuoi, txtGioiTinh, txtDienThoai, txtEmail, txtDiaChi;
    private JComboBox<String> cboTrangThai, cboChucVu;
    private JButton btnCapNhat, btnHuy, btnThem, btnXoa;
	private JTable table;
    private  DefaultTableModel tableModel;
    private List<NhanVien> listNhanVien;

    public Form_CapNhatNhanVien() {
        // Giao diện chung
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Cập Nhật Thông Tin Nhân Viên");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.BLUE);

        // Panel chính chứa hai khu vực song song
        JPanel pnlMain = new JPanel(new GridLayout(1, 2, 20, 0)); // 1 hàng, 2 cột, khoảng cách ngang 20px
        pnlMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlMain.setBackground(new Color(240, 248, 255));

        // Khu vực 1
        JPanel pnlArea1 = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 hàng, 2 cột, khoảng cách 10px
        pnlArea1.setBackground(new Color(240, 248, 255));
        pnlArea1.setBorder(BorderFactory.createTitledBorder(""));

        pnlArea1.add(new JLabel("Mã Nhân Viên:"));
        pnlArea1.add(txtMaNV = new JTextField(20));
        txtMaNV.setEditable(false);
        txtMaNV.setFocusable(false);
        pnlArea1.add(new JLabel("Tên Nhân Viên:"));
        pnlArea1.add(txtTenNV = new JTextField(20));
        pnlArea1.add(new JLabel("Tuổi:"));
        pnlArea1.add(txtTuoi = new JTextField(20));
        pnlArea1.add(new JLabel("Giới Tính:"));
        pnlArea1.add(txtGioiTinh = new JTextField(20));

        // Khu vực 2
        JPanel pnlArea2 = new JPanel(new GridLayout(5, 2, 10, 10)); // 5 hàng, 2 cột (thêm trạng thái)
        pnlArea2.setBackground(new Color(240, 248, 255));
        pnlArea2.setBorder(BorderFactory.createTitledBorder(""));

        pnlArea2.add(new JLabel("Điện Thoại:"));
        pnlArea2.add(txtDienThoai = new JTextField(20));
        pnlArea2.add(new JLabel("Email:"));
        pnlArea2.add(txtEmail = new JTextField(20));
        pnlArea2.add(new JLabel("Địa Chỉ:"));
        pnlArea2.add(txtDiaChi = new JTextField(20));
        
        pnlArea2.add(new JLabel("Chức Vụ:"));
        cboChucVu = new JComboBox<>(new String[] {"Nhân viên bán hàng", "Quản lý", "Bảo vệ"});
        cboChucVu.setPreferredSize(new Dimension(150, 30));
        pnlArea2.add(cboChucVu);

        pnlArea2.add(new JLabel("Trạng Thái:")); // Trạng thái
        cboTrangThai = new JComboBox<>(new String[] {"Đang làm", "Đã nghỉ"});
        cboTrangThai.setPreferredSize(new Dimension(150, 30));
        pnlArea2.add(cboTrangThai);

        // Thêm hai khu vực vào giao diện chính
        pnlMain.add(pnlArea1);
        pnlMain.add(pnlArea2);

        // Buttons Panel
        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButton.setBackground(new Color(240, 248, 255));
        pnlButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(new Dimension(150, 30));
        btnThem.setBackground(new Color(100, 149, 237));
        btnThem.setForeground(Color.WHITE);
        pnlButton.add(btnThem);

        btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setPreferredSize(new Dimension(150, 30));
        btnCapNhat.setBackground(new Color(100, 149, 237));
        btnCapNhat.setForeground(Color.WHITE);
        pnlButton.add(btnCapNhat);

        btnHuy = new JButton("Hủy");
        btnHuy.setPreferredSize(new Dimension(150, 30));
        btnHuy.setBackground(new Color(255, 69, 0));
        btnHuy.setForeground(Color.WHITE);
        pnlButton.add(btnHuy);
        
        btnXoa = new JButton("Xóa");
        btnXoa.setPreferredSize(new Dimension(150, 30));
        btnXoa.setBackground(new Color(255, 69, 0));
        btnXoa.setForeground(Color.WHITE);
        pnlButton.add(btnXoa);
        

        // Table for displaying data
        String[] columnNames = {"Mã Nhân Viên", "Tên Nhân Viên", "Tuổi", "Giới Tính", "Điện Thoại", "Email", "Địa Chỉ", "Trạng Thái", "Chức Vụ"};
         tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(800, 200)); // Tăng chiều rộng của bảng
        updateTableNV();

        // Add components to layout
        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.setBackground(new Color(255, 250, 240));
        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        pnlNorth.add(pnlMain, BorderLayout.CENTER);
        pnlNorth.add(pnlButton, BorderLayout.SOUTH);
        add(pnlNorth, BorderLayout.NORTH);

        add(tableScrollPane, BorderLayout.CENTER);
        

        // Add button actions
        btnCapNhat.addActionListener(this);
        btnHuy.addActionListener(this);
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
    }
    private void updateTableNV()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        tableModel.setRowCount(0);
        try {
            NhanVienService nhaVienService = (NhanVienService ) Naming.lookup("rmi://localhost:9090/nhanVienService");
            listNhanVien = nhaVienService.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ thuốc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // Thêm dữ liệu mới vào bảng thuốc
        for (NhanVien nv : listNhanVien) {
            Object[] rowData = {
                   nv.getMaNV(),
                    nv.getTenNV(),
                    "18",
                    "Nam",
                    nv.getSoDienThoai().toString(),
                    nv.getEmail(),
                    nv.getDiaChi(),
                    nv.getChuThich(),
                    nv.getChucVu()



            };
            tableModel.addRow(rowData);
        }
    }
    private void xoaTrang() {
        txtDiaChi.setText("");
        txtDienThoai.setText("");
        txtEmail.setText("");
        txtTuoi.setText("");
        txtMaNV.setText("");
        txtGioiTinh.setText("");
        txtTenNV.setText("");

    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();
        if (o.equals(btnCapNhat)) {

            
        }
        
        if (o.equals(btnHuy)) {
            // Xử lý làm sạch form

        }

        if(o.equals(btnThem)) {
            String tenNV = txtTenNV.getText();
            int tuoi = Integer.parseInt(txtTuoi.getText()); // Tuổi
            String gioiTinh = txtGioiTinh.getText();
            String soDienThoai = txtDienThoai.getText();
            String email = txtEmail.getText();
            String diaChi = txtDiaChi.getText();
            String chucVu = cboChucVu.getSelectedItem().toString();
            String trangThai = cboTrangThai.getSelectedItem().toString();

            // Chuyển tuổi về ngày sinh (giả định sinh nhật là 01/01)
            Date ngaySinh = null;
            try {
                int namHienTai = java.time.Year.now().getValue();
                int namSinh = namHienTai - tuoi;
                String ngaySinhStr = "01/01/" + namSinh;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                ngaySinh = sdf.parse(ngaySinhStr);
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi định dạng ngày sinh", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date ngayVaoLam = new Date(); // Ngày hiện tại

            // Tạo đối tượng Nhân viên
            NhanVien nv = new NhanVien();
            nv.setTenNV(tenNV);
            nv.setSoDienThoai(soDienThoai);
            nv.setDiaChi(diaChi);
            nv.setEmail(email);

            nv.setNgaySinh(ngaySinh);
            nv.setNgayVaoLam(ngayVaoLam);
            nv.setChucVu(chucVu);
            nv.setChuThich(trangThai);

            try {
                NhanVienService nhanVienService = (NhanVienService) Naming.lookup("rmi://localhost:9090/nhanVienService");

                if (nhanVienService.themNhanVien(nv)) {
                    JOptionPane.showMessageDialog(this, "Lưu Nhân Viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                    // Thêm dòng vào bảng
                    updateTableNV();

                } else {
                    JOptionPane.showMessageDialog(this, "Thêm Nhân Viên thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi kết nối RMI!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(o.equals(btnXoa)) {

        }
    }

}
