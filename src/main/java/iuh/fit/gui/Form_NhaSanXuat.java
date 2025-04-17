package iuh.fit.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Form_NhaSanXuat extends JPanel {

	 private DefaultTableModel tableModel;

    public Form_NhaSanXuat() {
        form();
    }

    public void form() {
setLayout(new BorderLayout());
		
		JPanel jpTieuDe=new JPanel();
		jpTieuDe.setLayout(new BorderLayout());
		
		JLabel tieuDe = new JLabel("Danh mục nhà sản xuất");
		tieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		tieuDe.setFont(tieuDe.getFont().deriveFont(20f));
		jpTieuDe.add(tieuDe);
		
		Object [] row= {"Mã nhà sản xuất","Tên nhà sản xuất","Website","Quốc gia"};
		
		// Tạo bảng với DefaultTableModel
         tableModel = new DefaultTableModel(row,0);
        JTable table = new JTable(tableModel);
        // Change header colors
        table.getTableHeader().setBackground(new Color(76, 175, 80)); // Header background color
        table.getTableHeader().setForeground(Color.WHITE); // Header text color
        // Đặt bảng vào JScrollPane để hỗ trợ cuộn
        JScrollPane scrollPane = new JScrollPane(table);
        
        this.add(jpTieuDe,BorderLayout.NORTH);
        this.add(scrollPane,BorderLayout.CENTER);
		
		updateTable();
	}

	private void updateTable() {
	}
    }

