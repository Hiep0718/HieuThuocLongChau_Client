package iuh.fit.gui;

import model.Thuoc;
import services.ThuocService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class Form_Thuoc extends JPanel {

    private DefaultTableModel table_modal;
    private JTable table;
    private JScrollPane jcroll;
    private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8;
    private JTextField jtex1, jtex2, jtex3, jtex4, jtex5, jtex6, jtex7, jtex8;
    private JButton btn_Them, btn_Xoa, btn_XoaTrang;
    private List<Thuoc> dsThuoc;

    public Form_Thuoc() {
        form();
    }

    public void form() {
        dsThuoc = new ArrayList<>();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Danh mục thuốc");
        title.setFont(title.getFont().deriveFont(24.0f)); // Set font size
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH); // Add title at the top

        // Center Panel for Input Fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 2), // Outer border
                        BorderFactory.createEmptyBorder(10, 10, 10, 10) // Inner padding
                ),
                "Nhập thông tin thuốc"
        ));

        // Create JLabel and JTextField for each input field
        jl1 = new JLabel("Mã thuốc:");
        jl2 = new JLabel("Tên thuốc:");
        jl3 = new JLabel("Đơn giá:");
        jl4 = new JLabel("Số lượng tồn:");
        jl5 = new JLabel("Danh mục:");
        jl6 = new JLabel("Lô thuốc:");
        jl7 = new JLabel("Nhà sản xuất:");
        jl8 = new JLabel("Mô tả:");

        // Create JTextFields
        jtex1 = new JTextField(10);
        jtex2 = new JTextField(10);
        jtex3 = new JTextField(10);
        jtex4 = new JTextField(10);
        jtex5 = new JTextField(10);
        jtex6 = new JTextField(10);
        jtex7 = new JTextField(10);
        jtex8 = new JTextField(10);

        // Add input fields to the input panel
        inputPanel.add(createRow(jl1, jtex1, jl2, jtex2));
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(createRow(jl3, jtex3, jl4, jtex4));
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(createRow(jl5, jtex5, jl6, jtex6));
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(createRow(jl7, jtex7, jl8, jtex8));

//        add(inputPanel, BorderLayout.CENTER); // Add input panel to center

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        btn_Them = new JButton("     Thêm     ");
        btn_Xoa = new JButton("      Xóa        ");
        btn_XoaTrang = new JButton("   Xóa trắng ");

        // Set button colors
        btn_Them.setBackground(new Color(76, 175, 80)); // Green
        btn_Xoa.setBackground(new Color(244, 67, 54)); // Red
        btn_XoaTrang.setBackground(new Color(255, 152, 0)); // Orange

        buttonPanel.add(btn_Them);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(btn_Xoa);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(btn_XoaTrang);
//        add(buttonPanel, BorderLayout.EAST); // Add button panel to east

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(""));

        Object[] col = {"Mã thuốc", "Tên thuốc", "Số lượng", "Đơn vị tính", "Giá bán", "Giá nhập", "Loại thuốc"};
        table_modal = new DefaultTableModel(col, 0);
        updateTableThuoc();
        table = new JTable(table_modal);

        jcroll = new JScrollPane(table);

        // Set preferred size to increase height by 200 pixels
        Dimension scrollPaneSize = new Dimension(900, 350); // Increase height here to 400 pixels
        jcroll.setPreferredSize(scrollPaneSize); // Adjust height here

        tablePanel.add(jcroll, BorderLayout.CENTER); // Add table to table panel

        // Change header colors
        table.getTableHeader().setBackground(new Color(76, 175, 80)); // Header background color
        table.getTableHeader().setForeground(Color.WHITE); // Header text color

        // Change row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(224, 224, 224)); // Even row background color
                } else {
                    c.setBackground(Color.WHITE); // Odd row background color
                }
                return c;
            }
        });

        add(tablePanel); // Add table panel to south
    }

    // Method to create a row for the input fields
    private JPanel createRow(JLabel label1, JTextField textField1, JLabel label2, JTextField textField2) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.add(label1);
        row.add(textField1);
        row.add(Box.createHorizontalStrut(20)); // Spacer
        row.add(label2);
        row.add(textField2);
        return row;
    }

    private void updateTableThuoc()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        table_modal.setRowCount(0);
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
            table_modal.addRow(rowData);
        }
    }
}
