package iuh.fit.gui;


import model.Thuoc;
import services.ThuocService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.rmi.Naming;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Form_DoiTra extends JPanel implements ActionListener{
    private JPanel penHeader;
	private JPanel penTask;
	private JPanel penSouth;
	private DefaultTableModel data2;
	private JButton btnDoi;
	private JButton btnTra;
	private JButton btnXoa;
	private DefaultTableModel data1;
	private JTextField jtexMaHD;
	private JTextField jtextenNV;
	private JTable table1;
	private JTable table2;
	private DefaultTableModel data3;
	private JTextField txtTienTra;
	private JTextField txtTienThu;
	private JTable table3;
	int x = 0;
	private int selectable = -1;
	private boolean isDoi = true;
	private JTextField jtexSoLuong;
	private JButton btnTimKiem;
	private JTextField jtexTimKiem;
    private List<Thuoc> dsThuoc;

	public Form_DoiTra(){
        setLayout(new BorderLayout());
        

        
        penHeader = new JPanel();
        penHeader.setLayout(new BoxLayout(penHeader, BoxLayout.X_AXIS));

        JPanel thongTinDT = new JPanel(new GridLayout(3, 2, 10, 10));
        thongTinDT.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Thông tin đổi trả"));


        JLabel lbltenNV = new JLabel("Nhân viên:");
        jtextenNV = new JTextField();
        jtextenNV.setEnabled(false);
        thongTinDT.add(lbltenNV);
        thongTinDT.add(jtextenNV);


        JLabel lblMaHD = new JLabel("Mã hóa đơn:");
        jtexMaHD = new JTextField();
        thongTinDT.add(lblMaHD);
        thongTinDT.add(jtexMaHD);

  
        JLabel lblNgayDoi = new JLabel("Ngày đổi sản phẩm:");
        JLabel lblNgayDoiValue = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        thongTinDT.add(lblNgayDoi);
        thongTinDT.add(lblNgayDoiValue);

        JPanel lyDoDT = new JPanel(new GridLayout(4, 2, 10, 10)); 
        lyDoDT.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Lý do và tình trạng"));

        
        JLabel lblLyDo = new JLabel("Lý do đổi:");
        JTextField jtexLyDo = new JTextField();
        jtexLyDo.setEnabled(false);
        lyDoDT.add(lblLyDo);
        lyDoDT.add(jtexLyDo);

     
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        JComboBox<String> comboBoxTrangThai = new JComboBox<>(new String[]{"Đổi sản phẩm", "Trả sản phẩm"});
        lyDoDT.add(lblTrangThai);
        lyDoDT.add(comboBoxTrangThai);
        
    
        btnDoi = new JButton(" Đổi ");
        btnTra = new JButton(" Trả ");
        btnXoa = new JButton(" Xóa");
        
        btnTra.setEnabled(false);

        comboBoxTrangThai.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedStatus = (String) comboBoxTrangThai.getSelectedItem();
                isDoi = "Đổi sản phẩm".equals(selectedStatus);
                
                if ("Đổi sản phẩm".equals(selectedStatus)) {
                    btnTra.setEnabled(false);
                    btnDoi.setEnabled(true);
                    x = 1;
                }else if ("Trả sản phẩm".equals(selectedStatus)) {
                    btnDoi.setEnabled(false);
                    btnTra.setEnabled(true);
                    x = 2;
                }
            }
        });


        JLabel lblTinhTrang = new JLabel("Tình trạng thuốc:");
        JComboBox<String> comboBoxTinhTrang = new JComboBox<>(new String[]{"Nguyên tem", "Đã mở tem"});
        lyDoDT.add(lblTinhTrang);
        lyDoDT.add(comboBoxTinhTrang);


        JLabel lblSoLuong = new JLabel("Số lượng:");
        jtexSoLuong = new JTextField();
        lyDoDT.add(lblSoLuong);
        lyDoDT.add(jtexSoLuong);

        JPanel taskPanel = new JPanel(); 
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Các tác vụ"));
        taskPanel.setPreferredSize(new Dimension(80, 150));
  

        btnDoi.setBackground(new Color(0, 102, 204));
        btnTra.setBackground(new Color(0, 153, 255));
        btnXoa.setBackground(new Color(102, 204, 255));

        btnDoi.setForeground(Color.WHITE);
        btnTra.setForeground(Color.WHITE);
        btnXoa.setForeground(Color.WHITE);


        taskPanel.add(btnDoi);
        taskPanel.add(btnTra);
        taskPanel.add(btnXoa);
        
        penHeader.add(thongTinDT);
        penHeader.add(lyDoDT);
        penHeader.add(taskPanel);

        
        add(penHeader, BorderLayout.NORTH);


      // tạo table 1
        String[] columnNames1 = {"Mã hóa đơn","Mã thuốc", "Số lượng", "Đơn giá", "Đơn vị"};
        data1 = new DefaultTableModel(columnNames1, 0);
        table1 = new JTable(data1);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        scrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh mục thuốc của hóa đơn"));

        // tạo table 2
        String[] columnNames2 = {"Mã thuốc", "Tên thuốc", "Đơn giá", "Số lượng tồn", "Danh mục", "Đơn vị tính"};
        data2 = new DefaultTableModel(columnNames2, 0);
        updateTableThuoc();
        table2 = new JTable(data2);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Danh mục thuốc"));

        JPanel panelTimKiem = new JPanel(new BorderLayout());
        JLabel lblTimKiem = new JLabel("Tìm kiếm:");
        jtexTimKiem = new JTextField();
        btnTimKiem = new JButton("Tìm");

        panelTimKiem.add(lblTimKiem, BorderLayout.WEST);
        panelTimKiem.add(jtexTimKiem, BorderLayout.CENTER);
        panelTimKiem.add(btnTimKiem, BorderLayout.EAST);

        // Create panel for search and table 2
        JPanel searchAndTablePanel = new JPanel(new BorderLayout());
        searchAndTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Tìm kiếm thuốc"));
        
     // Tạo table3
        JPanel penSouth = new JPanel();
        String[] columnNames3 = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Đơn vị tính"};
        data3 = new DefaultTableModel(columnNames3, 0);
        table3 = new JTable(data3);
        JScrollPane scrollPane3 = new JScrollPane(table3);
        scrollPane3.setPreferredSize(new Dimension(0, 150));
        scrollPane3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Sản phẩm muốn đổi/trả"));



        searchAndTablePanel.add(panelTimKiem, BorderLayout.NORTH); 
        searchAndTablePanel.add(scrollPane2, BorderLayout.CENTER); 
        

 
        JPanel tablePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        tablePanel.add(scrollPane1); 
        tablePanel.add(searchAndTablePanel);
        add(tablePanel, BorderLayout.CENTER);
        

        
        JPanel penChiPhi = new JPanel();
        penChiPhi.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        JLabel tienThu = new JLabel("Thu vào:");
        JLabel tienTra = new JLabel("Trả lại:");
        txtTienThu = new JTextField();
        txtTienTra = new JTextField();
        tienThu.setPreferredSize(new Dimension(70, 30)); 
        tienTra.setPreferredSize(new Dimension(50, 30));
        txtTienTra.setPreferredSize(new Dimension(150, 30));
        txtTienThu.setPreferredSize(new Dimension(150, 30));
        txtTienThu.setEnabled(false);
        txtTienTra.setEnabled(false);
        penChiPhi.add(tienTra);
        penChiPhi.add(txtTienTra);
        penChiPhi.add(tienThu);
        penChiPhi.add(txtTienThu);
        
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(scrollPane3, BorderLayout.CENTER); // Đặt bảng table3 vào giữa
        southPanel.add(penChiPhi, BorderLayout.SOUTH);
        
        add(southPanel, BorderLayout.SOUTH);
        

        
        jtexTimKiem.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateTable();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateTable();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateTable();
			}
			
            // Phương thức cập nhật bảng khi có thay đổi trong ô tìm kiếm
            private void updateTable() {
                String searchTerm = jtexTimKiem.getText().toLowerCase();

                if (data2.getRowCount() == 0) {
                    // Không cần hiển thị thông báo nếu không tìm thấy thuốc
                    // JOptionPane.showMessageDialog(Form_DoiTra.this, "Không tìm thấy thuốc phù hợp!", "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
                }
            }
		});

        //
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	selectable = 0;
            	int selectedRow = table1.getSelectedRow();
                if (selectedRow != -1) {
                    String soLuong = table1.getValueAt(selectedRow, 2).toString();
                    jtexSoLuong.setText(soLuong); // Hiển thị số lượng từ bảng 1
                }
            }
        });
        
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent evt) {
        		selectable = 1;
        		int currentSoLuong = 0;
        		jtexSoLuong.setText("");
        		if(jtexMaHD.getText().isEmpty()) {
        			JOptionPane.showMessageDialog(null, "Danh mục hóa đơn không có dữ liệu", "", JOptionPane.WARNING_MESSAGE);
        			return;
        		}
        		int selectedRow = table2.getSelectedRow();
        		
        		if(selectedRow != -1) {
        			String tenThuoc = table2.getValueAt(selectedRow, 1).toString();
        			String soLuong = table2.getValueAt(selectedRow, 3).toString();
        			
        			if (soLuong.equals(0)) {
                        JOptionPane.showMessageDialog(null, "Sản phẩm '" + tenThuoc + "' hết hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
        			jtexSoLuong.setText(soLuong);
        		}
        	}
        	
        });
        
        jtexMaHD.addActionListener(this);
        btnTimKiem.addActionListener(this);
        jtexSoLuong.addActionListener(this);
        btnXoa.addActionListener(this);
        btnDoi.addActionListener(this);
        btnTra.addActionListener(this);

    }  // "}" của constructor
	
	//neu chon bang 2
	private void selecTable2() {
		// TODO Auto-generated method stub
    	int selectedRow2 = table2.getSelectedRow();
    	if (selectedRow2 == -1) {
            JOptionPane.showMessageDialog(Form_DoiTra.this, "Vui lòng chọn sản phẩm từ danh sách thuốc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            String maThuoc = table2.getValueAt(selectedRow2, 0).toString();
            String tenThuoc = table2.getValueAt(selectedRow2, 1).toString();
            double donGia = Double.parseDouble(table2.getValueAt(selectedRow2, 2).toString());
            int soLuongTon = Integer.parseInt(table2.getValueAt(selectedRow2, 3).toString());
            String donViTinh = table2.getValueAt(selectedRow2, 5).toString();

            int soLuongNhap = Integer.parseInt(jtexSoLuong.getText().trim());
            
            if (soLuongNhap <= 0 || soLuongNhap > soLuongTon) {
            	showMessage("Số lượng nhập không hợp lệ", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
            table2.setValueAt(soLuongTon - soLuongNhap, selectedRow2, 3);
            jtexSoLuong.setText("");
            
            //thêm vào bảng 1
        	Object[] rowData1 = {jtexMaHD.getText(), maThuoc, soLuongNhap, donGia, donViTinh};
            model1.addRow(rowData1);
            
	        int soLuongtable1 = 0;
	        for (int i = 0; i < model1.getRowCount(); i++) {
	        	String maThuoctb1 = model1.getValueAt(i, 1).toString();
	            if (maThuoc.equals(maThuoctb1)) {
	            	soLuongtable1 = Integer.parseInt(model1.getValueAt(i, 2).toString());
	            	break;
	            }
	        }
            
        }catch (NumberFormatException ex) {
        	showMessage("Số lượng nhập vào không hợp lệ!", JOptionPane.ERROR_MESSAGE);
        }
		
	}
	
	private void showMessage(String message, int messageType) {
        JOptionPane.showMessageDialog(Form_DoiTra.this, message, "Thông báo", messageType);
    }
	//xuat hoa don
		private void xuatHD() {
	}
	//neu chon bang 1
	private void selecTable1() {
		
	}
	
	private void updateTableByMaHD() {

	}

	
	private String timMaKH(String maHD) {
		return null;
	}

	
	private void updateTable2() {
		
	}
	private double tinhThanhTien() {
    	return 0;
    }
	

	@Override
	public void actionPerformed(ActionEvent e) {

	}

    private void updateTableThuoc()  {
        // Xóa dữ liệu cũ trong bảng thuốc
        data2.setRowCount(0);
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
                    thuoc.getTenThuoc(),
                    thuoc.getGiaBan(),
                    thuoc.getGiaNhap(),
                    thuoc.getSoLuong(),
                    thuoc.getDonViTinh()
            };
            data2.addRow(rowData);
        }
    }
	
}