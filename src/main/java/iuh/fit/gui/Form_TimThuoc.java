package iuh.fit.gui;

import model.Thuoc;
import services.ThuocService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Form_TimThuoc extends JPanel{
    // Khai báo các thành phần của form
    private JLabel lblTimKiem;
    private JTextField txtTimKiem;
    private JButton btnTimKiem, btnThoat;
    private JTable tblKetQua;
    private JScrollPane scrollPane;
	private DefaultTableModel data;
    private List<Thuoc> dsThuoc;

    public Form_TimThuoc() {
        dsThuoc = new ArrayList<>();

        // Tạo các thành phần
        lblTimKiem = new JLabel("Nhập mã hoặc tên thuốc:");
        txtTimKiem = new JTextField(20);
        btnTimKiem = new JButton("Tìm Kiếm");
        btnThoat = new JButton("Thoát");
        
        // Dữ liệu mẫu cho bảng kết quả
        String[] columnNames = {"Mã thuốc", "Tên thuốc", "Số lượng", "Đơn vị tính", "Giá bán", "Giá nhập", "Loại thuốc"};
        data = new DefaultTableModel(columnNames, 0);
        updateTableThuoc();
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
            }
        });


    }

    private void updateTableThuoc()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        data.setRowCount(0);
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
                    thuoc.getSoLuong(),
                    thuoc.getDonViTinh(),
                    thuoc.getGiaBan(),
                    thuoc.getGiaNhap(),
                    thuoc.getLoaiThuoc()
            };
            data.addRow(rowData);
        }
    }


}
