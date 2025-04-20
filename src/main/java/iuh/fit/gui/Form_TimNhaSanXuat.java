package iuh.fit.gui;

import model.KhachHang;
import model.NhaCungCap;
import services.KhachHangService;
import services.NhaSanXuatService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Form_TimNhaSanXuat extends JPanel {
    // Khai báo các thành phần của form
    private JLabel lblTimKiem;
    private JTextField txtTimKiem;
    private JButton btnTimKiem, btnThoat;
    private JTable tblKetQua;
    private JScrollPane scrollPane;
    private DefaultTableModel data;
    private List<NhaCungCap> dsNSX;

    public Form_TimNhaSanXuat() {
        dsNSX = new ArrayList<NhaCungCap>();

        // Tạo các thành phần
        lblTimKiem = new JLabel("Nhập mã hoặc tên nhà sản xuất:");
        txtTimKiem = new JTextField(20);
        btnTimKiem = new JButton("Tìm Kiếm");
        btnThoat = new JButton("Thoát");
        
        // Dữ liệu mẫu cho bảng kết quả
        String[] columnNames = {"Mã NSX", "Tên NSX", "Website", "Email", "Địa chỉ", "Số điện thoại"};
        data = new DefaultTableModel(columnNames, 0);
         updateTableNCC();
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

    private void updateTableNCC()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        data.setRowCount(0);
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
            data.addRow(rowData);
        }
    }
}
