package iuh.fit.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Form_TextField extends JTextField {
    private Color underlineColor = Color.RED; // Màu viền dưới
    private boolean isHover = false; // Kiểm tra trạng thái hover
    private String placeholder; // Placeholder text

    public Form_TextField(String placeholder, int columns) {
        super(columns);
        this.placeholder = placeholder;
        setOpaque(false); // Để không có nền
        setBorder(BorderFactory.createEmptyBorder()); // Bỏ viền mặc định
        setBackground(new Color(255, 255, 255, 200)); // Màu nền trong suốt

        // Thiết lập placeholder
        setForeground(Color.GRAY); // Màu chữ của placeholder

        // Thêm sự kiện cho khi JTextField có hoặc mất focus
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals("Tên đăng nhập")) {
                    setForeground(Color.BLUE); // Đổi màu chữ khi có focus
                    setText("");
                }
                isHover = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.GRAY); // Đổi lại màu chữ khi mất focus
                    setText(placeholder);
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

        // Khởi tạo với placeholder
        setText(placeholder);
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


