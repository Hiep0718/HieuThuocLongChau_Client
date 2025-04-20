package iuh.fit.gui;

import model.KhachHang;
import services.KhachHangService;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class Form_ADDKH extends JFrame implements ActionListener {
    private JLabel tenKH;
    private JLabel SDT;
    private JLabel email;
    private JLabel title;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JButton buttonLuu;
    private JButton buttonHuyBo;

    // Font và màu sắc chung
    private final Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
    private final Font textFieldFont = new Font("Segoe UI", Font.PLAIN, 13);
    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 18);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 13);

    private final Color primaryColor = new Color(41, 128, 185); // Màu chủ đạo
    private final Color accentColor = new Color(52, 152, 219); // Màu nhấn
    private final Color bgColor = new Color(240, 240, 240); // Màu nền
    private final Color textColor = new Color(52, 73, 94); // Màu chữ
    private final Color successColor = new Color(46, 204, 113); // Màu nút lưu
    private final Color dangerColor = new Color(231, 76, 60); // Màu nút hủy

    public Form_ADDKH() {
        setTitle("Quản Lý Khách Hàng");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(bgColor);

        // Panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 15));
        mainPanel.setBackground(bgColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Tiêu đề
        JPanel jpTitle = new JPanel();
        jpTitle.setLayout(new BorderLayout());
        jpTitle.setBackground(primaryColor);
        jpTitle.setPreferredSize(new Dimension(500, 60));

        title = new JLabel("THÊM KHÁCH HÀNG MỚI", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(titleFont);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        jpTitle.add(title, BorderLayout.CENTER);

        // Thêm hiệu ứng shadow cho tiêu đề
        jpTitle.setBorder(BorderFactory.createCompoundBorder(
                new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 50)),
                BorderFactory.createEmptyBorder(0, 0, 5, 0)
        ));

        // Form thông tin
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(bgColor);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        // Tên khách hàng
        JPanel jp1 = createFieldPanel();
        tenKH = createLabel("Tên Khách Hàng:");
        txtTenKH = createTextField();
        jp1.add(tenKH);
        jp1.add(txtTenKH);

        // Số điện thoại
        JPanel jp2 = createFieldPanel();
        SDT = createLabel("Số Điện Thoại:");
        txtSDT = createTextField();
        jp2.add(SDT);
        jp2.add(txtSDT);

        // Email
        JPanel jp3 = createFieldPanel();
        email = createLabel("Email:");
        txtEmail = createTextField();
        jp3.add(email);
        jp3.add(txtEmail);

        // Thêm các field vào form
        formPanel.add(jp1);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(jp2);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(jp3);
        formPanel.add(Box.createVerticalStrut(20));

        // Panel nút thao tác
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(bgColor);

        buttonHuyBo = createButton("Hủy", dangerColor, Color.WHITE);
        buttonLuu = createButton("Thêm", successColor, Color.WHITE);

        buttonPanel.add(buttonHuyBo);
        buttonPanel.add(buttonLuu);

        // Thêm vào form chính
        formPanel.add(buttonPanel);

        mainPanel.add(jpTitle, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createFieldPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBackground(bgColor);
        panel.setMaximumSize(new Dimension(440, 50));
        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(labelFont);
        label.setForeground(textColor);
        label.setPreferredSize(new Dimension(150, 25));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(textFieldFont);
        textField.setPreferredSize(new Dimension(220, 35));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));
        textField.setBackground(Color.WHITE);
        textField.setForeground(textColor);
        return textField;
    }

    private JButton createButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(buttonFont);
        button.setForeground(fgColor);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 35));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonHuyBo) {
            dispose();
        } else if (e.getSource() == buttonLuu) {
            // Xử lý lưu thông tin khách hàng
            try {
                KhachHangService khachHangService = (KhachHangService) Naming.lookup("rmi://localhost:9090/khachHangService");
                String tenKH = txtTenKH.getText().trim();
                String sdt = txtSDT.getText().trim();
                String email = txtEmail.getText().trim();
                if (tenKH.isEmpty() || sdt.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                };
                if (!sdt.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                    JOptionPane.showMessageDialog(this, "Email không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                KhachHang khachHang = new KhachHang();
                khachHang.setTenKH(tenKH);
                khachHang.setSoDienThoai(sdt);
                khachHang.setEmail(email);
                if (!khachHangService.save(khachHang)) {
                    JOptionPane.showMessageDialog(this, "Thêm khách hàng thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Đã thêm khách hàng thành công!",
                            "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            Form_ADDKH form = new Form_ADDKH();
            form.setVisible(true);
        });
    }
}