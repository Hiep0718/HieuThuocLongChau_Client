package iuh.fit.gui;


import model.TaiKhoan;
import services.TaiKhoanService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Form_DangNhap extends JFrame {
    private JTextField txtTenDangNhap;
    private JPasswordField passwordField;
    private JButton btnDangNhap;
    private JButton btnHuy;
	private JLabel jmk;
	private Component jUS;
	

    public Form_DangNhap() throws MalformedURLException, NotBoundException, RemoteException {
        setTitle("Đăng Nhập");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo panel với ảnh nền
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Tạo một panel nền mờ
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        loginPanel.setBackground(new Color(255, 255, 255, 200)); // Màu trắng với độ mờ
 
        JLabel titleLabel = new JLabel("Đăng Nhập");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 34, 34));
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        jUS = new JLabel("Tên đăng nhập:");
        loginPanel.add(jUS, gbc);

        txtTenDangNhap = new Form_TextField("",13);
        gbc.gridx = 1;
        loginPanel.add(txtTenDangNhap, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        jmk = new JLabel("Mật khẩu:");
        jmk.setPreferredSize(jUS.getPreferredSize());
        loginPanel.add(jmk, gbc);

        passwordField = new Form_PasswordField(18);
        //passwordField.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 1;
        loginPanel.add(passwordField, gbc);

        btnHuy = new JButton("     Hủy     ");
        btnHuy.setBackground(new Color(193, 164, 162));
        btnHuy.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3; 
        loginPanel.add(btnHuy, gbc);
        
        btnDangNhap = new JButton("Đăng Nhập");
        btnDangNhap.setBackground(new Color(76, 175, 80));
        btnDangNhap.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPanel.add(btnDangNhap, gbc);

        

        
        // Thêm panel đăng nhập vào backgroundPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(loginPanel, gbc);

        // Thêm backgroundPanel vào JFrame
        add(backgroundPanel);
        TaiKhoanService taiKhoanService = (TaiKhoanService) Naming.lookup("rmi://localhost:9090/taiKhoanService");

        // Thêm sự kiện cho nút Đăng Nhập
        btnDangNhap.addActionListener(new ActionListener() {
        

			@Override
            public void actionPerformed(ActionEvent e) {
            	Object o = e.getSource();
        		boolean trangthai = true;
        		if(o.equals(btnDangNhap)) {
        			try {
        				String tenDN = txtTenDangNhap.getText().trim();
        				@SuppressWarnings("deprecation")
						String mk = passwordField.getText().trim();
                        List<TaiKhoan> list = taiKhoanService.getAll();
                        TaiKhoan tk = null;
                        for (TaiKhoan taiKhoan : list) {
                            if (taiKhoan.getTenTK().equals(tenDN)) {
                                tk = taiKhoan;
                                break;
                            }
                        }
                        // Kiểm tra thông tin đăng nhập
        				if(txtTenDangNhap.getText().trim().equals("") || passwordField.getText().trim().equals(""))
        				{
        					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
        					trangthai = false;
        				}
        				else if(tk != null)
        				{
                                if(tk.getMatKhau().equals(mk))
            					{
            						JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
            						trangthai = true;
            					}
            					else
            					{
            						JOptionPane.showMessageDialog(null, "Sai mật khẩu");
            						trangthai = false;
            					}
        				}
        				else
        				{
        					JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
        					trangthai = false;
        				}
        				if(trangthai == true) {
        					//MyMenuExample menu = new MyMenuExample();
        					if (trangthai == true) {
        						MyMenuExample menu = new MyMenuExample();
            			        SwingUtilities.invokeLater(new Runnable() {
            			            public void run() {
            			                menu.createAndShowGUI();
            			            }
            			        });
            			        dispose();
							}else {
								MyMenuExample menu = new MyMenuExample();
            			        SwingUtilities.invokeLater(new Runnable() {
            			            public void run() {
            			                menu.createAndShowGUIForNV();
            			            }
            			        });
							}
        					
        					
        					dispose();
        				}
        			} catch (Exception e2) {
        				// TODO: handle exception
//        				JOptionPane.showMessageDialog(this, "Login Failed");
        				e2.printStackTrace();
        			}
        		}}
        });

        // Thêm sự kiện cho nút Hủy
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtTenDangNhap.setText("");
                passwordField.setText("");
            }
        });
    }
    private class BackgroundPanel extends JPanel {
        private BufferedImage backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(new File("src/main/java/iuh/fit/icons/backrought.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            Form_DangNhap frame = new Form_DangNhap();
//            frame.setVisible(true);
//        });
//    }
}


