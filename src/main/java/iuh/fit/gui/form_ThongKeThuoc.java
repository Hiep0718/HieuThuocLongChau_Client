package iuh.fit.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;


public class form_ThongKeThuoc extends JPanel implements ActionListener{

	private JPanel panelTieuDe;
	private Label lblTieuDe;
	private DefaultTableModel tbmThuoc;
	private JTable tblThuoc;
	private JScrollPane scrollThuoc;
	private JButton btnThongKe;
	private JComboBox comboBox;
	private Temporal today;
	private JRadioButton rbtnThuocHetHan;
	private JRadioButton rbtnThuocBanChay;
	private ButtonGroup groupRadio;
	private JRadioButton rbtnThuocGanHetHan;
	private DefaultTableModel tbmThuocBanChay;

	public form_ThongKeThuoc() {
		// Cấu hình cửa sổ
        setSize(1000, 750); 
        setLayout(new BorderLayout(10, 10));

        // Thêm tiêu đề cho cửa sổ
        panelTieuDe = new JPanel();
        panelTieuDe.setLayout(new BorderLayout());

        lblTieuDe = new Label("Thống kê thuốc");
        lblTieuDe.setFont(new Font("Open Sans", Font.BOLD, 25));
        lblTieuDe.setForeground(new Color(0, 102, 204));
        panelTieuDe.setBackground(new Color(245, 245, 245));
        lblTieuDe.setAlignment(Label.CENTER);

        panelTieuDe.add(lblTieuDe, BorderLayout.CENTER);
        add(panelTieuDe, BorderLayout.NORTH);

        // Tạo panel tác vụ
        JPanel panelTacVu = new JPanel(new GridBagLayout());
        panelTacVu.setBorder(BorderFactory.createTitledBorder("Thống kê thuốc gần hết hạn"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Thêm ngày tháng hôm nay
        LocalDate today = LocalDate.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd 'tháng' MM, yyyy");      
        String formattedDate = today.format(formatter);
        JLabel lblNgayHomNay = new JLabel("Hôm nay: " + formattedDate);
        lblNgayHomNay.setFont(new Font("Arial", Font.BOLD, 18));
        lblNgayHomNay.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelTacVu.add(lblNgayHomNay, gbc);

        // Thêm Label mô tả ComboBox
        JLabel lblThoiGian = new JLabel("Chọn thời gian thống kê:");
        lblThoiGian.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelTacVu.add(lblThoiGian, gbc);

        // Thêm ComboBox
        String[] thoiGian = {"1 tuần", "2 tuần", "3 tuần", "1 tháng", "2 tháng", "6 tháng", "1 năm"};
        comboBox = new JComboBox<>(thoiGian);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelTacVu.add(comboBox, gbc);
        
        // Tạo JLabel 
        JLabel lblGhiChu = new JLabel("*Áp dụng cho thống kê các thuốc gần hết hạn");
        lblGhiChu.setFont(new Font("Arial", Font.ITALIC, 14)); 
        lblGhiChu.setForeground(Color.RED); 
        gbc.gridx = 2;
        gbc.gridy = 1;
        panelTacVu.add(lblGhiChu, gbc);
        
        // Thêm JRadioButton cho Thuốc hết hạn và Thuốc bán chạy
        rbtnThuocGanHetHan = new JRadioButton("Thuốc gần hết hạn");
        rbtnThuocHetHan = new JRadioButton("Thuốc hết hạn");
        rbtnThuocBanChay = new JRadioButton("Thuốc bán chạy");
        
        // Tăng kích thước phông chữ cho các JRadioButton
        Font font = new Font("Arial", Font.PLAIN, 16); 
        rbtnThuocGanHetHan.setFont(font);
        rbtnThuocHetHan.setFont(font);
        rbtnThuocBanChay.setFont(font);
        
        // Nhóm các JRadioButton lại với nhau
        groupRadio = new ButtonGroup();
        groupRadio.add(rbtnThuocGanHetHan);
        groupRadio.add(rbtnThuocHetHan);
        groupRadio.add(rbtnThuocBanChay);

        // Đặt JRadioButton vào giao diện
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelTacVu.add(rbtnThuocGanHetHan, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelTacVu.add(rbtnThuocHetHan, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        panelTacVu.add(rbtnThuocBanChay, gbc);
        // Thêm nút Thống kê
        btnThongKe = new JButton("Thống kê");
        btnThongKe.setFont(new Font("Arial", Font.BOLD, 14));
        btnThongKe.setBackground(new Color(0, 102, 204));
        btnThongKe.setForeground(Color.WHITE);
        btnThongKe.addActionListener(this);

        gbc.gridx = 3;
        gbc.gridy = 2;
        panelTacVu.add(btnThongKe, gbc);

        add(panelTacVu, BorderLayout.CENTER);



        String[] columnNamesThuoc = {"Mã thuốc", "Tên thuốc", "Đơn giá", "Số lượng tồn", "Tên nhà sản xuất", "Quốc gia", "Ngày sản xuất", "Ngày hết hạn", "Thời gian còn lại"};
        tbmThuoc = new DefaultTableModel(columnNamesThuoc, 0);
        tblThuoc = new JTable(tbmThuoc);
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
        // Tạo panel thống kê
        JPanel panelThongKe = new JPanel(new BorderLayout());
        panelThongKe.setBorder(BorderFactory.createTitledBorder("Thống kê"));
        panelThongKe.add(scrollThuoc, BorderLayout.CENTER);
        add(panelThongKe, BorderLayout.SOUTH);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	public static int chuyenNgay(String thoiGian) {
        // Dựa trên đơn vị để tính số ngày

        return 0;
    }
   
}
