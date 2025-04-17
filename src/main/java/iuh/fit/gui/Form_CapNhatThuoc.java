package iuh.fit.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class Form_CapNhatThuoc extends JPanel implements ActionListener {
    private JTextField txtMaThuoc, txtTenThuoc, txtDonGia, txtSoLuongTon, txtDanhMuc, txtLo, txtNhaSanXuat;
    private JTextArea txtMoTa;
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton btnCapNhat, btnHuy;
    private JComboBox cbbDanhMuc;
    private JComboBox cbbLo;
    private JComboBox cbbNSX;

    public Form_CapNhatThuoc() throws ParseException {
        // Giao diện chung
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Cập Nhật Thông Tin Thuốc");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.BLUE);

        // Panel chứa các trường nhập liệu
        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBorder(new TitledBorder("Thông tin thuốc"));
        pnlInput.setBackground(new Color(240, 248, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Mã thuốc
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlInput.add(new JLabel("Mã Thuốc:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;  // Làm cho JTextField chiếm hết chiều rộng
        txtMaThuoc = new JTextField(15);
        txtMaThuoc.setFocusable(false);
        txtMaThuoc.setEditable(false);
        pnlInput.add(txtMaThuoc, gbc);

        // Tên thuốc
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnlInput.add(new JLabel("Tên Thuốc:"), gbc);

        gbc.gridx = 1;
        txtTenThuoc = new JTextField(15);
        pnlInput.add(txtTenThuoc, gbc);

        // Đơn giá
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnlInput.add(new JLabel("Đơn Giá:"), gbc);

        gbc.gridx = 1;
        txtDonGia = new JTextField(15);
        pnlInput.add(txtDonGia, gbc);

        // Số lượng tồn
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnlInput.add(new JLabel("Số Lượng Tồn:"), gbc);

        gbc.gridx = 1;
        txtSoLuongTon = new JTextField(15);
        pnlInput.add(txtSoLuongTon, gbc);

        // Danh mục
        gbc.gridx = 0;
        gbc.gridy = 4;
        pnlInput.add(new JLabel("Danh Mục:"), gbc);

        gbc.gridx = 1;
        cbbDanhMuc = new JComboBox();
        pnlInput.add(cbbDanhMuc, gbc);
        updateDanhMuc();
        // Lô
        gbc.gridx = 0;
        gbc.gridy = 5;
        pnlInput.add(new JLabel("Lô:"), gbc);

        gbc.gridx = 1;
        txtLo = new JTextField(15);
        cbbLo=new JComboBox();
        
        pnlInput.add(cbbLo, gbc);
       updateLo();
        // Nhà sản xuất
        gbc.gridx = 0;
        gbc.gridy = 6;
        pnlInput.add(new JLabel("Nhà Sản Xuất:"), gbc);

        gbc.gridx = 1;
        txtNhaSanXuat = new JTextField(15);
        cbbNSX = new JComboBox();
        pnlInput.add(cbbNSX, gbc);
        updateCBBNSX();
        // Mô tả
        gbc.gridx = 0;
        gbc.gridy = 7;
        pnlInput.add(new JLabel("Mô Tả:"), gbc);

        gbc.gridx = 1;
        gbc.weighty = 1.0;  // Làm cho JTextArea chiếm hết không gian chiều dọc còn lại
        txtMoTa = new JTextArea(5, 15);
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        txtMoTa.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(txtMoTa);
        pnlInput.add(scrollPane, gbc);

        // Buttons Panel
        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlButton.setBackground(new Color(240, 248, 255));

        btnCapNhat = new JButton("Thêm");
        btnCapNhat.setPreferredSize(new Dimension(150, 30));
        btnCapNhat.setBackground(new Color(100, 149, 237));
        btnCapNhat.setForeground(Color.WHITE);
        pnlButton.add(btnCapNhat);

        btnHuy = new JButton("Hủy");
        btnHuy.setPreferredSize(new Dimension(150, 30));
        btnHuy.setBackground(new Color(255, 69, 0));
        btnHuy.setForeground(Color.WHITE);
        pnlButton.add(btnHuy);

        // Table for displaying data
        String[] columnNames = {"Mã Thuốc", "Tên Thuốc", "Đơn Giá", "Số Lượng Tồn", "Danh Mục", "Lô", "Nhà Sản Xuất", "Mô Tả"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(800, 200)); // Tăng chiều rộng của bảng
        updateTable();
        // Add components to layout
        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.setBackground(new Color(255, 250, 240));
        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        pnlNorth.add(pnlInput, BorderLayout.CENTER);
        pnlNorth.add(pnlButton, BorderLayout.SOUTH);
        add(pnlNorth, BorderLayout.NORTH);

        add(tableScrollPane, BorderLayout.CENTER);

        // Add button actions
        btnCapNhat.addActionListener(this);
        btnHuy.addActionListener(this);
    }

    private void updateDanhMuc() {

		
	}

	private void updateLo() throws ParseException {

		
	}

	private void updateCBBNSX() {

	}

	private void updateTable() {

	}

	@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnCapNhat) {

        } else if (source == btnHuy) {
            // Clear input fields
            clearInputFields();
        }
    }

    private void clearInputFields() {

    }
}
