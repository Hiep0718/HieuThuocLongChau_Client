package iuh.fit.gui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Form_CapNhatPhieuDat extends JPanel{
	 private JLabel lblTimKiem;
	    private JTextField txtTimKiem;
	    private JButton btnTimKiem, btnXoa;
	    private JTable tblKetQua;
	    private JScrollPane scrollPane;
		private DefaultTableModel data;

	    public Form_CapNhatPhieuDat() {
			// Tạo các thành phần
			lblTimKiem = new JLabel("Nhập sdt khách hàng:");
			txtTimKiem = new JTextField(20);
			btnTimKiem = new JButton("Tìm Kiếm");
			btnXoa = new JButton("Xóa");

			// Dữ liệu mẫu cho bảng kết quả
			String[] columnNames = {"Mã phiếu đặt", "Tên khách hàng ", "ngày đặt", "Điện thoại", "Nhân viên", "Trạng thái",};
			data = new DefaultTableModel(columnNames, 0);
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
			panelButtons.add(btnXoa);

			// Thêm các panel vào Frame
			setLayout(new BorderLayout());
			add(panelSearch, BorderLayout.NORTH);
			add(panelTable, BorderLayout.CENTER);
			add(panelButtons, BorderLayout.SOUTH);

		}
}
