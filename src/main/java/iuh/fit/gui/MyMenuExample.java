package iuh.fit.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
 
public class MyMenuExample {
    private static JFrame frame;
    private static JPanel contentPanel;
    private static JPanel newPanel;
	private static JMenuItem nv1;
	private static JMenuItem nv2;
	private static JMenuItem nv3;
	private static JMenuItem nv4;
	private static JMenuItem thuoc1;
	private static JMenuItem thuoc3;
	private static JMenuItem thuoc2;
	private static JMenuItem thuoc4;
	private static JMenuItem kh1;
	private static JMenuItem kh2;
	private static JMenuItem kh3;
	private static JMenuItem kh4;
	private static JMenuItem nsx1;
	private static JMenuItem nsx2;
	private static JMenuItem nsx3;
	private static JMenuItem nsx4;
	private static JMenuItem doiTra;
	private static JMenuItem lapHD;
	private static JMenuItem cnPd;
	private static Form_DoiTra dt;
	private static Form_LapHoaDon lhd;
	private static Form_TimNhanVien tnv;
	private static Form_TimThuoc tt;
	private static Form_TimKhachHang tkh;
	private static Form_NhanVien nv;
	private static Form_KhachHang kh;
	private static Form_Thuoc thuoc;
	private static Form_NhaSanXuat nsx;
	private static TrangChu trangChu;
	
	
	private static JMenuItem tkThang;
	private static JMenuItem tkNgay;
	
	private static JMenuItem doanhThu;
	private static JMenuItem TKthuoc;
	private static BufferedImage image;
	
	private static JMenuItem taiKhoan;
	private static JMenuItem datThuoc;
	
	private static Form_PhieuDat gd_DatThuoc;
	private static JMenuItem tkThuoc;
	private static ThongKeTheoNgay tkDay;
	
	private static ThongKeTheoThang tkMonth;
	private static form_ThongKeThuoc tkThuocPn;
	private static Form_TimNhaSanXuat timNSX;
	private static Form_CapNhatThuoc update_Thuoc;
	private static Form_CapNhatKhachHang update_KhachHang;
	private static Form_CapNhatNhanVien update_NhanVien;
	private static Form_CapNhatNhaSanXuat update_NhaSanXuat;
	private static Form_CapNhatTaiKhoan update_TaiKhoan;
	private static Form_CapNhatPhieuDat update_PhieuDat;
	
