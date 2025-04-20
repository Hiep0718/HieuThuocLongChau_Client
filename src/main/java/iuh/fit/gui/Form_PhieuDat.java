package iuh.fit.gui;


import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.*;
import services.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Form_PhieuDat extends JPanel implements MouseListener,ActionListener{
	private JPanel jPThongTinThuoc;
	private JPanel jPTieuDeThongTinThuoc;
	private JPanel jPNoiDungThongTinThuoc;
	private JPanel jPNoiDungThongTinThuoc1;
	private JPanel jPNoiDungThongTinThuoc2;
	private JPanel jPNoiDungThongTinThuoc3;
	private JPanel jPNoiDungThongTinThuoc4;
	private JPanel jPNoiDungThongTinThuoc5;
	private JPanel jPNoiDungPhieuDat;
	private JPanel jPNoiDungPhieuDat1;
	private JPanel jPNoiDungPhieuDat2;
	private JPanel jPNoiDungPhieuDat3;
	private JPanel jPNoiDungPhieuDat4;
	private JPanel jPNoiDungPhieuDat5;
	private JPanel jPChucNang;
	private JPanel jPChucNang1;
	private JPanel jPChucNang2;
	private JPanel jPNorthmain;
	private JPanel jPGioHang;
	private JPanel jPTieuDeGioHang;
	private JPanel JPNoiDungGioHang;
	private JPanel JPCen;
	private JPanel jPwest;
	private Box box;
	private Box box1;
	private Box box2;
	private JLabel lblThonTinThuoc;
	private JLabel lblMaThuoc;
	private JLabel lblTenThuoc;
	private JLabel lblThanhPhan;
	private JLabel lblDonGia;
	private JLabel lblGioHang;
	private JLabel lblDonVi;
	private JLabel lblMaPhieuDat;
	private JLabel lblSoDienThoai;
	private JLabel lblTenKhachHang;
	private JLabel lblTongTien;
	private JTextField txtMaThuoc;
	private JTextField txtTenThuoc;
	private JTextField txtThanhPhan;
	private JTextField txtDongia;
	private JTextField txtDonVi;
	private JTextField txtTimKiem;
	private JTextField txtSoLuong;
	private JTextField txtmaPhieuDat;
	public static JTextField txtSDT;
	private JTextField txtTenKH;
	private JTextField txtTongTien;
	private JButton buttonTimKiem;
	private JButton buttonThem;
	private JButton buttonHuyBo;
	private JButton buttonLuu;
	private JButton btnAdd;
	private JButton btnLook;
	private JButton  btndelete;
	private DefaultTableModel modelThongTinThuoc;
	private JTable tableThongTinThuoc;
	private DefaultTableModel modelGioHang;
	private JTable tableGioHang;
	private List<Thuoc> dsThuoc;
	private AutoSuggestTextField cmbSDT;
	private KhachHang khachhang;

	public  Form_PhieuDat(){
		initComBoNent();
	}
   public void initComBoNent() {
	   jPThongTinThuoc = new JPanel();
       jPTieuDeThongTinThuoc = new JPanel();
       jPNoiDungThongTinThuoc = new JPanel();
       jPNoiDungThongTinThuoc1 = new JPanel();
       jPNoiDungThongTinThuoc2 = new JPanel();
       jPNoiDungThongTinThuoc3 = new JPanel();
       jPNoiDungThongTinThuoc4 = new JPanel();
       jPNoiDungThongTinThuoc5 = new JPanel();
       jPGioHang=new JPanel();
	   jPNorthmain=new JPanel(); 
	   jPTieuDeGioHang = new JPanel(); 
	   JPNoiDungGioHang= new JPanel(); 
	   box=Box.createHorizontalBox();
	   box1=Box.createVerticalBox();
	   box2=Box.createVerticalBox();
			   
	 
	 jPThongTinThuoc.setLayout(new BorderLayout());
	
	 jPThongTinThuoc.setPreferredSize(new Dimension(550,300));
	 jPThongTinThuoc.setBackground(Color.white);
	 
	 
	 jPTieuDeThongTinThuoc.setLayout(new BorderLayout());
	 jPTieuDeThongTinThuoc.setBackground(new Color(0, 153, 153));
	 jPTieuDeThongTinThuoc.setPreferredSize(new Dimension(550,25));
	 
	 lblThonTinThuoc=new JLabel("Thông Tin Thuốc");
	 lblThonTinThuoc.setHorizontalAlignment(SwingConstants.CENTER);
	 lblThonTinThuoc.setForeground(Color.white);
	 
	 jPTieuDeThongTinThuoc.add(lblThonTinThuoc);
	 jPThongTinThuoc.add(jPTieuDeThongTinThuoc,BorderLayout.NORTH);
	 
	 //390
     JPanel jPAnhThuoc=new JPanel();
     jPAnhThuoc.setPreferredSize(new Dimension(220,360));
     jPAnhThuoc.setBackground(Color.white);
	 
     JPanel jPNDAnhThuoc=new JPanel();
     jPNDAnhThuoc.setPreferredSize(new Dimension(550,360));
     jPNDAnhThuoc.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
     
	 jPNoiDungThongTinThuoc.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
	 jPNoiDungThongTinThuoc.setPreferredSize(new Dimension(320,360));
	 jPNoiDungThongTinThuoc.setBackground(Color.white);
	
	 jPNoiDungThongTinThuoc1.setLayout(new FlowLayout(FlowLayout.LEFT,7,0));
	 jPNoiDungThongTinThuoc1.setBackground(Color.white);

	 jPNoiDungThongTinThuoc1.setPreferredSize(new Dimension(350,45));
	 jPNoiDungThongTinThuoc2.setLayout(new FlowLayout(FlowLayout.LEFT,7,0));
	 jPNoiDungThongTinThuoc2.setPreferredSize(new Dimension(350,45));
	 jPNoiDungThongTinThuoc2.setBackground(Color.white);
	 jPNoiDungThongTinThuoc3.setLayout(new FlowLayout(FlowLayout.LEFT,7,0));
	 jPNoiDungThongTinThuoc3.setPreferredSize(new Dimension(350,45));
	 jPNoiDungThongTinThuoc3.setBackground(Color.white);
	 jPNoiDungThongTinThuoc4.setLayout(new FlowLayout(FlowLayout.LEFT,7,0));
	 jPNoiDungThongTinThuoc4.setPreferredSize(new Dimension(350,45));
	 jPNoiDungThongTinThuoc4.setBackground(Color.white);
	 jPNoiDungThongTinThuoc5.setLayout(new FlowLayout(FlowLayout.LEFT,7,0));
	 jPNoiDungThongTinThuoc5.setPreferredSize(new Dimension(350,45));
	 jPNoiDungThongTinThuoc5.setBackground(Color.white);
	
	 
	 lblMaThuoc=new JLabel("Mã Thuốc :");
	 lblMaThuoc.setPreferredSize(new Dimension(80,20));
	 
	 txtMaThuoc=new JTextField();
	 txtMaThuoc.setFocusable(false);
	 txtMaThuoc.setEditable(false);
	 txtMaThuoc.setPreferredSize(new Dimension(100,30));
	 
	 lblDonVi=new JLabel("Đơn vị :");
	 lblDonVi.setPreferredSize(new Dimension(80,20));
	 
	 txtDonVi=new JTextField();
	 txtDonVi.setFocusable(false);
	 txtDonVi.setEditable(false);
	 txtDonVi.setPreferredSize(new Dimension(100,30));

	 
	 lblTenThuoc=new JLabel("Tên thuốc :");
	 lblTenThuoc.setPreferredSize(new Dimension(80,20));
	 
	 txtTenThuoc=new JTextField();
	 txtTenThuoc.setEditable(false);
	 txtTenThuoc.setFocusable(false);
	 txtTenThuoc.setPreferredSize(new Dimension(200,30));
	 
	 lblThanhPhan=new JLabel("SL tồn :");
	 lblThanhPhan.setPreferredSize(new Dimension(80,20));
	 
	 txtThanhPhan=new JTextField();
	 txtThanhPhan.setFocusable(false);
	 txtThanhPhan.setEditable(false);
	 txtThanhPhan.setPreferredSize(new Dimension(200,40));
	 
	 lblDonGia=new JLabel("Đơn giá :");
	 lblDonGia.setPreferredSize(new Dimension(80,20));
	 
	 txtDongia=new JTextField();
	 txtDongia.setEditable(false);
	 txtDongia.setFocusable(false);
	 txtDongia.setPreferredSize(new Dimension(100,30));
	 
	 jPNoiDungThongTinThuoc1.add(lblMaThuoc);
	 jPNoiDungThongTinThuoc1.add(txtMaThuoc);
	 
	 jPNoiDungThongTinThuoc2.add(lblTenThuoc);
	 jPNoiDungThongTinThuoc2.add(txtTenThuoc);
	 
	 jPNoiDungThongTinThuoc3.add(lblThanhPhan);
	 jPNoiDungThongTinThuoc3.add(txtThanhPhan);
	 
	 jPNoiDungThongTinThuoc4.add(lblDonGia);
	 jPNoiDungThongTinThuoc4.add(txtDongia);
	 
	 jPNoiDungThongTinThuoc5.add(lblDonVi);
	 jPNoiDungThongTinThuoc5.add(txtDonVi);
	 
	 jPNoiDungThongTinThuoc.add(jPNoiDungThongTinThuoc1);
	 jPNoiDungThongTinThuoc.add(jPNoiDungThongTinThuoc2);
	 jPNoiDungThongTinThuoc.add(jPNoiDungThongTinThuoc5);
     jPNoiDungThongTinThuoc.add(jPNoiDungThongTinThuoc3);
	 jPNoiDungThongTinThuoc.add(jPNoiDungThongTinThuoc4);
	 	 
     //Chuc nang
	 jPChucNang=new JPanel();
	 jPChucNang1=new JPanel();
	 jPChucNang2=new JPanel();
	 
	 jPChucNang.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
	 jPChucNang.setPreferredSize(new Dimension(550,100));
	 jPChucNang.setBackground(Color.white);
	 jPChucNang1.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
	 jPChucNang1.setPreferredSize(new Dimension(280,80));
	 jPChucNang1.setBackground(Color.white);
	 
	 jPChucNang2.setLayout(new FlowLayout(FlowLayout.RIGHT,5,0));
	 jPChucNang2.setPreferredSize(new Dimension(250,80));
	 jPChucNang2.setBackground(Color.white);
	 
	 
	 txtTimKiem=new JTextField("Tìm Kiếm");
	 txtTimKiem.setForeground(Color.gray);
	 txtTimKiem.setBorder(new LineBorder(Color.black,1));
	 txtTimKiem.setPreferredSize(new Dimension(140,35));
	 
	 buttonTimKiem=new JButton("");
	 buttonTimKiem.setBackground(Color.blue);
	 buttonTimKiem.setForeground(Color.yellow);
	 buttonTimKiem.setFocusPainted(false);
	 buttonTimKiem.setBorderPainted(false);
	 buttonTimKiem.setIcon(new FlatSVGIcon("icons/search.svg"));
	 buttonTimKiem.setPreferredSize(new Dimension(50,35));
	 
	 jPChucNang1.add(txtTimKiem);
	 jPChucNang1.add(buttonTimKiem);
	 buttonTimKiem.addActionListener(this);
	 jPChucNang.add(jPChucNang1);
	 
	 txtSoLuong=new JTextField("");
	 txtSoLuong.setText("1");
	 txtSoLuong.setForeground(Color.gray);
	 txtSoLuong.setPreferredSize(new Dimension(50,35));
	 txtSoLuong.setBorder(new LineBorder(Color.black,1));
	 buttonThem=new JButton("Thêm");
	 buttonThem.setBackground(Color.blue);
	 buttonThem.setForeground(Color.yellow);
	 buttonThem.setFocusPainted(false);
	 buttonThem.setBorderPainted(false);
	 buttonThem.setIcon(new FlatSVGIcon("icons/add-to-cart.svg"));
	 buttonThem.setPreferredSize(new Dimension(120,35));
	 buttonThem.addActionListener(this);
	
	 
	 jPChucNang2.add(txtSoLuong);
	 jPChucNang2.add(buttonThem);
	 
	 jPChucNang.add(jPChucNang1);
	 jPChucNang.add(jPChucNang2);
	 
	// jPNoiDungThongTinThuoc.add(jPChucNang);
	 
	 
	 
	 jPNDAnhThuoc.add(jPNoiDungThongTinThuoc);
	 jPNDAnhThuoc.add(jPAnhThuoc);
	 
	
	 jPThongTinThuoc.add(jPNDAnhThuoc,BorderLayout.CENTER);
	 
	
	 
	 

	
	 jPGioHang.setLayout(new BorderLayout());
	// jPGioHang.setBackground(Color.white);
	jPGioHang.setPreferredSize(new Dimension(450, 350)); 
	lblGioHang = new JLabel("Giỏ hàng");
	lblGioHang.setForeground(Color.white);
	lblGioHang.setHorizontalAlignment(SwingConstants.CENTER);
	jPTieuDeGioHang.setLayout(new BorderLayout());
	jPTieuDeGioHang.setBackground(new Color(0, 153, 153));
	jPTieuDeGioHang.setPreferredSize(new Dimension(450, 25));
	jPTieuDeGioHang.add(lblGioHang);

	String[] columName = {"Mã thuốc", "Tên Thuốc", "Số Lượng", "Đơn Giá"};
	 modelGioHang = new DefaultTableModel(columName, 0);
	 tableGioHang = new JTable(modelGioHang);
	

	JScrollPane scrGioHang = new JScrollPane(tableGioHang);
	
	scrGioHang.getViewport().setBackground(Color.white); // Đặt nền Viewport thành màu trắng
	
	
	JPanel jpdelete=new JPanel();
	jpdelete.setLayout(new FlowLayout(FlowLayout.RIGHT,20,0));
	jpdelete.setBackground(Color.white);
	jpdelete.setPreferredSize(new Dimension(420,35));
	
	btndelete=new JButton();
	btndelete.setIcon(new FlatSVGIcon("icons/trash-cart.svg"));
	btndelete.setBackground(Color.red);
	btndelete.setPreferredSize(new Dimension(30,30));
	btndelete.setFocusPainted(false);
	btndelete.setForeground(Color.black);
	btndelete.addActionListener(this);
	jpdelete.add(btndelete);

	jPGioHang.add(jPTieuDeGioHang, BorderLayout.NORTH);
	jPGioHang.add(scrGioHang, BorderLayout.CENTER);
    jPGioHang.add(jpdelete,BorderLayout.SOUTH);
	
	 

	 JPanel jpcen=new JPanel(new  BorderLayout());
	 
	 String [] cot= {"STT","Mã Thuốc","Tên Thuốc","Đơn Vị","Số Lượng Tồn","Đơn Giá"};
     modelThongTinThuoc=new DefaultTableModel(cot,0);
	 tableThongTinThuoc=new JTable(modelThongTinThuoc);
	 JScrollPane scrTTThuoc=new JScrollPane(tableThongTinThuoc);
	 scrTTThuoc.getViewport().setBackground(Color.white);
	 jpcen.add(scrTTThuoc);
	 
	
	 updateTableThuoc();
	 tableThongTinThuoc.addMouseListener(this);
	 
	 

	 jPwest=new JPanel();
	 jPwest.setBackground(Color.white);
	 jPwest.setLayout(new BorderLayout());
	 jPwest.setPreferredSize(new Dimension(450,390));
	 JPanel jPTieuDePhieuDat =new JPanel();
	 jPTieuDePhieuDat.setLayout(new BorderLayout());
	 jPTieuDePhieuDat.setBackground(new Color(0, 153, 153));
	 jPTieuDePhieuDat.setPreferredSize(new Dimension(450,25));
	 JLabel lblTieuDePhieuDat=new  JLabel("Phiếu Đặt");
	 lblTieuDePhieuDat.setForeground(Color.white);
	 lblTieuDePhieuDat.setHorizontalAlignment(SwingConstants.CENTER);
	 jPTieuDePhieuDat.add(lblTieuDePhieuDat);
	 jPwest.add(jPTieuDePhieuDat,BorderLayout.NORTH);
	 
	 jPNoiDungPhieuDat=new JPanel(new FlowLayout(FlowLayout.LEFT,5,10));
	 jPNoiDungPhieuDat.setPreferredSize(new Dimension(350,365));
	 jPNoiDungPhieuDat.setBackground(Color.white);
	 jPNoiDungPhieuDat1=new JPanel(new  FlowLayout(FlowLayout.LEFT,7,0));
	 jPNoiDungPhieuDat1.setBackground(Color.white);
	 jPNoiDungPhieuDat1.setPreferredSize(new Dimension(350,35));
	 jPNoiDungPhieuDat2=new JPanel(new  FlowLayout(FlowLayout.LEFT,7,0));
	 jPNoiDungPhieuDat2.setBackground(Color.white);
	 jPNoiDungPhieuDat2.setPreferredSize(new Dimension(420,35));
	 jPNoiDungPhieuDat3=new JPanel(new  FlowLayout(FlowLayout.LEFT,7,0));
	 jPNoiDungPhieuDat3.setBackground(Color.white);
	 jPNoiDungPhieuDat3.setPreferredSize(new Dimension(350,35));
	
	 jPNoiDungPhieuDat4=new JPanel(new  FlowLayout(FlowLayout.LEFT,7,0));
	 jPNoiDungPhieuDat4.setBackground(Color.white);
	 jPNoiDungPhieuDat4.setPreferredSize(new Dimension(350,35));
	 jPNoiDungPhieuDat5=new JPanel(new  FlowLayout(FlowLayout.CENTER,80,0));
	 jPNoiDungPhieuDat5.setBackground(Color.white);
	 jPNoiDungPhieuDat5.setPreferredSize(new Dimension(450,70));
	 
	 lblMaPhieuDat=new JLabel("Nhân viên:");
	 lblMaPhieuDat.setPreferredSize(new Dimension(130,20));
	 txtmaPhieuDat=new JTextField();
	 txtmaPhieuDat.setPreferredSize(new Dimension(200,30));
	 txtmaPhieuDat.setFocusable(false);
     txtmaPhieuDat.setEditable(false);	
     txtmaPhieuDat.setBorder(new LineBorder(Color.black,1));
	 txtmaPhieuDat.setText(Form_DangNhap.nhanVien.getTenNV());
     
	 jPNoiDungPhieuDat1.add(lblMaPhieuDat);
	 jPNoiDungPhieuDat1.add(txtmaPhieuDat);
	 
	 lblSoDienThoai =new JLabel("Số Điện thoại:");
	 lblSoDienThoai.setPreferredSize(new Dimension(130,20));
	 
	 txtSDT=new JTextField();
	 txtSDT.setPreferredSize(new Dimension(160,30));
	 txtSDT.setBorder(new LineBorder(Color.black,1));

	   ArrayList<String> phoneList = new ArrayList<String>();
	   try {
		   KhachHangService khachHangService = (KhachHangService) Naming.lookup("rmi://localhost:9090/khachHangService");
		   List<KhachHang> dsKH = khachHangService.getAll();
		   dsKH.forEach(khachHang -> {
			   phoneList.add(khachHang.getSoDienThoai());
		   });
		   cmbSDT = new AutoSuggestTextField(phoneList);
		   cmbSDT.addSuggestionSelectedListener(phoneNumber -> {
			   // Look up the customer with this phone number from your data source
			   // This is just an example - replace with your actual lookup code
			   try {
				   KhachHang customer = khachHangService.timBangSDT(phoneNumber);
				   if (customer != null) {
					   txtTenKH.setText(customer.getTenKH());
					   khachhang = customer;
				   } else {
					   txtTenKH.setText("Customer not found");
				   }
			   } catch (Exception ex) {
				   txtTenKH.setText("Error: " + ex.getMessage());
			   }
		   });
	   } catch (Exception e){
		   JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ thuốc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	   }


	  btnAdd=new JButton();
	 btnAdd.setIcon(new FlatSVGIcon("icons/add.svg"));
	 btnAdd.setBackground(Color.white);
	 btnAdd.setPreferredSize(new Dimension(30,30));
	 btnAdd.addActionListener(this);
	   cmbSDT.setPreferredSize(new Dimension(100, 30));
	   btnLook=new JButton();
	 btnLook.setIcon(new FlatSVGIcon("icons/search.svg"));
	 btnLook.setBackground(Color.white);
	 btnLook.setPreferredSize(new Dimension(30,30));
	 btnLook.addActionListener(this);
	 jPNoiDungPhieuDat2.add(lblSoDienThoai);
	 jPNoiDungPhieuDat2.add( cmbSDT);
	 jPNoiDungPhieuDat2.add(btnLook);
	 jPNoiDungPhieuDat2.add(btnAdd);
	 
	 lblTenKhachHang=new JLabel("Khách hàng:");
	 lblTenKhachHang.setPreferredSize(new Dimension(130,20));
	 
	 txtTenKH=new JTextField();
	 txtTenKH.setPreferredSize(new Dimension(200,30));
	 txtTenKH.setBorder(new LineBorder(Color.black,1));
	 txtTenKH.setEditable(false);
	 txtTenKH.setFocusable(false);
	 jPNoiDungPhieuDat3.add(lblTenKhachHang);
	 jPNoiDungPhieuDat3.add(txtTenKH);
	 
	 lblTongTien=new JLabel("Tổng tiền:");
	 lblTongTien.setPreferredSize(new Dimension(130,20));
	 txtTongTien=new JTextField();
	 txtTongTien.setBorder(new LineBorder(Color.black,1));
	 txtTongTien.setPreferredSize(new Dimension(200,30));
	 txtTongTien.setFocusable(false);
	 txtTongTien.setEditable(false);
	 
	 JSeparator Sp= new JSeparator(SwingConstants.HORIZONTAL);
	 Sp.setPreferredSize(new Dimension(450,2));
	 Sp.setBackground(Color.black);
	 Sp.setAlignmentX(SwingConstants.CENTER);
	 jPNoiDungPhieuDat4.add(lblTongTien);
	 jPNoiDungPhieuDat4.add(txtTongTien);
	 
	 buttonHuyBo=new JButton("Hủy");
	 buttonHuyBo.setPreferredSize(new Dimension(80,30));
	 buttonHuyBo.setBackground(Color.red);
	 buttonHuyBo.addActionListener(this);
	 
	 buttonLuu=new JButton("Lưu");
	 buttonLuu.setPreferredSize(new Dimension(80,30));
	 buttonLuu.setBackground(Color.green);
	 buttonLuu.addActionListener(this);
	 
	 jPNoiDungPhieuDat5.add(buttonHuyBo);
	 jPNoiDungPhieuDat5.add(buttonLuu);
	 
	 jPNoiDungPhieuDat.add(jPNoiDungPhieuDat1);
	 jPNoiDungPhieuDat.add(jPNoiDungPhieuDat2);
	 jPNoiDungPhieuDat.add(jPNoiDungPhieuDat3);
	 jPNoiDungPhieuDat.add(Sp);
	 jPNoiDungPhieuDat.add(jPNoiDungPhieuDat4);
	 jPNoiDungPhieuDat.add(jPNoiDungPhieuDat5);
	
			 
	 
	 jPwest.add(jPNoiDungPhieuDat,BorderLayout.SOUTH);
	 
	 
	 
	 jPChucNang.setPreferredSize(new Dimension(450,38));
	 box1.add(jPThongTinThuoc);
	 box1.add( Box.createVerticalStrut(5));
	 box1.add(jPChucNang);
	 box1.add( Box.createVerticalStrut(5));
	 box1.add(jpcen);
		
	 box2.add(jPGioHang);
	 box2.add(Box.createVerticalStrut(5));
	 box2.add(jPwest);
	 
	 box.add(box1);
	 box.add(box.createHorizontalStrut(5));
	 box.add(box2);
	 this.add(box);
		
	 
   }

	private void updateTableThuoc()  {
		// Xóa dữ liệu cũ trong bảng thuốc
		modelThongTinThuoc.setRowCount(0);
		try {
			ThuocService thuocService = (ThuocService) Naming.lookup("rmi://localhost:9090/thuocService");
			dsThuoc = thuocService.getAll();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối đến dịch vụ thuốc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		// Thêm dữ liệu mới vào bảng thuốc
		for (Thuoc thuoc : dsThuoc) {
			Object[] rowData = {
					thuoc.getMaThuoc(),
					thuoc.getMaThuoc(),
					thuoc.getTenThuoc(),
					thuoc.getDonViTinh(),
					thuoc.getSoLuong(),
					thuoc.getGiaBan(),



			};
			modelThongTinThuoc.addRow(rowData);
		}
	}
	public static String getSDT() {
		 String sdtt=txtSDT.getText();
	        return sdtt;
	}
	private void xoaTrang() {
		txtMaThuoc.setText("");
		txtDonVi.setText("");
		txtThanhPhan.setText("");
		txtTenThuoc.setText("");
		txtDongia.setText("");
		txtSoLuong.setText("1");
		
	}
	private String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(new Date());
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				Object o=e.getSource();
				
				if(o.equals(buttonThem)) {
					String soLuong=txtSoLuong.getText();
					int sl=Integer.parseInt(soLuong);

					String soLuongTon=txtThanhPhan.getText();
					int slt=Integer.parseInt(soLuongTon);

					if(sl>0 && sl<slt ) {
						String donGia=txtDongia.getText();
						double dg=Double.parseDouble(donGia)*sl;

						Object [] row= {txtMaThuoc.getText(),txtTenThuoc.getText(),sl,dg};
						modelGioHang.addRow(row);
						updateTotalPrice(modelGioHang, txtTongTien);

						int rowTtt=tableThongTinThuoc.getSelectedRow();

						modelThongTinThuoc.setValueAt(slt-sl, rowTtt, 4);

						xoaTrang();
					}
					else {
						JOptionPane.showMessageDialog(JPCen, "Số lượng k hợp lệ");
					}


				}
		if(o.equals(btnLook)) {


		}
				if(o.equals(btnAdd)) {
				}
				if(o.equals(buttonLuu)) {
					try {
					String ngaykhoitao= getCurrentDate();
						PhieuDatThuocService phieuDatThuocService = (PhieuDatThuocService) Naming.lookup("rmi://localhost:9090/phieuDatThuocService");
						KhachHangService khachHangService = (KhachHangService) Naming.lookup("rmi://localhost:9090/khachHangService");
						ThuocService thuocService = (ThuocService) Naming.lookup("rmi://localhost:9090/thuocService");
						if (tableGioHang.getRowCount() == 0) {
							JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc trước khi lưu hóa đơn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
							return;
						}

						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate date = LocalDate.parse(ngaykhoitao, formatter);
						LocalDateTime dateTime = date.atStartOfDay(); // tạo LocalDateTime với giờ 00:00
						NhanVien nhanVien = Form_DangNhap.nhanVien;
						KhachHang kh = khachhang;

						PhieuDatThuoc Pdthuoc = new PhieuDatThuoc(nhanVien, kh, dateTime, "");
						List<ChiTietPhieuDatThuoc> danhSachChiTiet = new ArrayList<>();
						for (int i = 0; i < tableGioHang.getRowCount(); i++) {
							Integer maThuoc = Integer.parseInt(tableGioHang.getValueAt(i, 0).toString());
							Integer soLuong = Integer.parseInt(tableGioHang.getValueAt(i, 2).toString());
							Double donGia = Double.parseDouble(tableGioHang.getValueAt(i, 3).toString());
							String donViTinh = tableThongTinThuoc.getValueAt(maThuoc, 3).toString();
							Thuoc thuoc = thuocService.findById(maThuoc);
							ChiTietPhieuDatThuoc chiTietPhieuDat = new ChiTietPhieuDatThuoc( Pdthuoc,thuoc, soLuong , donGia, donViTinh);
							danhSachChiTiet.add(chiTietPhieuDat);
						}
						if (phieuDatThuocService.themPhieuDatThuoc(nhanVien.getMaNV(),khachhang.getMaKH(),"",danhSachChiTiet) ){
							JOptionPane.showMessageDialog(this, "Lưu hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							xoaTrang();
							// Xóa dữ liệu trong bảng thuốc đã chọn
							modelGioHang.setRowCount(0);
						}else {
							JOptionPane.showMessageDialog(this, "Lưu hóa đơn thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Lỗi lưu hóa đơn: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
				    
					

				if(o.equals(buttonHuyBo)) {
					
				}
				 if(o.equals(btndelete)){

				 }
	}

	private void updateTotalPrice(DefaultTableModel modelGioHang2, JTextField txtTongTien2) {
		double totalPrice = 0;

		// Duyệt qua tất cả các dòng trong bảng để tính tổng giá
		for (int i = 0; i < modelGioHang2.getRowCount(); i++) {
			double rowPrice = (Double) modelGioHang2.getValueAt(i, 3); // Lấy giá trị "Thành tiền" từ cột thứ 3
			totalPrice += rowPrice;
		}

		// Cập nhật giá trị trong JTextField
		txtTongTien2.setText(String.valueOf(totalPrice));
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
				int row = tableThongTinThuoc.getSelectedRow(); // lay dong dang chon tren table
				txtMaThuoc.setText(tableThongTinThuoc.getValueAt(row,1).toString());
				txtTenThuoc.setText(tableThongTinThuoc.getValueAt(row,2).toString());
				txtDonVi.setText(tableThongTinThuoc.getValueAt(row,3).toString());
				txtThanhPhan.setText(tableThongTinThuoc.getValueAt(row,4).toString());
				txtDongia.setText(tableThongTinThuoc.getValueAt(row,5).toString());
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {

	}

}

