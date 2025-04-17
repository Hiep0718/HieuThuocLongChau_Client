package iuh.fit.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_ADDKH extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private JLabel tenKH;
    private JLabel SDT;
    private JLabel email;
    private JLabel title;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JButton buttonLuu;
    private JButton buttonHuyBo;

    public Form_ADDKH() {
        setTitle("Thêm Khách Hàng");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tiêu đề
        JPanel jpTitle = new JPanel(new BorderLayout());
        jpTitle.setBackground(new Color(0, 153, 153));
        jpTitle.setPreferredSize(new Dimension(500, 50));
        title = new JLabel("Thêm Khách Hàng", SwingConstants.CENTER);
        title.setForeground(Color.white);
        jpTitle.add(title, BorderLayout.CENTER);

        // Form chính
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));

        // Họ tên
        JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        jp1.setPreferredSize(new Dimension(400, 60));
        tenKH = new JLabel("Tên Khách Hàng:");
        tenKH.setPreferredSize(new Dimension(150, 20));
        txtTenKH = new JTextField();
        txtTenKH.setPreferredSize(new Dimension(200, 40));
        jp1.add(tenKH);
        jp1.add(txtTenKH);

        // Số điện thoại
        JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        jp2.setPreferredSize(new Dimension(400, 60));
        SDT = new JLabel("Số điện thoại:");
        SDT.setPreferredSize(new Dimension(150, 20));
        txtSDT = new JTextField();
        txtSDT.setPreferredSize(new Dimension(200, 40));
        jp2.add(SDT);
        jp2.add(txtSDT);

        // Email
        JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        jp3.setPreferredSize(new Dimension(400, 60));
        email = new JLabel("Email:");
        email.setPreferredSize(new Dimension(150, 20));
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(200, 40));
        jp3.add(email);
        jp3.add(txtEmail);

        // Buttons
        JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 110, 20));
        jp4.setPreferredSize(new Dimension(400, 60));
        buttonHuyBo = new JButton("Hủy");
        buttonHuyBo.setPreferredSize(new Dimension(80, 30));
        buttonHuyBo.setBackground(Color.red);
        buttonHuyBo.addActionListener(this);

        buttonLuu = new JButton("Thêm");
        buttonLuu.setPreferredSize(new Dimension(80, 30));
        buttonLuu.setBackground(Color.green);
        buttonLuu.addActionListener(this);

        jp4.add(buttonHuyBo);
        jp4.add(buttonLuu);

        // Thêm tất cả vào form chính
        jp.add(jp1);
        jp.add(jp2);
        jp.add(jp3);
        jp.add(jp4);

        add(jpTitle, BorderLayout.NORTH);
        add(jp, BorderLayout.CENTER);
    }

}
