package iuh.fit.gui;

import model.NhaCungCap;
import services.NhaSanXuatService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Form_NhaSanXuat extends JPanel {

	 private DefaultTableModel tableModel;
    private List<NhaCungCap> dsNSX;

    public Form_NhaSanXuat() {
        form();
    }

    public void form() {
setLayout(new BorderLayout());
dsNSX = new ArrayList<>();
		
		JPanel jpTieuDe=new JPanel();
		jpTieuDe.setLayout(new BorderLayout());
		
		JLabel tieuDe = new JLabel("Danh mục nhà sản xuất");
		tieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		tieuDe.setFont(tieuDe.getFont().deriveFont(20f));
		jpTieuDe.add(tieuDe);
		
		Object [] row= {"Mã NSX", "Tên NSX", "Website", "Email", "Địa chỉ", "Số điện thoại"};
		
		// Tạo bảng với DefaultTableModel
         tableModel = new DefaultTableModel(row,0);
        updateTableNCC();
        JTable table = new JTable(tableModel);
        // Change header colors
        table.getTableHeader().setBackground(new Color(76, 175, 80)); // Header background color
        table.getTableHeader().setForeground(Color.WHITE); // Header text color
        // Đặt bảng vào JScrollPane để hỗ trợ cuộn
        JScrollPane scrollPane = new JScrollPane(table);
        
        this.add(jpTieuDe,BorderLayout.NORTH);
        this.add(scrollPane,BorderLayout.CENTER);
		

	}

    private void updateTableNCC()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        tableModel.setRowCount(0);
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
            tableModel.addRow(rowData);
        }
    }
    }

