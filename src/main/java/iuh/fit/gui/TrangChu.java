package iuh.fit.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrangChu extends JPanel {
        private JLabel lblTime;
        private Timer timer;
        private JPanel statPanel;

        public TrangChu() {
                // Set layout for the main panel
                setLayout(new BorderLayout(15, 15));
                setBackground(new Color(245, 245, 250));

                // Add padding around the entire panel
                setBorder(new EmptyBorder(20, 20, 20, 20));

                // ===== TOP SECTION: Header with Logo and Time =====
                JPanel headerPanel = createHeaderPanel();
                add(headerPanel, BorderLayout.NORTH);

                // ===== CENTER SECTION: Main content with welcome banner and dashboard =====
                JPanel contentPanel = new JPanel(new BorderLayout(0, 20));
                contentPanel.setOpaque(false);

                // Welcome Banner
                JPanel welcomePanel = createWelcomePanel();
                contentPanel.add(welcomePanel, BorderLayout.NORTH);

                // Main Dashboard
                JPanel dashboardPanel = createDashboardPanel();
                contentPanel.add(dashboardPanel, BorderLayout.CENTER);

                add(contentPanel, BorderLayout.CENTER);

                // ===== BOTTOM SECTION: Footer with copyright info =====
                JPanel footerPanel = createFooterPanel();
                add(footerPanel, BorderLayout.SOUTH);

                // Start the clock timer
                startClock();
        }

        private JPanel createHeaderPanel() {
                JPanel headerPanel = new JPanel(new BorderLayout());
                headerPanel.setOpaque(false);

                // Logo and Title Section
                JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                logoPanel.setOpaque(false);

                // Logo (simulated with a colored panel)
                JPanel logoIcon = new JPanel();
                logoIcon.setPreferredSize(new Dimension(50, 50));
                logoIcon.setBackground(new Color(23, 162, 184));
                logoIcon.setBorder(new LineBorder(new Color(15, 140, 160), 2));

                // Application Title
                JLabel lblAppTitle = new JLabel("KOSMEN");
                lblAppTitle.setFont(new Font("Montserrat", Font.BOLD, 28));
                lblAppTitle.setForeground(new Color(35, 47, 62));
                lblAppTitle.setBorder(new EmptyBorder(0, 10, 0, 0));

                logoPanel.add(logoIcon);
                logoPanel.add(lblAppTitle);

                // Time and Date Panel
                JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                timePanel.setOpaque(false);

                lblTime = new JLabel();
                lblTime.setFont(new Font("Arial", Font.PLAIN, 16));
                lblTime.setForeground(new Color(80, 80, 80));

                timePanel.add(lblTime);

                headerPanel.add(logoPanel, BorderLayout.WEST);
                headerPanel.add(timePanel, BorderLayout.EAST);

                return headerPanel;
        }

        private JPanel createWelcomePanel() {
                JPanel welcomePanel = new JPanel(new BorderLayout());
                welcomePanel.setBackground(new Color(255, 255, 255));
                welcomePanel.setBorder(createPanelBorder());

                // Welcome message
                JPanel messagePanel = new JPanel(new BorderLayout());
                messagePanel.setBackground(new Color(35, 47, 62));
                messagePanel.setBorder(new EmptyBorder(25, 30, 25, 30));

                JLabel lblWelcome = new JLabel("Chào mừng đến với Hệ thống Quản lý Nhà thuốc KOSMEN");
                lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 22));
                lblWelcome.setForeground(Color.WHITE);

                // User information line
                JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                userInfoPanel.setOpaque(false);

                // Use Form_DangNhap.NV to access the employee's information instead of hardcoded values
                JLabel lblUserInfo = new JLabel("Nhân viên: Nguyễn Văn A | Chức vụ: Quản lý | Ngày làm việc: " + getCurrentDate());
                lblUserInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                lblUserInfo.setForeground(new Color(200, 200, 200));

                userInfoPanel.add(lblUserInfo);

                messagePanel.add(lblWelcome, BorderLayout.NORTH);
                messagePanel.add(userInfoPanel, BorderLayout.CENTER);

                welcomePanel.add(messagePanel, BorderLayout.CENTER);

                return welcomePanel;
        }

        private JPanel createDashboardPanel() {
                JPanel dashboard = new JPanel(new GridLayout(2, 2, 15, 15));
                dashboard.setOpaque(false);

                // Add 4 dashboard cards
                dashboard.add(createDashboardCard("Quản lý bán hàng", "icon-sales.png", new Color(23, 162, 184),
                        "Số đơn hôm nay: 24", "Doanh thu: 15.500.000 VNĐ"));

                dashboard.add(createDashboardCard("Quản lý kho", "icon-inventory.png", new Color(40, 167, 69),
                        "Tổng sản phẩm: 487", "Cần nhập thêm: 15 sản phẩm"));

                dashboard.add(createDashboardCard("Quản lý khách hàng", "icon-customers.png", new Color(255, 193, 7),
                        "Tổng khách hàng: 1,245", "Khách mới tháng này: 38"));

                dashboard.add(createDashboardCard("Báo cáo", "icon-reports.png", new Color(220, 53, 69),
                        "Báo cáo doanh thu tháng", "Báo cáo tồn kho"));

                return dashboard;
        }

        private JPanel createDashboardCard(String title, String iconFile, Color color, String stat1, String stat2) {
                JPanel card = new JPanel(new BorderLayout());
                card.setBackground(Color.WHITE);
                card.setBorder(createPanelBorder());

                // Card Header
                JPanel cardHeader = new JPanel(new BorderLayout());
                cardHeader.setBackground(color);
                cardHeader.setBorder(new EmptyBorder(15, 15, 15, 15));

                JLabel lblCardTitle = new JLabel(title);
                lblCardTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
                lblCardTitle.setForeground(Color.WHITE);

                // We'd normally load an actual icon, but we'll simulate with color for now
                JPanel iconSimulation = new JPanel();
                iconSimulation.setPreferredSize(new Dimension(32, 32));
                iconSimulation.setBackground(new Color(255, 255, 255, 50));

                cardHeader.add(lblCardTitle, BorderLayout.WEST);
                cardHeader.add(iconSimulation, BorderLayout.EAST);

                // Card Content
                JPanel cardContent = new JPanel(new GridLayout(2, 1, 0, 5));
                cardContent.setBackground(Color.WHITE);
                cardContent.setBorder(new EmptyBorder(15, 15, 15, 15));

                JLabel lblStat1 = new JLabel(stat1);
                lblStat1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

                JLabel lblStat2 = new JLabel(stat2);
                lblStat2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

                cardContent.add(lblStat1);
                cardContent.add(lblStat2);

                card.add(cardHeader, BorderLayout.NORTH);
                card.add(cardContent, BorderLayout.CENTER);

                // Make the card clickable (visual effect only)
                card.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseEntered(java.awt.event.MouseEvent evt) {
                                card.setCursor(new Cursor(Cursor.HAND_CURSOR));
                                card.setBorder(new CompoundBorder(
                                        new LineBorder(color, 2),
                                        new EmptyBorder(10, 10, 10, 10)
                                ));
                        }

                        public void mouseExited(java.awt.event.MouseEvent evt) {
                                card.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                card.setBorder(createPanelBorder());
                        }
                });

                return card;
        }

        private JPanel createFooterPanel() {
                JPanel footerPanel = new JPanel(new BorderLayout());
                footerPanel.setOpaque(false);
                footerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

                JLabel footer = new JLabel("© 2024 KOSMEN Pharmacy Management System - Phát triển bởi Nhóm X", JLabel.CENTER);
                footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                footer.setForeground(new Color(100, 100, 100));

                footerPanel.add(footer, BorderLayout.CENTER);

                return footerPanel;
        }

        private CompoundBorder createPanelBorder() {
                return new CompoundBorder(
                        new LineBorder(new Color(225, 225, 225), 1),
                        new EmptyBorder(12, 12, 12, 12)
                );
        }

        private String getCurrentDate() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(new Date());
        }

        private void updateTime() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss | dd/MM/yyyy");
                lblTime.setText(sdf.format(new Date()));
        }

        private void startClock() {
                updateTime(); // Update immediately

                // Update the time every second
                timer = new Timer(1000, e -> updateTime());
                timer.start();
        }

        // Clean up resources when the panel is no longer in use
        public void stopClock() {
                if (timer != null && timer.isRunning()) {
                        timer.stop();
                }
        }
}