package iuh.fit.gui;

import model.KhachHang;
import services.KhachHangService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Form_KhachHang extends JPanel {
	private JLabel tieuDe;
	private DefaultTableModel tableModel;
	private List<KhachHang> dsKH;

	public Form_KhachHang() {
		initForm();
	}

	private void initForm() {
		dsKH = new ArrayList<>();
		setLayout(new BorderLayout());

		// Tiêu đề
		JPanel jpTieuDe = new JPanel(new BorderLayout());
		tieuDe = new JLabel("Danh mục khách hàng");
		tieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		tieuDe.setFont(tieuDe.getFont().deriveFont(20f));
		jpTieuDe.add(tieuDe, BorderLayout.CENTER);

		// Bảng hiển thị
		String[] columns = {"Mã khách hàng", "Tên khách hàng", "Số Điện Thoại", "Email", "Địa chỉ", "Ngày sinh"};
		tableModel = new DefaultTableModel(columns, 0);
		updateTableKH();
		JTable table = new JTable(tableModel);
		table.getTableHeader().setBackground(new Color(76, 175, 80));
		table.getTableHeader().setForeground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(table);

		add(jpTieuDe, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void updateTableKH()  {
		// Xóa dữ liệu cũ trong bảng thuốc
		tableModel.setRowCount(0);
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
			tableModel.addRow(rowData);
		}
	}
}
