package iuh.fit.test;

import iuh.fit.gui.Form_DangNhap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
             Form_DangNhap frame = null;
             try {
                 frame = new Form_DangNhap();
             } catch (MalformedURLException e) {
                 throw new RuntimeException(e);
             } catch (NotBoundException e) {
                 throw new RuntimeException(e);
             } catch (RemoteException e) {
                 throw new RuntimeException(e);
             }
             frame.setVisible(true);
	        });
    }
}
