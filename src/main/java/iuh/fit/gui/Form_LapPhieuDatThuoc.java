package iuh.fit.gui;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Form_LapPhieuDatThuoc extends JPanel implements ListSelectionListener, ActionListener {

	private JLabel lblPDT;
	private JTextField txtPDT;
	private JLabel lblNgayTaoPDT;
	private JLabel lblHienThiNgayTao;
	private JTextField txtNhanVien;
	private JLabel lblNhanVien;
	private JLabel lblKhachHang;
	private JTextField txtKhachHang;
	private JLabel lblSDT;
	private JComboBox<String> cmbSDT;
	private JLabel lblTenThuoc;
	private JTextField txtTenThuoc;
	private JTextField txtSoLuong;
	private JLabel lblSoLuong;
	private JLabel lblDonVi;
	private JTextField txtDonVi;
	private JTextField txtDonGia;
	private JLabel lblDonGia;
	private JLabel lblThanhTien;
	private JTextField txtThanhTien;
	private JLabel lblPhieuDatThuoc;
	private JComboBox<String> cmbPhieuDatThuoc;
	private JButton btnTaoMoiPDT;
	private JButton btnLuuPDT;
	private JButton btnXoaPDT;
	private JLabel lblTimKiemThuoc;
	private JButton btnTimKiemThuoc;
	private JTextField txtTimKiemThuoc;
	private JButton btnQuetMa;
	private JTable tblThuoc;
	private JScrollPane scrollThuoc;
	private JTable tblThuocDaChon;
	private JScrollPane scrollThuocDaChon;
	private Label lblTieuDe;
	private JPanel panelTieuDe;
	private DefaultTableModel tbmThuoc;
	private JPanel panelThuoc2;
	private JLabel lblGioHang;
	private JPanel panelGioHang;
	private DefaultTableModel tbmThuocDaChon;



	public Form_LapPhieuDatThuoc() {
        // Cấu hình cửa sổ
 
        setSize(1000, 750); // Kích thước ứng dụng là 1000x750

 
        setLayout(new BorderLayout(10, 10));


     // Tạo khu vực tác vụ (khách hàng, nhân viên, hóa đơn)
        JPanel panelTacVu = new JPanel(new GridBagLayout());
        panelTacVu.setBorder(BorderFactory.createTitledBorder("Thông tin Phiếu đặt thuốc"));

        GridBagConstraints gbc = new GridBagConstraints();
        // Điều chỉnh khoảng cách giữa các thành phần
        gbc.insets = new Insets(5, 5, 5, 5);  // Thêm khoảng trống giữa các thành phần trong panelTacVu
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        lblPDT = new JLabel("Mã phiếu đặt thuốc:");
        txtPDT = new JTextField(15);
        customizeTextField(txtPDT, false);
        panelTacVu.add(lblPDT, gbc);
        gbc.gridx = 1;
        panelTacVu.add(txtPDT, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        lblNgayTaoPDT = new JLabel("Ngày tạo phiếu đặt:");
        panelTacVu.add(lblNgayTaoPDT, gbc);

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
        gbc.gridx = 0;
        gbc.gridy = 4;
        lblSDT = new JLabel("Số điện thoại:");
        cmbSDT = new JComboBox<String>();
        panelTacVu.add(lblSDT, gbc);
        gbc.gridx = 1;
        cmbSDT.setPreferredSize(new Dimension(txtPDT.getWidth(), 30));
        panelTacVu.add(cmbSDT, gbc);
        updateKH();
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
        txtSoLuong.addActionListener(this);
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

        gbc.gridx = 0;
        gbc.gridy = 7;

        lblPhieuDatThuoc = new JLabel("Phiếu đặt thuốc:");
        cmbPhieuDatThuoc = new JComboBox<String>();
        cmbPhieuDatThuoc.setPreferredSize(new Dimension(txtSoLuong.getWidth(), 30));

        panelTacVu.add(lblPhieuDatThuoc, gbc);
        gbc.gridx = 1;
        panelTacVu.add(cmbPhieuDatThuoc, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        btnTaoMoiPDT = new JButton("Tạo mới");
        btnTaoMoiPDT.setBackground(new Color(34, 139, 34)); // Màu xanh lá cây đậm
        btnTaoMoiPDT.setForeground(Color.WHITE);
        panelTacVu.add(btnTaoMoiPDT, gbc);
        gbc.gridx = 1;
        btnLuuPDT = new JButton("Lưu");
        btnLuuPDT.setBackground(new Color(70, 130, 180)); // Màu xanh lam
        btnLuuPDT.setForeground(Color.WHITE);
        panelTacVu.add(btnLuuPDT, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        btnXoaPDT = new JButton("Xóa");
        gbc.gridwidth = 2;
        btnXoaPDT.setBackground(new Color(255, 165, 0)); // Màu cam
        btnXoaPDT.setForeground(Color.WHITE);
        panelTacVu.add(btnXoaPDT, gbc);


        // Tạo khu vực thuốc
     // Tạo panelThuoc2 với BoxLayout theo chiều dọc
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
        String[] columnNamesThuoc = {"Mã thuốc", "Tên thuốc", "Đơn giá", "Số lượng tồn", "Đơn vị tính"};
        tbmThuoc = new DefaultTableModel(columnNamesThuoc, 0);
        tblThuoc = new JTable(tbmThuoc);
        tblThuoc.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleTblThuocSelection();
            }
        });

        
        
        // Tùy chỉnh bảng thuốc với màu nền xen kẽ giữa các hàng
        tblThuoc.setShowGrid(true);
        tblThuoc.setGridColor(Color.LIGHT_GRAY);
        tblThuoc.setRowHeight(30);  // Tăng chiều cao của hàng
        tblThuoc.setSelectionBackground(new Color(135, 206, 235));  // Màu nền khi được chọn
        tblThuoc.setFont(new Font("Open Sans", Font.PLAIN, 14));  // Phông chữ cho bảng
        
        // Đặt màu nền cho tiêu đề bảng
        tblThuoc.getTableHeader().setBackground(new Color(70, 130, 180));
        tblThuoc.getTableHeader().setForeground(Color.WHITE);
        tblThuoc.getTableHeader().setFont(new Font("Open Sans", Font.BOLD, 14));
        
        scrollThuoc = new JScrollPane(tblThuoc);

        String[] columnNamesThuocDaChon = {"Mã thuốc", "Tên thuốc", "Số lượng", "Đơn giá", "Đơn vị tính"};
        tbmThuocDaChon = new DefaultTableModel(columnNamesThuocDaChon, 0);
        
        tblThuocDaChon = new JTable(tbmThuocDaChon);
        
        // Tùy chỉnh bảng thuốc với màu nền xen kẽ giữa các hàng
        tblThuocDaChon.setShowGrid(true);
        tblThuocDaChon.setGridColor(Color.LIGHT_GRAY);
        tblThuocDaChon.setFont(new Font("Open Sans", Font.PLAIN, 14));
        tblThuocDaChon.setRowHeight(30);  // Điều chỉnh chiều cao hàng cho bảng thuốc đã chọn
        tblThuocDaChon.setSelectionBackground(new Color(135, 206, 235));  // Màu nền khi được chọn

        // Đặt màu nền cho tiêu đề bảng
        tblThuocDaChon.getTableHeader().setBackground(new Color(70, 130, 180));
        tblThuocDaChon.getTableHeader().setForeground(Color.WHITE);
        tblThuocDaChon.getTableHeader().setFont(new Font("Open Sans", Font.BOLD, 14));
        
        tblThuocDaChon.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleTblThuocDaChonSelection();
            }
        });
        
        scrollThuocDaChon = new JScrollPane(tblThuocDaChon);
        
        
        panelGioHang = new JPanel();
        panelGioHang.setLayout(new BorderLayout());
        panelGioHang.setBorder(BorderFactory.createTitledBorder("Danh sách thuốc mua"));
        panelGioHang.setSize(new Dimension(scrollThuoc.getPreferredSize()));
        panelGioHang.add(scrollThuocDaChon, BorderLayout.CENTER);
        
        
