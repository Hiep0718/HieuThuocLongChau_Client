package iuh.fit.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Form_KhachHang extends JPanel {
	private JLabel tieuDe;
	private DefaultTableModel tableModel;

	public Form_KhachHang() {
		initForm();
	}

	private void initForm() {
		setLayout(new BorderLayout());

		// Tiêu đề
		JPanel jpTieuDe = new JPanel(new BorderLayout());
		tieuDe = new JLabel("Danh mục khách hàng");
		tieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		tieuDe.setFont(tieuDe.getFont().deriveFont(20f));
		jpTieuDe.add(tieuDe, BorderLayout.CENTER);

		// Bảng hiển thị
		String[] columns = {"Mã khách hàng", "Tên khách hàng", "Điện thoại", "Email", "Trạng thái"};
		tableModel = new DefaultTableModel(columns, 0);
		JTable table = new JTable(tableModel);
		table.getTableHeader().setBackground(new Color(76, 175, 80));
		table.getTableHeader().setForeground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(table);

		add(jpTieuDe, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}
}
