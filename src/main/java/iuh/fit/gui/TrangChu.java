package iuh.fit.gui;

import javax.swing.*;
import java.awt.*;


public class TrangChu extends JPanel{
	public TrangChu() {
		// Set layout for the panel
        setLayout(new BorderLayout());

        // Create a label for the title
        JLabel lblTitle = new JLabel("\u2728 Trang Chủ \u2728", JLabel.CENTER);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 40));
        lblTitle.setForeground(new Color(0, 102, 204)); // Deep blue color
        lblTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0)); // Add padding around the title

        // Create a decorative background panel
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBackground(new Color(240, 248, 255)); // Alice blue background

        // Add a decorative banner
        JLabel banner = new JLabel();
        banner.setIcon(new ImageIcon("src/icons/anh-nen.jpg")); // Replace with your banner image path
        banner.setHorizontalAlignment(JLabel.CENTER);
        banner.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add a welcome message with icons
        JLabel lblWelcome = new JLabel("\u2728 Chào mừng đến với Hệ thống quản lý nhà thuốc KOSMEN \u2728", JLabel.CENTER);
        lblWelcome.setFont(new Font("SansSerif", Font.ITALIC, 20));
        lblWelcome.setForeground(new Color(60, 60, 60));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Display Employee Info
        JPanel employeeInfoPanel = new JPanel();
        employeeInfoPanel.setLayout(new GridLayout(3, 1)); // Display name, position, and email in separate rows
        employeeInfoPanel.setBackground(new Color(255, 255, 255));
        employeeInfoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Use Form_DangNhap.NV to access the employee's information
        JLabel lblEmployeeName = new JLabel("Xin chào, " +  "!", JLabel.CENTER);
        lblEmployeeName.setFont(new Font("Arial", Font.BOLD, 18));
        lblEmployeeName.setForeground(new Color(0, 102, 204));
        
        JLabel lblEmployeePosition = new JLabel("Vị trí: " , JLabel.CENTER);
        lblEmployeePosition.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEmployeePosition.setForeground(new Color(80, 80, 80));

        JLabel lblEmployeeEmail = new JLabel("Email: ", JLabel.CENTER);
        lblEmployeeEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEmployeeEmail.setForeground(new Color(80, 80, 80));

        // Add employee info to the panel
        employeeInfoPanel.add(lblEmployeeName);
        employeeInfoPanel.add(lblEmployeePosition);
        employeeInfoPanel.add(lblEmployeeEmail);

        // Add a footer section
        JLabel footer = new JLabel("\u00A9 2024 KOSMEN Pharmacy Management System", JLabel.CENTER);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        footer.setForeground(new Color(100, 100, 100));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Add components to the background panel
        backgroundPanel.add(banner, BorderLayout.NORTH);
        backgroundPanel.add(lblWelcome, BorderLayout.CENTER);
        backgroundPanel.add(employeeInfoPanel, BorderLayout.SOUTH);

        // Add components to the main panel
        add(lblTitle, BorderLayout.NORTH);
        add(backgroundPanel, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
	}
}