    public static void createAndShowGUI() {
        // Tạo frame
        frame = new JFrame("Chương trình quản lý nhà thuốc KOSMEN");
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Lấy kích thước màn hình
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        // Tính toán vị trí top-center
        int x = (screenSize.width - frame.getWidth()) / 2; // Trung tâm theo chiều ngang
        int y = 0; // Vị trí ở top (trên cùng của màn hình)

        // Đặt vị trí cửa sổ
        frame.setLocation(x, y);

        // Tạo menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
        menuBar.setBackground(Color.white);
        menuBar.setPreferredSize(new Dimension(frame.getWidth(), 50));

        // Tạo menu với biểu tượng
        JMenu trangChu = createMenuWithIcon("Trang chủ", "src/main/java/iuh/fit/icons/home.png");
        JMenu danhMuc = createMenuWithIcon("Danh mục", "src/main/java/iuh/fit/icons/list.png");
        JMenu timKiem = createMenuWithIcon("Tìm kiếm", "src/main/java/iuh/fit/icons/timkiem.png");
        JMenu capNhat = createMenuWithIcon("Cập nhật", "src/main/java/iuh/fit/icons/update.png");
        JMenu xuLy = createMenuWithIcon("Xử lý", "src/main/java/iuh/fit/icons/xuly.png");
        JMenu thongKe = createMenuWithIcon("Thống kê", "src/main/java/iuh/fit/icons/thongke.png");
        JMenu logo = createMenuWithIcon("", "src/main/java/iuh/fit/icons/logo.jpg");


        // Set kích thước từng JMenu
        trangChu.setPreferredSize(new Dimension(120, 70));
        danhMuc.setPreferredSize(new Dimension(120, 70));
        timKiem.setPreferredSize(new Dimension(120, 70));
        capNhat.setPreferredSize(new Dimension(120, 70));
        xuLy.setPreferredSize(new Dimension(100, 70));
        thongKe.setPreferredSize(new Dimension(120, 70));        
        logo.setPreferredSize(new Dimension(210, 100));
        logo.setBackground(Color.black);
        logo.setIcon(new ImageIcon(image.getScaledInstance(200, 70, Image.SCALE_SMOOTH)));
        
        //Tạo menuItem
        nv1 = new JMenuItem("Nhân viên");
        nv2 = new JMenuItem("Nhân viên");
        nv3 = new JMenuItem("Nhân viên");
        nv1.setPreferredSize(new Dimension(110, 30));
        nv2.setPreferredSize(new Dimension(110, 30));
        nv3.setPreferredSize(new Dimension(110, 30));
        
        thuoc1 = new JMenuItem("Thuốc");
        thuoc2 = new JMenuItem("Thuốc");
        thuoc3 = new JMenuItem("Thuốc");
        thuoc1.setPreferredSize(new Dimension(110, 30));
        thuoc2.setPreferredSize(new Dimension(110, 30));
        thuoc3.setPreferredSize(new Dimension(110, 30));
        
        kh1 = new JMenuItem("Khách hàng");
        kh2 = new JMenuItem("Khách hàng");
        kh3 = new JMenuItem("Khách hàng");
        kh1.setPreferredSize(new Dimension(110, 30));
        kh2.setPreferredSize(new Dimension(110, 30));
        kh3.setPreferredSize(new Dimension(110, 30));
        
        nsx1 = new JMenuItem("Nhà sản xuất");
        nsx2 = new JMenuItem("Nhà sản xuất");
        nsx3 = new JMenuItem("Nhà sản xuất");
        nsx1.setPreferredSize(new Dimension(110, 30));
        nsx2.setPreferredSize(new Dimension(110, 30));
        nsx3.setPreferredSize(new Dimension(110, 30));
        
        doiTra = new JMenuItem("Đổi/Trả");
        doiTra.setPreferredSize(new Dimension(110, 30));
        lapHD = new JMenuItem("Lập hóa đơn");
        lapHD.setPreferredSize(new Dimension(110, 30));
        
        tkThang = new JMenuItem("Thống kê tháng");
        tkNgay = new JMenuItem("Thống kê ngày");
        
        tkThuoc = new JMenuItem("Thống kê thuốc");
        
        taiKhoan = new JMenuItem("Tài khoản");
        taiKhoan.setPreferredSize(new Dimension(110, 30));
        
        doanhThu = new JMenu("Doanh thu");
        doanhThu.setPreferredSize(new Dimension(110, 30));
        
        TKthuoc = new JMenu("Thuốc");
        TKthuoc.setPreferredSize(new Dimension(110, 30));
        
        datThuoc = new JMenuItem("Đặt thuốc");
        datThuoc.setPreferredSize(new Dimension(110, 30));
        
        cnPd=new JMenuItem("Phiếu đặt");
        cnPd.setPreferredSize(new Dimension(110, 30));

        
        doanhThu.add(tkNgay);
        doanhThu.add(tkThang);
       
        TKthuoc.add(tkThuoc);

        // Thêm các mục vào menu
        danhMuc.add(nv1);
        danhMuc.add(thuoc1);
        danhMuc.add(kh1);
        danhMuc.add(nsx1);

        timKiem.add(nv2);
        timKiem.add(thuoc2);
        timKiem.add(kh2);
        timKiem.add(nsx2);

        capNhat.add(nv3);
        capNhat.add(thuoc3);
        capNhat.add(kh3);
        capNhat.add(nsx3);
        capNhat.add(taiKhoan);
        capNhat.add(cnPd);
        
        
        xuLy.add(doiTra);
        xuLy.add(lapHD);
        xuLy.add(datThuoc);
        
//        thongKe.add(tkNgay);
//        thongKe.add(tkThang);
//        thongKe.add(tkNam);
        thongKe.add(doanhThu);
        thongKe.add(tkThuoc);
        


        // Thêm menu vào menu bar
        menuBar.add(trangChu);
        menuBar.add(danhMuc);
        menuBar.add(xuLy);
        menuBar.add(capNhat);
        menuBar.add(timKiem);
        menuBar.add(thongKe);
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(logo);
        //Thêm sự kiện cho menuItem
        doiTra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayDoiTra();
			}
		});
        
        lapHD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayLapHoaDon();
			}
		});
        
        //form-timnhanvien
        nv2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTimNhanVien();
				
			}
		});
        
        //tim thuoc
        thuoc2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTimThuoc();
			}
		});
        
        //tim khach hang
        kh2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTimKhachHang();
			}
		});
        
        //Tim nsx
        nsx2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timNSX();
			}
		});
        
        //danh mục nhân viên
        nv1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayNhanVien();
			}
		});
        
        kh1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayKhachHang();
			}
		});
        
        thuoc1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayThuoc();
			}
		});
        
        nsx1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayNhaSanXuat();
			}
		});
        
        tkThang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTKThang();
			}
		});
        
        tkNgay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTKNgay();
			}
		});
        
        
		
        datThuoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayDatThuoc();
			}
		});
			
        tkThuoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTKThuoc();;
			}
		});
        
        kh3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayCapNhatKhachHang();
				
			}
		});
        
       thuoc3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayCapNhatThuoc();;
				
			}
		});
       
       taiKhoan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayCapNhatTaiKhoan();
			}
		});
       
       cnPd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayCapNhatPhieuDat();
			}

			
		});
       
       nsx3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayCapNhatNhaSanXuat();
			}
		});
       
       nv3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayCapNhatNhanVien();
			}
		});
       
       trangChu.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			displayTrangchu();
		}
       });
        // Đặt menu bar vào frame
        frame.setJMenuBar(menuBar);

        // Tạo panel chứa nội dung
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Mặc định hiển thị giao diện cho mục "New"
        newPanel = new JPanel();
        contentPanel.add(newPanel, BorderLayout.CENTER);

        // Đặt panel chứa nội dung vào frame
        frame.getContentPane().add(contentPanel);

        // Hiển thị frame
        frame.setVisible(true);
        displayTrangchu();
    }
    public static void createAndShowGUIForNV() {
        // Tạo frame
        frame = new JFrame("Chương trình quản lý nhà thuốc KOSMEN");
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Lấy kích thước màn hình
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        // Tính toán vị trí top-center
        int x = (screenSize.width - frame.getWidth()) / 2; // Trung tâm theo chiều ngang
        int y = 0; // Vị trí ở top (trên cùng của màn hình)

        // Đặt vị trí cửa sổ
        frame.setLocation(x, y);

        // Tạo menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
        menuBar.setBackground(Color.white);
        menuBar.setPreferredSize(new Dimension(frame.getWidth(), 50));

        // Tạo menu với biểu tượng
        JMenu trangChu = createMenuWithIcon("Trang chủ", "src/icons/home.png");
        JMenu danhMuc = createMenuWithIcon("Danh mục", "src/icons/list.png");
        JMenu timKiem = createMenuWithIcon("Tìm kiếm", "src/icons/timkiem.png");
        JMenu capNhat = createMenuWithIcon("Cập nhật", "src/icons/update.png");
        JMenu xuLy = createMenuWithIcon("Xử lý", "src/icons/xuly.png");
        JMenu thongKe = createMenuWithIcon("Thống kê", "src/icons/thongke.png");
        JMenu logo = createMenuWithIcon("", "src/icons/logo.jpg");


        // Set kích thước từng JMenu
        trangChu.setPreferredSize(new Dimension(120, 70));
        danhMuc.setPreferredSize(new Dimension(120, 70));
        timKiem.setPreferredSize(new Dimension(120, 70));
        capNhat.setPreferredSize(new Dimension(120, 70));
        xuLy.setPreferredSize(new Dimension(100, 70));
        thongKe.setPreferredSize(new Dimension(120, 70));        
        logo.setPreferredSize(new Dimension(210, 100));
        logo.setBackground(Color.black);
        logo.setIcon(new ImageIcon(image.getScaledInstance(200, 70, Image.SCALE_SMOOTH)));
        
        //Tạo menuItem
        nv1 = new JMenuItem("Nhân viên");
        nv2 = new JMenuItem("Nhân viên");
        nv3 = new JMenuItem("Nhân viên");
        nv1.setPreferredSize(new Dimension(110, 30));
        nv2.setPreferredSize(new Dimension(110, 30));
        nv3.setPreferredSize(new Dimension(110, 30));
        
        thuoc1 = new JMenuItem("Thuốc");
        thuoc2 = new JMenuItem("Thuốc");
        thuoc3 = new JMenuItem("Thuốc");
        thuoc1.setPreferredSize(new Dimension(110, 30));
        thuoc2.setPreferredSize(new Dimension(110, 30));
        thuoc3.setPreferredSize(new Dimension(110, 30));
        
        kh1 = new JMenuItem("Khách hàng");
        kh2 = new JMenuItem("Khách hàng");
        kh3 = new JMenuItem("Khách hàng");
        kh1.setPreferredSize(new Dimension(110, 30));
        kh2.setPreferredSize(new Dimension(110, 30));
        kh3.setPreferredSize(new Dimension(110, 30));
        
        nsx1 = new JMenuItem("Nhà sản xuất");
        nsx2 = new JMenuItem("Nhà sản xuất");
        nsx3 = new JMenuItem("Nhà sản xuất");
        nsx1.setPreferredSize(new Dimension(110, 30));
        nsx2.setPreferredSize(new Dimension(110, 30));
        nsx3.setPreferredSize(new Dimension(110, 30));
        
        doiTra = new JMenuItem("Đổi/Trả");
        doiTra.setPreferredSize(new Dimension(110, 30));
        lapHD = new JMenuItem("Lập hóa đơn");
        lapHD.setPreferredSize(new Dimension(110, 30));
        
        tkThang = new JMenuItem("Thống kê tháng");
        tkNgay = new JMenuItem("Thống kê ngày");
        
        tkThuoc = new JMenuItem("Thống kê thuốc");
        
        taiKhoan = new JMenuItem("Tài khoản");
        taiKhoan.setPreferredSize(new Dimension(110, 30));
        
        doanhThu = new JMenu("Doanh thu");
        doanhThu.setPreferredSize(new Dimension(110, 30));
        
        TKthuoc = new JMenu("Thuốc");
        TKthuoc.setPreferredSize(new Dimension(110, 30));
        
        datThuoc = new JMenuItem("Đặt thuốc");
        datThuoc.setPreferredSize(new Dimension(110, 30));
        
        cnPd=new JMenuItem("Phiếu đặt");
        cnPd.setPreferredSize(new Dimension(110, 30));

        
        doanhThu.add(tkNgay);
        doanhThu.add(tkThang);
       
        TKthuoc.add(tkThuoc);

        // Thêm các mục vào menu
        danhMuc.add(nv1);
        danhMuc.add(thuoc1);
        danhMuc.add(kh1);
        danhMuc.add(nsx1);

        timKiem.add(nv2);
        timKiem.add(thuoc2);
        timKiem.add(kh2);
        timKiem.add(nsx2);

        capNhat.add(nv3);
        capNhat.add(thuoc3);
        capNhat.add(kh3);
        capNhat.add(nsx3);
        capNhat.add(taiKhoan);
        capNhat.add(cnPd);
        capNhat.setEnabled(false);
        
        
        
        xuLy.add(doiTra);
        xuLy.add(lapHD);
        xuLy.add(datThuoc);
        
//        thongKe.add(tkNgay);
//        thongKe.add(tkThang);
//        thongKe.add(tkNam);
        thongKe.add(doanhThu);
        thongKe.add(tkThuoc);
        


        // Thêm menu vào menu bar
        menuBar.add(trangChu);
        menuBar.add(danhMuc);
        menuBar.add(xuLy);
        menuBar.add(capNhat);
        menuBar.add(timKiem);
        menuBar.add(thongKe);
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(logo);
        //Thêm sự kiện cho menuItem
        doiTra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayDoiTra();
			}
		});
        
        lapHD.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayLapHoaDon();
			}
		});
        
        //form-timnhanvien
        nv2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTimNhanVien();
				
			}
		});
        
        //tim thuoc
        thuoc2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTimThuoc();
			}
		});
        
        //tim khach hang
        kh2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTimKhachHang();
			}
		});
        
        //Tim nsx
        nsx2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timNSX();
			}
		});
        
        //danh mục nhân viên
        nv1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayNhanVien();
			}
		});
        
        kh1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayKhachHang();
			}
		});
        
        thuoc1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayThuoc();
			}
		});
        
        nsx1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayNhaSanXuat();
			}
		});
        
        tkThang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTKThang();
			}
		});
        
        tkNgay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTKNgay();
			}
		});
        
        
		
        datThuoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayDatThuoc();
			}
		});
			
        tkThuoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayTKThuoc();;
			}
		});
        
        kh3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayCapNhatKhachHang();
				
			}
		});
        
       thuoc3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayCapNhatThuoc();;
				
			}
		});
       
       taiKhoan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayCapNhatTaiKhoan();
			}
		});
       
       cnPd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayCapNhatPhieuDat();
			}

			
		});
       
       nsx3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayCapNhatNhaSanXuat();
			}
		});
       
       nv3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				displayCapNhatNhanVien();
			}
		});
       
       trangChu.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			displayTrangchu();
		}
       });
        // Đặt menu bar vào frame
        frame.setJMenuBar(menuBar);

        // Tạo panel chứa nội dung
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Mặc định hiển thị giao diện cho mục "New"
        newPanel = new JPanel();
        contentPanel.add(newPanel, BorderLayout.CENTER);

        // Đặt panel chứa nội dung vào frame
        frame.getContentPane().add(contentPanel);

        // Hiển thị frame
        frame.setVisible(true);
        displayTrangchu();
    }

    // Tạo JMenu với biểu tượng
    private static JMenu createMenuWithIcon(String title, String iconPath) {
        JMenu menu = new JMenu(title);
        menu.setForeground(Color.blue);
        menu.setFont(new Font("", Font.BOLD, 16));

        // Đọc tệp hình ảnh và đặt biểu tượng cho JMenu
        try {	
            image = ImageIO.read(new File(iconPath));
            // Tạo ImagePanel để sử dụng Graphics2D
            ImagePanel panel = new ImagePanel(image);
            menu.setIcon(new ImageIcon(image.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return menu;
    }
    private static void displayTrangchu() {
    	
    	contentPanel.removeAll();
    	trangChu = new TrangChu();
    	contentPanel.add(trangChu, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    // Hiển thị giao diện cho mục "Đổi trả"
    private static void displayDoiTra() {
    	contentPanel.removeAll();
    	dt = new Form_DoiTra();
    	contentPanel.add(dt, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayLapHoaDon() {
    	contentPanel.removeAll();
    	lhd = new Form_LapHoaDon();
    	contentPanel.add(lhd, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void timNSX() {
    	contentPanel.removeAll();
    	timNSX = new Form_TimNhaSanXuat();
    	contentPanel.add(timNSX, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayTimNhanVien() {
    	contentPanel.removeAll();
    	tnv = new Form_TimNhanVien();
    	contentPanel.add(tnv, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
	}
    
    private static void displayTimThuoc() {
    	contentPanel.removeAll();
    	tt = new Form_TimThuoc();
    	contentPanel.add(tt, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayTimKhachHang() {
    	contentPanel.removeAll();
    	tkh = new Form_TimKhachHang();
    	contentPanel.add(tkh, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayCapNhatThuoc() {
    	contentPanel.removeAll();
    	try {
			update_Thuoc = new Form_CapNhatThuoc();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	contentPanel.add(update_Thuoc, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayCapNhatKhachHang() {
    	contentPanel.removeAll();
    	update_KhachHang = new Form_CapNhatKhachHang();
    	contentPanel.add(update_KhachHang, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayNhanVien() {
    	contentPanel.removeAll();
    	nv = new Form_NhanVien();
    	contentPanel.add(nv, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayKhachHang() {
    	contentPanel.removeAll();
    	kh = new Form_KhachHang();
    	contentPanel.add(kh, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayThuoc() {
    	contentPanel.removeAll();
    	thuoc = new Form_Thuoc();
    	contentPanel.add(thuoc, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayNhaSanXuat() {
    	contentPanel.removeAll();
    	nsx = new Form_NhaSanXuat();
    	contentPanel.add(nsx, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayTKThang() {
    	contentPanel.removeAll();
    	tkMonth = new ThongKeTheoThang();
    	contentPanel.add(tkMonth, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayTKNgay() {
    	contentPanel.removeAll();
    	tkDay = new ThongKeTheoNgay();
    	contentPanel.add(tkDay, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
  
    
    private static void displayTKThuoc() {
    	contentPanel.removeAll();
    	tkThuocPn = new form_ThongKeThuoc();
    	contentPanel.add(tkThuocPn, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayDatThuoc() {
    	contentPanel.removeAll();
    	gd_DatThuoc = new Form_PhieuDat();
    	contentPanel.add(gd_DatThuoc, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayCapNhatNhanVien() {
    	contentPanel.removeAll();
    	update_NhanVien = new Form_CapNhatNhanVien();
    	contentPanel.add(update_NhanVien, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    private static void displayCapNhatPhieuDat() {
    	contentPanel.removeAll();
    	update_PhieuDat = new Form_CapNhatPhieuDat();
    	contentPanel.add(update_PhieuDat, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
	}
    private static void displayCapNhatNhaSanXuat() {
    	contentPanel.removeAll();
    	update_NhaSanXuat = new Form_CapNhatNhaSanXuat();
    	contentPanel.add(update_NhaSanXuat, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    private static void displayCapNhatTaiKhoan() {
    	contentPanel.removeAll();
    	update_TaiKhoan = new Form_CapNhatTaiKhoan();
    	contentPanel.add(update_TaiKhoan, BorderLayout.CENTER);//hiển thị lên menu 
    	contentPanel.revalidate(); // Cập nhật giao diện
        contentPanel.repaint(); // Vẽ lại panel
    }
    
    // Lớp ImagePanel để sử dụng Graphics2D
    public static class ImagePanel extends JPanel {
        private BufferedImage image;

        public ImagePanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Chuyển Graphics thành Graphics2D
            Graphics2D g2d = (Graphics2D) g;

            // Thiết lập RenderingHints để cải thiện chất lượng hiển thị
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            // Vẽ ảnh với kích thước của JPanel
            if (image != null) {
                g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    


//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//                displayTrangchu();
//            }
//        });
//    }
}
