package iuh.fit.gui;

import model.NhaCungCap;
import model.Thuoc;
import services.NhaSanXuatService;
import services.ThuocService;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Form_PhieuNhapThuoc extends JPanel implements ActionListener {
    // Các thành phần khu vực tác vụ
    private JLabel lblMaPNT, lblNgayTaoPNT, lblNhaCungCap, lblTenThuoc, lblSoLuong, lblDonVi, lblDonGia, lblThanhTien, lblHienThiNgayTao;
    private JTextField txtMaPNT, txtTenThuoc, txtSoLuong, txtDonVi, txtDonGia, txtThanhTien;
    private JButton btnTaoMoiPNT, btnInPNT, btnLuuPNT;

    // Thành phần mới cho thông tin lô thuốc
    private JLabel lblNgaySanXuat, lblHanSuDung;
    private JSpinner spnNgaySanXuat, spnHanSuDung;
    private SpinnerDateModel modelNgaySX, modelHanSD;

    // Các thành phần khu vực thuốc
    private JLabel lblTimKiemThuoc;
    private JTextField txtTimKiemThuoc;
    private JButton btnTimKiemThuoc;
    private JTable tblThuoc, tblThuocDaChon;
    private JScrollPane scrollThuoc, scrollThuocDaChon;
    private JButton btnQuetMa, btnThemVaoPhieu;
    private Label lblTieuDe;
    private JPanel panelTieuDe;
    private JComboBox<String> cmbNhaCungCap;
    private JPanel panelThuoc2;
    private JPanel panelGioHang;
    private DefaultTableModel tbmThuoc;
    private DefaultTableModel tbmThuocDaChon;
    private ListSelectionListener listener1;
    private ListSelectionListener listener2;
    private JButton btnThemNCC;
    private JPanel panelNCC;
    private int flag;
    private List<Thuoc> dsThuoc;
    private List<NhaCungCap> dsNCC;

    public Form_PhieuNhapThuoc() {
        // Cấu hình cửa sổ
        setSize(1000, 750); // Kích thước ứng dụng là 1000x750
        setLayout(new BorderLayout(10, 10));

        // Tạo khu vực tác vụ (nhà cung cấp, nhân viên, phiếu nhập thuốc)
        JPanel panelTacVu = new JPanel(new GridBagLayout());
        panelTacVu.setBorder(BorderFactory.createTitledBorder("Thông tin phiếu nhập thuốc"));

        GridBagConstraints gbc = new GridBagConstraints();
        // Điều chỉnh khoảng cách giữa các thành phần
        gbc.insets = new Insets(5, 5, 5, 5);  // Thêm khoảng trống giữa các thành phần trong panelTacVu
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        lblMaPNT = new JLabel("Mã phiếu nhập:");
        txtMaPNT = new JTextField(15);
        customizeTextField(txtMaPNT, false);
        panelTacVu.add(lblMaPNT, gbc);
        gbc.gridx = 1;
        panelTacVu.add(txtMaPNT, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        lblNgayTaoPNT = new JLabel("Ngày tạo phiếu:");
        panelTacVu.add(lblNgayTaoPNT, gbc);

        // Hiển thị ngày hiện tại
        lblHienThiNgayTao = new JLabel(getCurrentDate());
        gbc.gridx = 1;
        panelTacVu.add(lblHienThiNgayTao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;

        gbc.gridx = 1;

        gbc.gridx = 0;
        gbc.gridy = 3;
        lblNhaCungCap = new JLabel("Nhà cung cấp:");
        panelTacVu.add(lblNhaCungCap, gbc);

        // Thêm trường nhà cung cấp
        panelNCC = new JPanel();
        gbc.gridx = 1;
        cmbNhaCungCap = new JComboBox<String>();
        cmbNhaCungCap.setPreferredSize(new Dimension(100, 30));
        panelNCC.add(cmbNhaCungCap);
        btnThemNCC = new JButton("Thêm");
        btnThemNCC.setBackground(new Color(34, 139, 34)); // Màu xanh lá cây đậm
        btnThemNCC.setForeground(Color.WHITE);
        panelNCC.add(btnThemNCC);
        panelTacVu.add(panelNCC, gbc);
        btnThemNCC.addActionListener(this);
        cmbNhaCungCap.addActionListener(this);

        // Khu vực thay đổi số lượng thuốc và thông tin lô
        JPanel panelThuoc = new JPanel(new GridBagLayout());
        panelThuoc.setBorder(BorderFactory.createTitledBorder("Thông tin thuốc và lô")); // Đổi tên để phản ánh cả thông tin lô

        GridBagConstraints gbcThuoc = new GridBagConstraints();
        gbcThuoc.insets = new Insets(5, 5, 5, 5);
        gbcThuoc.fill = GridBagConstraints.HORIZONTAL;
        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 0;

        lblTenThuoc = new JLabel("Tên thuốc:");
        txtTenThuoc = new JTextField(15);
        customizeTextField(txtTenThuoc, false);
        txtTenThuoc.setEditable(false);
        panelThuoc.add(lblTenThuoc, gbcThuoc);
        gbcThuoc.gridx = 1;
        panelThuoc.add(txtTenThuoc, gbcThuoc);

        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 1;
        lblDonVi = new JLabel("Đơn vị:");
        txtDonVi = new JTextField(15);
        customizeTextField(txtDonVi, false);
        txtDonVi.setEditable(false);
        panelThuoc.add(lblDonVi, gbcThuoc);
        gbcThuoc.gridx = 1;
        panelThuoc.add(txtDonVi, gbcThuoc);

        // Thêm thông tin lô thuốc - Mã lô
        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 2;


        // Thêm thông tin lô thuốc - Ngày sản xuất
        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 3;
        lblNgaySanXuat = new JLabel("Ngày sản xuất:");
        modelNgaySX = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spnNgaySanXuat = new JSpinner(modelNgaySX);
        JSpinner.DateEditor editorNgaySX = new JSpinner.DateEditor(spnNgaySanXuat, "dd/MM/yyyy");
        spnNgaySanXuat.setEditor(editorNgaySX);
        panelThuoc.add(lblNgaySanXuat, gbcThuoc);
        gbcThuoc.gridx = 1;
        panelThuoc.add(spnNgaySanXuat, gbcThuoc);

        // Thêm thông tin lô thuốc - Hạn sử dụng
        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 4;
        lblHanSuDung = new JLabel("Hạn sử dụng:");
        // Đặt ngày mặc định là 2 năm sau ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2);
        modelHanSD = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.DAY_OF_MONTH);
        spnHanSuDung = new JSpinner(modelHanSD);
        JSpinner.DateEditor editorHanSD = new JSpinner.DateEditor(spnHanSuDung, "dd/MM/yyyy");
        spnHanSuDung.setEditor(editorHanSD);
        panelThuoc.add(lblHanSuDung, gbcThuoc);
        gbcThuoc.gridx = 1;
        panelThuoc.add(spnHanSuDung, gbcThuoc);

        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 5;
        lblSoLuong = new JLabel("Số lượng:");
        txtSoLuong = new JTextField(15);
        customizeTextField(txtSoLuong, true);
        panelThuoc.add(lblSoLuong, gbcThuoc);
        gbcThuoc.gridx = 1;
        panelThuoc.add(txtSoLuong, gbcThuoc);

        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 6;
        lblDonGia = new JLabel("Đơn giá nhập:");
        txtDonGia = new JTextField(15);
        customizeTextField(txtDonGia, true);
        panelThuoc.add(lblDonGia, gbcThuoc);
        gbcThuoc.gridx = 1;
        panelThuoc.add(txtDonGia, gbcThuoc);

        // Thêm nút "Thêm vào phiếu" cho thuốc đã nhập đầy đủ thông tin
        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 7;
        gbcThuoc.gridwidth = 2;
        gbcThuoc.fill = GridBagConstraints.HORIZONTAL;
        btnThemVaoPhieu = new JButton("Thêm vào phiếu");
        btnThemVaoPhieu.setBackground(new Color(70, 130, 180)); // Màu xanh lam
        btnThemVaoPhieu.setForeground(Color.WHITE);
        panelThuoc.add(btnThemVaoPhieu, gbcThuoc);
        btnThemVaoPhieu.addActionListener(this);
        gbcThuoc.gridwidth = 1;

        // Thêm panelThuoc vào panelTacVu
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Để khung bao thuốc chiếm cả chiều rộng
        panelTacVu.add(panelThuoc, gbc);

        // Thêm thành tiền và các nút
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        lblThanhTien = new JLabel("Thành tiền:");
        txtThanhTien = new JTextField(15);
        customizeTextField(txtThanhTien, false);
        txtThanhTien.setEditable(false); // Không cho phép chỉnh sửa
        panelTacVu.add(lblThanhTien, gbc);
        gbc.gridx = 1;
        panelTacVu.add(txtThanhTien, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        btnTaoMoiPNT = new JButton("Tạo mới");
        btnTaoMoiPNT.setBackground(new Color(34, 139, 34)); // Màu xanh lá cây đậm
        btnTaoMoiPNT.setForeground(Color.WHITE);
        panelTacVu.add(btnTaoMoiPNT, gbc);
        gbc.gridx = 1;
        btnLuuPNT = new JButton("Lưu phiếu");
        btnLuuPNT.setBackground(new Color(70, 130, 180)); // Màu xanh lam
        btnLuuPNT.setForeground(Color.WHITE);
        panelTacVu.add(btnLuuPNT, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        btnInPNT = new JButton("In phiếu");
        gbc.gridwidth = 2;
        btnInPNT.setBackground(new Color(255, 165, 0)); // Màu cam
        btnInPNT.setForeground(Color.WHITE);
        panelTacVu.add(btnInPNT, gbc);
        flag = 1;

        // Tạo khu vực thuốc
        panelThuoc2 = new JPanel();
        panelThuoc2.setLayout(new BoxLayout(panelThuoc2, BoxLayout.Y_AXIS));
        panelThuoc2.setBorder(BorderFactory.createTitledBorder("Thông tin thuốc"));

        // Tìm kiếm thuốc
        JPanel panelTimKiemThuoc = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblTimKiemThuoc = new JLabel("Tìm kiếm thuốc:");
        txtTimKiemThuoc = new JTextField(15);
        customizeTextField(txtTimKiemThuoc, true);
        btnTimKiemThuoc = new JButton("Tìm kiếm");
        btnTimKiemThuoc.setBackground(new Color(255, 165, 0)); // Màu cam
        btnTimKiemThuoc.setForeground(Color.WHITE);
        btnQuetMa = new JButton("Quét mã");
        btnQuetMa.setBackground(new Color(34, 139, 34)); // Màu xanh lá cây đậm
        btnQuetMa.setForeground(Color.WHITE);
        panelTimKiemThuoc.add(lblTimKiemThuoc);
        panelTimKiemThuoc.add(txtTimKiemThuoc);
        panelTimKiemThuoc.add(btnTimKiemThuoc);
        panelTimKiemThuoc.add(btnQuetMa);

        // Bảng thuốc và bảng thuốc đã chọn
        String[] columnNamesThuoc = {"Mã thuốc", "Tên thuốc", "Giá bán", "Giá nhập", "Số lượng tồn", "Đơn vị tính"};
        tbmThuoc = new DefaultTableModel(columnNamesThuoc, 0);
        tblThuoc = new JTable(tbmThuoc);

        // Khai báo listener
        listener1 = e -> {
            if (!e.getValueIsAdjusting()) {
                handleTblThuocSelection();
            }
        };
        listener2 = e -> {
            if (!e.getValueIsAdjusting()) {
                handleTblThuocDaChonSelection();
            }
        };
        tblThuoc.getSelectionModel().addListSelectionListener(listener1);

        // Tùy chỉnh bảng thuốc với màu nền xen kẽ giữa các hàng
        tblThuoc.setShowGrid(true);
        tblThuoc.setGridColor(Color.LIGHT_GRAY);
        tblThuoc.setRowHeight(30);  // Tăng chiều cao của hàng
        tblThuoc.setSelectionBackground(new Color(135, 206, 235));  // Màu nền khi được chọn
        tblThuoc.setFont(new Font("Open Sans", Font.PLAIN, 14));  // Phông chữ cho bảng

        // Đặt màu nền cho tiêu đề bảng
        tblThuoc.getTableHeader().setBackground(new Color(70, 130, 180));
        tblThuoc.getTableHeader().setForeground(Color.WHITE);
        tblThuoc.getTableHeader().setFont(new Font("Open Sans", Font.PLAIN, 14));

        scrollThuoc = new JScrollPane(tblThuoc);

        // Cập nhật các cột cho bảng thuốc đã chọn để bao gồm thông tin lô
        String[] columnNamesThuocDaChon = {"Mã thuốc", "Tên thuốc", "NSX", "HSD", "Số lượng", "Đơn giá nhập", "Đơn vị tính", "Thành tiền"};
        tbmThuocDaChon = new DefaultTableModel(columnNamesThuocDaChon, 0);

        tblThuocDaChon = new JTable(tbmThuocDaChon);
        tblThuocDaChon.getSelectionModel().addListSelectionListener(listener2);
        // Tùy chỉnh bảng thuốc với màu nền xen kẽ giữa các hàng
        tblThuocDaChon.setShowGrid(true);
        tblThuocDaChon.setGridColor(Color.LIGHT_GRAY);
        tblThuocDaChon.setFont(new Font("Open Sans", Font.PLAIN, 14));
        tblThuocDaChon.setRowHeight(30);  // Điều chỉnh chiều cao hàng cho bảng thuốc đã chọn
        tblThuocDaChon.setSelectionBackground(new Color(135, 206, 235));  // Màu nền khi được chọn

        // Đặt màu nền cho tiêu đề bảng
        tblThuocDaChon.getTableHeader().setBackground(new Color(70, 130, 180));
        tblThuocDaChon.getTableHeader().setForeground(Color.WHITE);
        tblThuocDaChon.getTableHeader().setFont(new Font("Open Sans", Font.PLAIN, 14));

        scrollThuocDaChon = new JScrollPane(tblThuocDaChon);

        panelGioHang = new JPanel();
        panelGioHang.setLayout(new BorderLayout());
        panelGioHang.setBorder(BorderFactory.createTitledBorder("Danh sách thuốc nhập"));
        panelGioHang.setSize(new Dimension(scrollThuoc.getPreferredSize()));
        panelGioHang.add(scrollThuocDaChon, BorderLayout.CENTER);

        // Thêm các thành phần vào khu vực thuốc
        panelThuoc2.add(panelTimKiemThuoc);
        panelThuoc2.add(scrollThuoc);
        panelThuoc2.add(panelGioHang);

        // Thêm tiêu đề cho cửa sổ
        panelTieuDe = new JPanel();
        lblTieuDe = new Label("LẬP PHIẾU NHẬP THUỐC");

        // Cải thiện tiêu đề của ứng dụng
        lblTieuDe.setFont(new Font("Open Sans", Font.BOLD, 25));
        lblTieuDe.setForeground(new Color(0, 102, 204));  // Màu xanh dương đậm
        panelTieuDe.setBackground(new Color(245, 245, 245));  // Nền sáng cho tiêu đề

        lblTieuDe.setForeground(Color.BLUE);  // Đặt màu chữ xanh
        panelTieuDe.add(lblTieuDe);

        // Thêm hai khu vực vào cửa sổ chính
        add(panelTacVu, BorderLayout.WEST);
        add(panelThuoc2, BorderLayout.CENTER);
        add(panelTieuDe, BorderLayout.NORTH);

        // Xử lý sự kiện các nút
        btnTaoMoiPNT.addActionListener(this);
        btnQuetMa.addActionListener(this);
        btnInPNT.addActionListener(this);
        btnLuuPNT.addActionListener(this);
        btnTimKiemThuoc.addActionListener(this);
        btnThemVaoPhieu.addActionListener(this);
        txtSoLuong.addActionListener(this);
        txtDonGia.addActionListener(this);

        updateTableThuoc();
        updateNhaCungCap();
    }

    private void updateTableThuoc()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        tbmThuoc.setRowCount(0);
        try {
            ThuocService thuocService = (ThuocService) Naming.lookup("rmi://localhost:9090/thuocService");
            dsThuoc = thuocService.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ thuốc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // Thêm dữ liệu mới vào bảng thuốc
        for (Thuoc thuoc : dsThuoc) {
            Object[] rowData = {
                    thuoc.getMaThuoc(),
                    thuoc.getTenThuoc(),
                    thuoc.getGiaBan(),
                    thuoc.getGiaNhap(),
                    thuoc.getSoLuong(),
                    thuoc.getDonViTinh()
            };
            tbmThuoc.addRow(rowData);
        }
    }

    private void updateNhaCungCap()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        try {
            NhaSanXuatService thuocService = (NhaSanXuatService) Naming.lookup("rmi://localhost:9090/nhaSanXuatService");
            dsNCC = thuocService.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ nhà cung cấp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // Thêm dữ liệu mới vào bảng thuốc
        for (NhaCungCap thuoc : dsNCC) {
            cmbNhaCungCap.addItem(thuoc.getTenNCC());
        }
    }

    // Phương thức lấy ngày hiện tại theo định dạng dd/MM/yyyy
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());
    }

    private void customizeTextField(JTextField textField, boolean editable) {
        textField.setEditable(editable);
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(170, 170, 170), 1, true),  // Viền bo tròn
                BorderFactory.createEmptyBorder(5, 5, 5, 5)  // Khoảng cách trong
        ));

        if (editable) {
            textField.setBackground(new Color(245, 245, 245));  // Nền sáng nhẹ khi chỉnh sửa được
            textField.setForeground(Color.DARK_GRAY);  // Màu chữ
        } else {
            textField.setBackground(new Color(230, 230, 230));  // Nền khác cho trạng thái không chỉnh sửa
            textField.setForeground(Color.GRAY);  // Màu chữ nhạt hơn khi không chỉnh sửa
        }

        textField.setFont(new Font("Open Sans", Font.PLAIN, 14));  // Phông chữ hiện đại
    }

    private void updateNCC() {
        // TODO: Implement updating supplier combobox
        try {
            while (cmbNhaCungCap.getItemCount() != 0) {
                cmbNhaCungCap.removeAllItems();
            }
            // Load nhà cung cấp từ database vào combobox
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void handleTblThuocDaChonSelection() {
        // Xử lý khi chọn thuốc trong bảng thuốc đã chọn
        int row = tblThuocDaChon.getSelectedRow();
        if (row >= 0) {
            String maThuoc = tblThuocDaChon.getValueAt(row, 0).toString();
            String tenThuoc = tblThuocDaChon.getValueAt(row, 1).toString();
            String maLo = tblThuocDaChon.getValueAt(row, 2).toString();
            String ngaySX = tblThuocDaChon.getValueAt(row, 3).toString();
            String hanSD = tblThuocDaChon.getValueAt(row, 4).toString();
            String soLuong = tblThuocDaChon.getValueAt(row, 5).toString();
            String donGia = tblThuocDaChon.getValueAt(row, 6).toString();
            String donVi = tblThuocDaChon.getValueAt(row, 7).toString();

            txtTenThuoc.setText(tenThuoc);
            txtSoLuong.setText(soLuong);
            txtDonVi.setText(donVi);
            txtDonGia.setText(donGia);

            // Cập nhật date spinners (cần chuyển đổi string thành date)
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date ngaySanXuat = sdf.parse(ngaySX);
                Date hanSuDung = sdf.parse(hanSD);

                modelNgaySX.setValue(ngaySanXuat);
                modelHanSD.setValue(hanSuDung);
            } catch (Exception e) {
                e.printStackTrace();
            }

            updateThanhTien();
        }
    }

    private void handleTblThuocSelection() {
        // Xử lý khi chọn thuốc trong bảng thuốc
        int row = tblThuoc.getSelectedRow();
        if (row >= 0) {
            String maThuoc = tblThuoc.getValueAt(row, 0).toString();
            String tenThuoc = tblThuoc.getValueAt(row, 1).toString();
            String donGia = tblThuoc.getValueAt(row, 3).toString();
            String donVi = tblThuoc.getValueAt(row, 5).toString();

            txtTenThuoc.setText(tenThuoc);
            txtDonVi.setText(donVi);
            txtDonGia.setText(donGia);
            txtSoLuong.setText("1");

            // Tạo mã lô mặc định theo ngày và mã thuốc
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String ngayHienTai = sdf.format(new Date());

            updateThanhTien();
        }
    }

    private void themThuocVaoPhieu() {
        // Kiểm tra và thêm thuốc vào phiếu nhập
        if (txtTenThuoc.getText().isEmpty() ||
                txtSoLuong.getText().isEmpty() || txtDonGia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin thuốc và lô");
            return;
        }

        // Lấy thông tin từ form
        int selectedRow = tblThuoc.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }

        String maThuoc = tblThuoc.getValueAt(selectedRow, 0).toString();
        String tenThuoc = txtTenThuoc.getText();

        // Định dạng ngày để hiển thị
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngaySX = sdf.format(modelNgaySX.getDate());
        String hanSD = sdf.format(modelHanSD.getDate());

        int soLuong = Integer.parseInt(txtSoLuong.getText());
        double donGia = Double.parseDouble(txtDonGia.getText());
        String donVi = txtDonVi.getText();
        double thanhTien = soLuong * donGia;

        // Thêm vào bảng thuốc đã chọn
        tbmThuocDaChon.addRow(new Object[]{
                maThuoc, tenThuoc, ngaySX, hanSD, soLuong, donGia, donVi, thanhTien
        });

        // Cập nhật tổng tiền phiếu nhập
        updateTongTien();

        // Xóa thông tin trong form để nhập thuốc mới
        txtTenThuoc.setText("");
        txtSoLuong.setText("");
        txtDonVi.setText("");
        txtDonGia.setText("");
        txtThanhTien.setText("");
        tblThuoc.clearSelection();
    }

    private void updateTongTien() {
        double tongTien = 0;
        for (int i = 0; i < tbmThuocDaChon.getRowCount(); i++) {
            tongTien += Double.parseDouble(tbmThuocDaChon.getValueAt(i, 8).toString());
        }
        txtThanhTien.setText(String.valueOf(tongTien));
    }

    private void updateThanhTien() {
        // Cập nhật thành tiền dựa trên số lượng và đơn giá
        try {
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            double donGia = Double.parseDouble(txtDonGia.getText());
            double thanhTien = soLuong * donGia;
            txtThanhTien.setText(String.valueOf(thanhTien));
        } catch (NumberFormatException e) {
            txtThanhTien.setText("0");
        }
    }

    private void clearForm() {
        // Xóa tất cả các trường trong form
        txtMaPNT.setText("");
        txtTenThuoc.setText("");
        txtSoLuong.setText("");
        txtDonVi.setText("");
        txtDonGia.setText("");
        txtThanhTien.setText("");
        lblHienThiNgayTao.setText(getCurrentDate());

        // Xóa bảng thuốc đã chọn
        tbmThuocDaChon.setRowCount(0);
        // Xóa bảng thuốc
        tbmThuoc.setRowCount(0);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThemVaoPhieu)) {
            int selectedRow = tblThuoc.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc từ danh sách");
                return;
            }
            // Thêm thuốc vào phiếu
            int maThuoc = Integer.parseInt(tblThuoc.getValueAt(selectedRow, 0).toString());
            String tenThuoc = tblThuoc.getValueAt(selectedRow, 1).toString();
            String donVi = tblThuoc.getValueAt(selectedRow, 5).toString();
            String nsx = modelNgaySX.toString();
            String hsd = modelHanSD.toString();
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            double donGia = Double.parseDouble(txtDonGia.getText());
            double thanhTien = soLuong * donGia;
            tbmThuocDaChon.addRow(new Object[]{maThuoc, tenThuoc, nsx, hsd, soLuong, donGia, donVi, thanhTien});
            // Xóa thông tin trong form để nhập thuốc mới
            txtTenThuoc.setText("");
            txtSoLuong.setText("");
            txtDonVi.setText("");
            txtDonGia.setText("");
            tblThuoc.clearSelection();
        }
    }
}

