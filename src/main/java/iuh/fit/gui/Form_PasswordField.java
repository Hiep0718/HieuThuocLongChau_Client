package iuh.fit.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Form_PasswordField extends JPasswordField {
    private Color underlineColor = Color.RED; // Màu viền dưới
    private boolean isHover = false; // Kiểm tra trạng thái hover

    public Form_PasswordField(int columns) {
        super(columns);
        setOpaque(false); // Để không có nền
        setBorder(BorderFactory.createEmptyBorder()); // Bỏ viền mặc định
        setBackground(new Color(255, 255, 255, 200)); // Màu nền trong suốt

        // Thiết lập màu chữ của placeholder
        setForeground(Color.GRAY);
        setEchoChar((char) 0); // Ẩn ký tự mật khẩu

        // Thêm sự kiện cho khi JPasswordField có hoặc mất focus
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.BLACK); // Đổi màu chữ khi có focus
                    setEchoChar('*'); // Hiển thị ký tự mật khẩu
                }
                isHover = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.GRAY); // Đổi lại màu chữ khi mất focus
                    setEchoChar((char) 0); // Ẩn ký tự mật khẩu
                }
                isHover = false;
                repaint();
            }
        });

        // Thêm sự kiện cho hover
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHover = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHover = false;
                repaint();
            }
        });

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, 30); // Thay đổi chiều cao tại đây
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Vẽ viền dưới khi hover hoặc có focus
        if (isHover || hasFocus()) {
            g.setColor(underlineColor);
            g.fillRect(0, getHeight() - 2, getWidth(), 2); // Vẽ viền dưới
        }
    }
}

