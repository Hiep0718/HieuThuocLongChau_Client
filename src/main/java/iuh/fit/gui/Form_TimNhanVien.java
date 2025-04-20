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
import java.util.ArrayList;
import java.util.List;

public class Form_TimNhanVien extends JPanel{
    // Khai báo các thành phần của form
    private JLabel lblTimKiem;
    private JTextField txtTimKiem;
    private JButton btnTimKiem, btnThoat;
    private JTable tblKetQua;
    private JScrollPane scrollPane;
	private DefaultTableModel data;
    private List<NhanVien> dsNV;

    public Form_TimNhanVien() {
        dsNV = new ArrayList<>();
        // Tạo các thành phần
        lblTimKiem = new JLabel("Nhập mã nhân viên:");
        txtTimKiem = new JTextField(20);
        btnTimKiem = new JButton("Tìm Kiếm");
        btnThoat = new JButton("Thoát");
        
        // Dữ liệu mẫu cho bảng kết quả
        String[] columnNames = {"Mã NV","Tên nhân viên", "Chú thích","Chức vụ", "Địa chỉ","Email", "Ngày sinh", "Ngày vào làm", "Số điện thoại"};
        data = new DefaultTableModel(columnNames, 0);
        updateTableNhanVien();
        tblKetQua = new JTable(data);
        
        //Load dữ liệu lên table

        
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


            }

			private void timNV(String keyword) {

			}
        });

        // Xử lý sự kiện khi nhấn nút Thoát
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                dispose();  // Đóng form
            }
        });
    }
    private void updateTableNhanVien()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        data.setRowCount(0);
        try {
            NhanVienService NVService = (NhanVienService) Naming.lookup("rmi://localhost:9090/nhanVienService");
            dsNV = NVService.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // Thêm dữ liệu mới vào bảng thuốc
        for (NhanVien nv : dsNV) {
            Object[] rowData = {
                    nv.getMaNV(),
                    nv.getTenNV(),
                    nv.getChuThich(),
                    nv.getChucVu(),
                    nv.getDiaChi(),
                    nv.getEmail(),
                    nv.getNgaySinh(),
                    nv.getNgayVaoLam(),
                    nv.getSoDienThoai()
            };
            data.addRow(rowData);
        }
    }

}
