package iuh.fit.gui;

import dao.ThuocDAO;
import model.*;
import services.HoaDonService;
import services.KhachHangService;
import services.TaiKhoanService;
import services.ThuocService;
import services.impl.ThuocServiceImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Form_LapHoaDon extends JPanel implements ActionListener{
    // Các thành phần khu vực tác vụ
    private JLabel lblMaHD, lblNgayTaoHD, lblNhanVien, lblKhachHang, lblSDT, lblTenThuoc, lblSoLuong, lblDonVi, lblDonGia, lblThanhTien, lblHienThiNgayTao;
    private JTextField txtMaHD, txtNhanVien, txtKhachHang, txtTenThuoc, txtSoLuong, txtDonVi, txtDonGia, txtThanhTien;
    private JButton btnTaoMoiHD, btnInHD, btnLuuHD;

    // Các thành phần khu vực thuốc
    private JLabel lblTimKiemThuoc;
    private JTextField txtTimKiemThuoc;
    private JButton btnTimKiemThuoc;
    private JTable tblThuoc, tblThuocDaChon;
    private JScrollPane scrollThuoc, scrollThuocDaChon;
	private JButton btnQuetMa;
	private Label lblTieuDe;
	private JPanel panelTieuDe;
	private AutoSuggestTextField cmbSDT;
	private JLabel lblPDT;
	private JComboBox<String> cmbPDT;
	private JPanel panelThuoc2;
	private JPanel panelGioHang;
	private DefaultTableModel tbmThuoc;
	private DefaultTableModel tbmThuocDaChon;
	private ListSelectionListener listener1;
	private ListSelectionListener listener2;
	private JButton btnThemKH;
	private JPanel panelKH;
	private int flag;

    private List<Thuoc> dsThuoc;
    private ThuocDAO thuocDAO;
    private Integer maThuoc;

    public Form_LapHoaDon() {
        // Cấu hình cửa sổ
 
        setSize(1000, 750); // Kích thước ứng dụng là 1000x750

 
        setLayout(new BorderLayout(10, 10));


     // Tạo khu vực tác vụ (khách hàng, nhân viên, hóa đơn)
        JPanel panelTacVu = new JPanel(new GridBagLayout());
        panelTacVu.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        GridBagConstraints gbc = new GridBagConstraints();
        // Điều chỉnh khoảng cách giữa các thành phần
        gbc.insets = new Insets(5, 5, 5, 5);  // Thêm khoảng trống giữa các thành phần trong panelTacVu
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        lblMaHD = new JLabel("Mã hóa đơn:");
        txtMaHD = new JTextField(15);
        customizeTextField(txtMaHD, false);
        panelTacVu.add(lblMaHD, gbc);
        gbc.gridx = 1;
        panelTacVu.add(txtMaHD, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        lblNgayTaoHD = new JLabel("Ngày tạo hóa đơn:");
        panelTacVu.add(lblNgayTaoHD, gbc);

        // Hiển thị ngày hiện tại
        lblHienThiNgayTao = new JLabel(getCurrentDate());
        gbc.gridx = 1;
        panelTacVu.add(lblHienThiNgayTao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        lblNhanVien = new JLabel("Nhân viên:");
        txtNhanVien = new JTextField(15);
        customizeTextField(txtNhanVien, false);
        panelTacVu.add(lblNhanVien, gbc);
        gbc.gridx = 1;
        panelTacVu.add(txtNhanVien, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        lblKhachHang = new JLabel("Khách hàng:");
        txtKhachHang = new JTextField(15);
        customizeTextField(txtKhachHang, false);
        panelTacVu.add(lblKhachHang, gbc);
        gbc.gridx = 1;
        panelTacVu.add(txtKhachHang, gbc);

        // Thêm trường Số điện thoại
        panelKH = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 4;
        lblSDT = new JLabel("Số điện thoại:");
        // Danh sách số điện thoại (có thể lấy từ CSDL)
        ArrayList<String> phoneList = new ArrayList<String>();
        try {
            KhachHangService khachHangService = (KhachHangService) Naming.lookup("rmi://localhost:9090/khachHangService");
            List<KhachHang> dsKH = khachHangService.getAll();
            dsKH.forEach(khachHang -> {
                phoneList.add(khachHang.getSoDienThoai());
            });
            cmbSDT = new AutoSuggestTextField(phoneList);
            cmbSDT.addSuggestionSelectedListener(phoneNumber -> {
                // Look up the customer with this phone number from your data source
                // This is just an example - replace with your actual lookup code
                try {
                    KhachHang customer = khachHangService.timBangSDT(phoneNumber);
                    if (customer != null) {
                        txtKhachHang.setText(customer.getTenKH());
                    } else {
                        txtKhachHang.setText("Customer not found");
                    }
                } catch (Exception ex) {
                    txtKhachHang.setText("Error: " + ex.getMessage());
                }
            });
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ thuốc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        cmbSDT.setPreferredSize(new Dimension(100, 30));    
        panelTacVu.add(lblSDT, gbc);
        gbc.gridx = 1;
        panelKH.add(cmbSDT);
        btnThemKH = new JButton("Thêm");
        btnThemKH.setBackground(new Color(34, 139, 34)); // Màu xanh lá cây đậm
        btnThemKH.setForeground(Color.WHITE);
        panelKH.add(btnThemKH);
        panelTacVu.add(panelKH, gbc);
        btnThemKH.addActionListener(this);
        cmbSDT.addActionListener(this);
        
        // Khu vực thay đổi số lượng thuốc
        JPanel panelThuoc = new JPanel(new GridBagLayout());
        panelThuoc.setBorder(BorderFactory.createTitledBorder("Thông tin thuốc")); // Đặt viền bao quanh khu vực thuốc

        GridBagConstraints gbcThuoc = new GridBagConstraints();
        gbcThuoc.insets = new Insets(5,5,5,5);
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
        lblSoLuong = new JLabel("Số lượng:");
        txtSoLuong = new JTextField(15);
        customizeTextField(txtSoLuong, true);
        panelThuoc.add(lblSoLuong, gbcThuoc);
        gbcThuoc.gridx = 1;
        panelThuoc.add(txtSoLuong, gbcThuoc);

        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 2;
        lblDonVi = new JLabel("Đơn vị:");
        txtDonVi = new JTextField(15);
        customizeTextField(txtDonVi, false);
        txtDonVi.setEditable(false);
        panelThuoc.add(lblDonVi, gbcThuoc);
        gbcThuoc.gridx = 1;
        panelThuoc.add(txtDonVi, gbcThuoc);

        gbcThuoc.gridx = 0;
        gbcThuoc.gridy = 3;
        lblDonGia = new JLabel("Đơn giá:");
        txtDonGia = new JTextField(15);
        customizeTextField(txtDonGia, false);
        txtDonGia.setEditable(false);
        panelThuoc.add(lblDonGia, gbcThuoc);
        gbcThuoc.gridx = 1;
        panelThuoc.add(txtDonGia, gbcThuoc);

        // Thêm panelThuoc vào panelTacVu
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Để khung bao thuốc chiếm cả chiều rộng
        panelTacVu.add(panelThuoc, gbc);

        // Thêm thành tiền và các nút
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 6;
        lblThanhTien = new JLabel("Thành tiền:");
        txtThanhTien = new JTextField(15);
        customizeTextField(txtThanhTien, false);
        txtThanhTien.setEditable(false); // Không cho phép chỉnh sửa
        panelTacVu.add(lblThanhTien, gbc);
        gbc.gridx = 1;
        panelTacVu.add(txtThanhTien, gbc);
        
        //Thêm phiếu đặt thuốc
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 7;
        lblPDT = new JLabel("Phiếu đặt thuốc:");
        cmbPDT = new JComboBox<String>();
        cmbPDT.setPreferredSize(new Dimension(txtMaHD.getWidth(), 30));
        
        panelTacVu.add(lblPDT, gbc);
        cmbPDT.addActionListener(this);
        gbc.gridx = 1;
        panelTacVu.add(cmbPDT, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        btnTaoMoiHD = new JButton("Tạo mới");
        btnTaoMoiHD.setBackground(new Color(34, 139, 34)); // Màu xanh lá cây đậm
        btnTaoMoiHD.setForeground(Color.WHITE);
        panelTacVu.add(btnTaoMoiHD, gbc);
        gbc.gridx = 1;
        btnLuuHD = new JButton("Lưu HĐ");
        btnLuuHD.setBackground(new Color(70, 130, 180)); // Màu xanh lam
        btnLuuHD.setForeground(Color.WHITE);
        panelTacVu.add(btnLuuHD, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        btnInHD = new JButton("In HĐ");
        gbc.gridwidth = 2;
        btnInHD.setBackground(new Color(255, 165, 0)); // Màu cam
        btnInHD.setForeground(Color.WHITE);
        panelTacVu.add(btnInHD, gbc);
        flag = 1;

        // Tạo khu vực thuốc
        panelThuoc2 = new JPanel();
        panelThuoc2.setLayout(new BoxLayout(panelThuoc2, 1));
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
        updateTableThuoc();
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

        String[] columnNamesThuocDaChon = {"Mã thuốc", "Tên thuốc", "Đơn giá", "Đơn vị tính", "Số lượng"};
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


        // Tạo JScrollPane cho bảng thuốc đã chọn
        scrollThuocDaChon = new JScrollPane(tblThuocDaChon);
        
        panelGioHang = new JPanel();
        panelGioHang.setLayout(new BorderLayout());
        panelGioHang.setBorder(BorderFactory.createTitledBorder("Danh sách thuốc mua"));
        panelGioHang.setSize(new Dimension(scrollThuoc.getPreferredSize()));
        panelGioHang.add(scrollThuocDaChon, BorderLayout.CENTER);
        
        // Thêm các thành phần vào khu vực thuốc
        panelThuoc2.add(panelTimKiemThuoc);
        panelThuoc2.add(scrollThuoc);
        panelThuoc2.add(panelGioHang);

        //Thêm tiêu đề cho cửa sổ
        panelTieuDe = new JPanel();
        lblTieuDe = new Label("LẬP HÓA ĐƠN");

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
        btnTaoMoiHD.addActionListener(this);
        btnQuetMa.addActionListener(this);
        btnInHD.addActionListener(this);
        btnLuuHD.addActionListener(this);
        btnTimKiemThuoc.addActionListener(this);
        txtSoLuong.addActionListener(this);

        txtNhanVien.setText(Form_DangNhap.nhanVien.getTenNV());
        try{
            HoaDonService hoaDonService = (HoaDonService) Naming.lookup("rmi://localhost:9090/hoaDonService");
            txtMaHD.setText(String.valueOf(hoaDonService.layMaHoaDonMoiNhat() + 1));
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ thuốc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
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

    // Cập nhật khách hàng
    // Khi bạn muốn thêm số điện thoại mới vào danh sách gợi ý
    public void updatePhoneList() {
        try {
            // Lấy danh sách hiện tại
            List<String> currentPhoneList = cmbSDT.getItems();

            // Lấy danh sách mới từ service
            KhachHangService khachHangService = (KhachHangService) Naming.lookup("rmi://localhost:9090/khachHangService");
            List<KhachHang> dsKH = khachHangService.getAll();

            // Thêm các số điện thoại mới vào danh sách hiện tại
            dsKH.forEach(khachHang -> {
                String phone = khachHang.getSoDienThoai();
                if (!currentPhoneList.contains(phone)) {
                    currentPhoneList.add(phone);
                }
            });

            // Hoặc nếu bạn muốn thêm số điện thoại cụ thể:
            // currentPhoneList.add("0123456789");

            // Cập nhật danh sách vào control
            cmbSDT.setItems(currentPhoneList);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi cập nhật danh sách số điện thoại: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
    private void updatePDT() {

	}

    private void handleCapNhatThuocDaChon() {
        try {
            int soLuongMoi = Integer.parseInt(txtSoLuong.getText());

            // Tìm số lượng tồn kho của thuốc này từ bảng tblThuoc
            int soLuongTonKho = -1;
            for (int i = 0; i < tbmThuoc.getRowCount(); i++) {
                Integer existingMaThuoc = (Integer) tbmThuoc.getValueAt(i, 0);
                if (existingMaThuoc.equals(maThuoc)) {
                    soLuongTonKho = (Integer) tbmThuoc.getValueAt(i, 4);
                    updateThanhTien();
                    break;
                }
            }

            if (soLuongTonKho == -1) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy thuốc trong kho!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (int i = 0; i < tbmThuocDaChon.getRowCount(); i++) {
                Integer existingMaThuoc = (Integer) tbmThuocDaChon.getValueAt(i, 0);
                if (existingMaThuoc.equals(maThuoc)) {

                    if (soLuongMoi == 0) {
                        // Nếu số lượng = 0 thì xóa khỏi bảng
                        tbmThuocDaChon.removeRow(i);
                        updateThanhTien();
                        JOptionPane.showMessageDialog(null, "Đã xóa thuốc khỏi danh sách chọn!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else if (soLuongMoi > soLuongTonKho) {
                        JOptionPane.showMessageDialog(null, "Số lượng vượt quá tồn kho: "+ soLuongTonKho, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Cập nhật số lượng
                        tbmThuocDaChon.setValueAt(soLuongMoi, i, 4);
                        updateThanhTien();
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }

                    break;
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void handleTblThuocDaChonSelection() {
        int selectedRow = tblThuocDaChon.getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy dữ liệu từ hàng được chọn trên bảng thuốc đã chọn
            maThuoc = (Integer) tbmThuocDaChon.getValueAt(selectedRow, 0);
            String tenThuoc = (String) tbmThuocDaChon.getValueAt(selectedRow, 1);
            Double giaBan = (Double) tbmThuocDaChon.getValueAt(selectedRow, 2);
            String donViTinh = (String) tbmThuocDaChon.getValueAt(selectedRow, 3);
            int soLuong = (int) tbmThuocDaChon.getValueAt(selectedRow, 4);

            txtDonGia.setText(String.valueOf(giaBan));
            txtTenThuoc.setText(tenThuoc);
            txtSoLuong.setText(String.valueOf(soLuong));
            txtDonVi.setText(donViTinh);

            updateThanhTien();
            // Bỏ chọn để có thể chọn lại sau
            tblThuocDaChon.clearSelection();
        }

    }
    private void handleTblThuocSelection() {
        int selectedRow = tblThuoc.getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy dữ liệu từ hàng được chọn trên bảng thuốc
            int maThuoc = (Integer) tbmThuoc.getValueAt(selectedRow, 0);
            String tenThuoc = (String) tbmThuoc.getValueAt(selectedRow, 1);
            Double giaBan = (Double) tbmThuoc.getValueAt(selectedRow, 2);
            String donViTinh = (String) tbmThuoc.getValueAt(selectedRow, 5);

            boolean daTonTai = false;
            for (int i = 0; i < tbmThuocDaChon.getRowCount(); i++) {
                Integer existingMaThuoc = (Integer) tbmThuocDaChon.getValueAt(i, 0);
                if (existingMaThuoc.equals(maThuoc)) {
                    // Nếu thuốc đã tồn tại, tăng số lượng lên 1
                    int currentSoLuong = (int) tbmThuocDaChon.getValueAt(i, 4);
                    tbmThuocDaChon.setValueAt(currentSoLuong + 1, i, 4);
                    daTonTai = true;
                    break;
                }
            }

            // Nếu chưa tồn tại thì thêm vào bảng thuốc đã chọn
            if (!daTonTai) {
                Object[] rowData = {maThuoc, tenThuoc, giaBan, donViTinh, 1}; // mặc định số lượng là 1
                tbmThuocDaChon.addRow(rowData);
            }
            updateThanhTien();
            // Bỏ chọn để có thể chọn lại sau
            tblThuoc.clearSelection();
            tblThuocDaChon.setRowSelectionInterval(0, 0);
        }
    }

    public void updateThanhTien() {
        Double thanhTien = 0.0;
        for (int i = 0; i < tbmThuocDaChon.getRowCount(); i++) {
            int soLuong = (int) tbmThuocDaChon.getValueAt(i, 4);
            Double donGia = (Double) tbmThuocDaChon.getValueAt(i, 2);
            thanhTien += soLuong * donGia;
        }
        DecimalFormat df = new DecimalFormat("#,###");

        // Gán vào text field (có thể thêm "VNĐ" nếu muốn)
        txtThanhTien.setText(df.format(thanhTien) + " VNĐ");
    }


	@Override
	public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(txtSoLuong)) {
            handleCapNhatThuocDaChon();
        }
        if (o.equals(btnLuuHD)){
            // Lưu hóa đơn
            try {
                HoaDonService hoaDonService = (HoaDonService) Naming.lookup("rmi://localhost:9090/hoaDonService");
                KhachHangService khachHangService = (KhachHangService) Naming.lookup("rmi://localhost:9090/khachHangService");
                ThuocService thuocService = (ThuocService) Naming.lookup("rmi://localhost:9090/thuocService");
                if (tbmThuocDaChon.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc trước khi lưu hóa đơn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int maHD = Integer.parseInt(txtMaHD.getText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(lblHienThiNgayTao.getText(), formatter);
                LocalDateTime dateTime = date.atStartOfDay(); // tạo LocalDateTime với giờ 00:00
                NhanVien nhanVien = Form_DangNhap.nhanVien;
                KhachHang khachHang = khachHangService.timBangSDT(cmbSDT.getText());
                HoaDon hoaDon = new HoaDon(nhanVien, khachHang, dateTime, "Đã thanh toán");
                List<ChiTietHoaDon> danhSachChiTiet = new ArrayList<>();
                for (int i = 0; i < tbmThuocDaChon.getRowCount(); i++) {
                    Integer maThuoc = (Integer) tbmThuocDaChon.getValueAt(i, 0);
                    Integer soLuong = (Integer) tbmThuocDaChon.getValueAt(i, 4);
                    String donViTinh = (String) tbmThuocDaChon.getValueAt(i, 3);
                    Double donGia = (Double) tbmThuocDaChon.getValueAt(i, 2);
                    Thuoc thuoc = thuocService.findById(maThuoc);
                    ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon( thuoc, hoaDon, soLuong , donGia, donViTinh);
                    danhSachChiTiet.add(chiTietHoaDon);
                }
                if (hoaDonService.luuHoaDon(hoaDon, danhSachChiTiet)){
                    JOptionPane.showMessageDialog(this, "Lưu hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    txtKhachHang.setText("");
                    txtTenThuoc.setText("");
                    txtSoLuong.setText("");
                    txtDonVi.setText("");
                    txtDonGia.setText("");
                    txtThanhTien.setText("");
                    txtMaHD.setText(String.valueOf(hoaDonService.layMaHoaDonMoiNhat() + 1));
                    // Xóa dữ liệu trong bảng thuốc đã chọn
                    tbmThuocDaChon.setRowCount(0);
                }else {
                    JOptionPane.showMessageDialog(this, "Lưu hóa đơn thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi lưu hóa đơn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (o.equals(btnTaoMoiHD)) {
            // Xóa dữ liệu trong các trường
            txtKhachHang.setText("");
            txtTenThuoc.setText("");
            txtSoLuong.setText("");
            txtDonVi.setText("");
            txtDonGia.setText("");
            txtThanhTien.setText("");
            // Xóa dữ liệu trong bảng thuốc đã chọn
            tbmThuocDaChon.setRowCount(0);
        }
        if (o.equals(btnThemKH)) {
            // Tạo đối tượng Form_ADDKH
            Form_ADDKH addkh = new Form_ADDKH();

            // Lắng nghe sự kiện khi cửa sổ đóng
            addkh.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // Gọi updateKH() sau khi cửa sổ đóng
                    updatePhoneList();
                }
            });

            // Hiển thị cửa sổ Form_ADDKH
            addkh.setVisible(true);
        }
	}


}