//        scrollThuocDaChon.setPreferredSize(new Dimension(scrollThuocDaChon.getWidth(), 600));;
        // Thêm các thành phần vào khu vực thuốc
        panelThuoc2.add(panelTimKiemThuoc);
        panelThuoc2.add(scrollThuoc);
        panelThuoc2.add(panelGioHang);

        //Thêm tiêu đề cho cửa sổ
        panelTieuDe = new JPanel();
        lblTieuDe = new Label("LẬP PHIẾU ĐẶT THUỐC");

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
        cmbPhieuDatThuoc.addActionListener(this);
        // Xử lý sự kiện các nút (nếu cần)
        btnTaoMoiPDT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý tạo mới hóa đơn
            	xoaBang();
            	txtDonGia.setText("");
            	txtDonVi.setText("");
            	txtKhachHang.setText("");
            	cmbSDT.setSelectedIndex(0);;
            	txtPDT.setText("");
            	txtSoLuong.setText("");
            	txtThanhTien.setText("");
            	txtTimKiemThuoc.setText("");
            	txtTenThuoc.setText("");
            	
            }
        });

        btnXoaPDT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnLuuPDT.addActionListener(new ActionListener() {
          
			@Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnTimKiemThuoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý tìm kiếm thuốc
            	
            }
        });
        updatePDT();
    }
    private void updateKH() {
		// TODO Auto-generated method stub
	}
    private void updatePDT() {
	}

	// Phương thức lấy ngày hiện tại theo định dạng dd/MM/yyyy
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return sdf.format(date);
    }
    public void xoaBang() {
    }

    private void customizeTextField(JTextField textField, boolean editable) {
    }


    private void handleTblThuocSelection() {

    }
    private void handleTblThuocDaChonSelection() {
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	private void updateThanhTien() {

		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
   
}


