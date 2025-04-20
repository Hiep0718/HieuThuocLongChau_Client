package iuh.fit.gui;

import model.KhachHang;
import model.Thuoc;
import services.KhachHangService;
import services.ThuocService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.List;

public class Form_TimKhachHang extends JPanel {
    // Khai báo các thành phần của form
    private JLabel lblTimKiem;
    private JTextField txtTimKiem;
    private JButton btnTimKiem, btnThoat;
    private JTable tblKetQua;
    private JScrollPane scrollPane;
	private DefaultTableModel data;
    private List<KhachHang> dsKH;

    public Form_TimKhachHang() {

    	
        // Tạo các thành phần
        lblTimKiem = new JLabel("Nhập mã hoặc tên khách hàng:");
        txtTimKiem = new JTextField(20);
        btnTimKiem = new JButton("Tìm Kiếm");
        btnThoat = new JButton("Thoát");
        
        // Dữ liệu mẫu cho bảng kết quả
        String[] columnNames = {"Mã khách hàng", "Tên khách hàng", "Số Điện Thoại", "Email", "Địa chỉ", "Ngày sinh"};
        data = new DefaultTableModel(columnNames, 0);
        updateTableKH();
        tblKetQua = new JTable(data);

        scrollPane = new JScrollPane(tblKetQua);

        // Panel để chứa các thành phần tìm kiếm
        JPanel panelSearch = new JPanel();
        panelSearch.add(lblTimKiem);
        panelSearch.add(txtTimKiem);
        panelSearch.add(btnTimKiem);

        // Panel để chứa bảng kết quả
        JPanel panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());
        panelTable.add(scrollPane, BorderLayout.CENTER);

        // Panel để chứa nút Thoát
        JPanel panelButtons = new JPanel();
        panelButtons.add(btnThoat);

        // Thêm các panel vào Frame
        setLayout(new BorderLayout());
        add(panelSearch, BorderLayout.NORTH);
        add(panelTable, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        // Xử lý sự kiện khi nhấn nút Tìm Kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = txtTimKiem.getText();
                // Thực hiện tìm kiếm khách hàng theo keyword (chưa xử lý dữ liệu thực)
                JOptionPane.showMessageDialog(null, "Đang tìm khách hàng với từ khóa: " + keyword);
            }
        });


    }

    private void updateTableKH()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        data.setRowCount(0);
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
            data.addRow(rowData);
        }
    }
}
