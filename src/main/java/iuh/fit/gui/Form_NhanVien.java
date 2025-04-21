package iuh.fit.gui;

import model.NhanVien;
import services.NhanVienService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;


public class Form_NhanVien extends JPanel {

    private DefaultTableModel table_modal;
    private JTable table;
    private JScrollPane jcroll;
    private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7;
    private JTextField jtex1, jtex2, jtex3, jtex4, jtex5, jtex6, jtex7;
    private JButton btn_Them, btn_Xoa, btn_XoaTrang;

    private List<NhanVien> dsNV;

    public Form_NhanVien() {
        form();
    }

    public void form() {
        setLayout(new BorderLayout());
        dsNV = new ArrayList<>();
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Danh mục nhân viên");
        title.setFont(title.getFont().deriveFont(24.0f)); // Set font size
        title.setForeground(new Color(64, 244, 180));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH); // Add title at the top

        // Center Panel for Input
        JPanel penRight = new JPanel();
        penRight.setLayout(new BoxLayout(penRight, BoxLayout.Y_AXIS));
        penRight.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 2), // Outer border
                        BorderFactory.createEmptyBorder(10, 10, 10, 10) // Inner padding
                ),
                "Nhập thông tin nhân viên"
        ));
        penRight.setPreferredSize(new Dimension(300, 350)); // Height for the input panel

        // Create JLabel and JTextField for each input field
        jl1 = new JLabel("Mã nhân viên:");
        jl2 = new JLabel("Họ và tên:");
        jl3 = new JLabel("Tuổi:");
        jl4 = new JLabel("Ngày vào làm:");
        jl5 = new JLabel("Địa chỉ:");
        jl6 = new JLabel("Số điện thoại:");
        jl7 = new JLabel("Email:");

        // Compact JTextFields
        jtex1 = new JTextField(10); // Set preferred width
        jtex2 = new JTextField(10);
        jtex3 = new JTextField(10);
        jtex4 = new JTextField(10);
        jtex5 = new JTextField(10);
        jtex6 = new JTextField(10);
        jtex7 = new JTextField(10);

        // Create rows for input fields
        penRight.add(createRow(jl1, jtex1, jl2, jtex2));
        penRight.add(Box.createVerticalStrut(30));
        penRight.add(createRow(jl3, jtex3, jl4, jtex4));
        penRight.add(Box.createVerticalStrut(30));
        penRight.add(createRow(jl5, jtex5, jl6, jtex6));
        penRight.add(Box.createVerticalStrut(30));
        penRight.add(createRow(jl7, jtex7, jl1, jtex1));

 //       add(penRight, BorderLayout.CENTER); // Add input panel to center

        // Button Panel
        JPanel penLeft = new JPanel();
        penLeft.setLayout(new BoxLayout(penLeft, BoxLayout.Y_AXIS));
        btn_Them = new JButton("     Thêm     ");
        btn_Xoa = new JButton("      Xóa        ");
        btn_XoaTrang = new JButton("   Xóa trắng ");

        // Set button colors
        btn_Them.setBackground(new Color(70, 200, 85));
        btn_Xoa.setBackground(new Color(240, 100, 54));
        btn_XoaTrang.setBackground(new Color(255, 10, 45));

        penLeft.add(Box.createVerticalStrut(30));
        penLeft.add(btn_Them);
        penLeft.add(Box.createVerticalStrut(20));
        penLeft.add(btn_Xoa);
        penLeft.add(Box.createVerticalStrut(20));
        penLeft.add(btn_XoaTrang);
        penLeft.add(Box.createVerticalStrut(20));

 //       add(penLeft, BorderLayout.EAST); // Add button panel to east

        // Table Panel
        JPanel penTable = new JPanel();
        penTable.setLayout(new BorderLayout());
        penTable.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        penTable.setPreferredSize(new Dimension(300, 350)); // Increased height for the table panel (100px more)

        Object[] col = {"Mã NV","Tên nhân viên", "Chú thích","Chức vụ", "Địa chỉ","Email", "Ngày sinh", "Ngày vào làm", "Số điện thoại"};
        table_modal = new DefaultTableModel(col, 0);
        updateTableNhanVien();
        table = new JTable(table_modal);

        jcroll = new JScrollPane(table);
        jcroll.setPreferredSize(new Dimension(300, 300)); // Adjusted height for the scroll pane
        penTable.add(jcroll, BorderLayout.CENTER); // Add table to table panel

        // Change header colors
        table.getTableHeader().setBackground(new Color(50, 230, 220)); // Header background color
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

        add(penTable); // Add table panel to south
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

    private void updateTableNhanVien()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        table_modal.setRowCount(0);
        try {
            NhanVienService NVService = (NhanVienService) Naming.lookup("rmi://localhost:9090/nhanVienService");
            dsNV = NVService.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ nhân viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        // Thêm dữ liệu mới vào bảng thuốc
        for (NhanVien nv : dsNV) {
            Object[] rowData = {
                    nv.getMaNV(),
                    nv.getTenNV(),
                    nv.getChuThich(),
                    nv.getChucVu(),
                    nv.getDiaChi(),
                    nv.getEmail(),
                    nv.getNgaySinh(),
                    nv.getNgayVaoLam(),
                    nv.getSoDienThoai()
            };
            table_modal.addRow(rowData);
        }
    }
}
