package iuh.fit.test;

import iuh.fit.gui.Form_DangNhap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class menu_gd {
	public static void main(String[] args) {
		 try {
			// Tắt toàn bộ log của Log4j
		        Logger.getRootLogger().setLevel(Level.OFF);
		        Logger.getLogger("com.github.sarxos.webcam.Webcam").setLevel(Level.OFF);  // Tắt log của thư viện webcam (nếu có)
	            // Tải phông Open Sans từ file
	            Font openSans = Font.createFont(Font.TRUETYPE_FONT, new File("lib/OpenSans-Regular.ttf")).deriveFont(14f);
	            // Đặt phông chữ cho tất cả các thành phần Swing
	            UIManager.put("Label.font", openSans);
	            UIManager.put("Button.font", openSans);
	            UIManager.put("TextField.font", openSans);
	            UIManager.put("Table.font", openSans);

	        } catch (FontFormatException | IOException e) { 
	            e.printStackTrace();
	        } 
		 SwingUtilities.invokeLater(() -> { 
	            Form_DangNhap frame = new Form_DangNhap();
	            frame.setVisible(true);
	        });
    }
}
