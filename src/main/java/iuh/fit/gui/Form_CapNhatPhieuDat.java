package iuh.fit.gui;


import model.NhanVien;
import model.PhieuDatThuoc;
import services.NhanVienService;
import services.PhieuDatThuocService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.Naming;
import java.util.List;

public class Form_CapNhatPhieuDat extends JPanel{
	 private JLabel lblTimKiem;
	    private JTextField txtTimKiem;
	    private JButton btnTimKiem, btnXoa;
	    private JTable tblKetQua;
	    private JScrollPane scrollPane;
		private DefaultTableModel data;
		List<PhieuDatThuoc> list;

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
           updateTablePDT();

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
	private void updateTablePDT()  {
		// Xóa dữ liệu cũ trong bảng thuốc
		data.setRowCount(0);
		try {
			PhieuDatThuocService  phieuDatThuocService = (PhieuDatThuocService) Naming.lookup("rmi://localhost:9090/phieuDatThuocService");
			list =phieuDatThuocService.getAll();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ thuốc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		// Thêm dữ liệu mới vào bảng thuốc
		for (PhieuDatThuoc pdt : list) {
			Object[] rowData = {
					pdt.getMaPDT(),
					pdt.getKhachHang().getTenKH(),
					pdt.getNgayLapPhieuDat(),
					pdt.getKhachHang().getSoDienThoai(),
					pdt.getNhanVien().getTenNV(),
					pdt.getGhiChu()



			};
			data.addRow(rowData);
		}
	}
}
