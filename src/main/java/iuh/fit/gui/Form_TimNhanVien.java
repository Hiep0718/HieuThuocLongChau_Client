package iuh.fit.gui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_TimNhanVien extends JPanel{
    // Khai báo các thành phần của form
    private JLabel lblTimKiem;
    private JTextField txtTimKiem;
    private JButton btnTimKiem, btnThoat;
    private JTable tblKetQua;
    private JScrollPane scrollPane;
	private DefaultTableModel data;

    public Form_TimNhanVien() {
        // Tạo các thành phần
        lblTimKiem = new JLabel("Nhập mã nhân viên:");
        txtTimKiem = new JTextField(20);
        btnTimKiem = new JButton("Tìm Kiếm");
        btnThoat = new JButton("Thoát");
        
        // Dữ liệu mẫu cho bảng kết quả
        String[] columnNames = {"Mã NV", "Họ Tên","Tuổi","Giới tính", "Điện thoại","Email", "Địa chỉ", "Trạng thái", "Chức vụ"};
        data = new DefaultTableModel(columnNames, 0);
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

}
