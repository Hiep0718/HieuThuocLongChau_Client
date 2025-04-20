package iuh.fit.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import services.ThongKeDoanhThuService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKeTheoThang extends JPanel {

    private JTable table;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private DefaultTableModel model;
    private DefaultCategoryDataset dataset;
    private ChartPanel chartPanel; // Khai báo biến ChartPanel
    
    public ThongKeTheoThang() {
        setLayout(new BorderLayout());

        // Create table
        String[] columnNames = {"Tháng", "Doanh thu"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Create month and year selectors and search button
        JPanel searchPanel = new JPanel();
        
        yearComboBox = new JComboBox<>(new Integer[]{2024, 2025, 2026}); // Thêm các năm khác nếu cần
        JButton searchButton = new JButton("Tìm kiếm");

        searchPanel.add(new JLabel("Chọn năm:"));
        searchPanel.add(yearComboBox);
        searchPanel.add(searchButton);

        // Add action listener to search button
        searchButton.addActionListener(e -> {
			try {
				dataset.clear();
				model.setRowCount(0);
				filterData();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            } catch (NotBoundException ex) {
                throw new RuntimeException(ex);
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Add components to the frame
        add(searchPanel, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Create dataset for chart
        dataset = new DefaultCategoryDataset();

        // Create and display the chart
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart); // Khởi tạo chartPanel
        chartPanel.setPreferredSize(new Dimension(800, 400));
        add(chartPanel, BorderLayout.NORTH);
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Thống kê Doanh thu " + yearComboBox.getSelectedItem(),
                "Tháng",
                "Số tiền (đ)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    private void filterData() throws SQLException, RemoteException, MalformedURLException, NotBoundException {
        int selectYear= (int) yearComboBox.getSelectedItem();
        String selectedYearStr = String.valueOf(selectYear);
        double DT;
        double CP;
        double LN;
        int i=1;
        ThongKeDoanhThuService tkdoanhThuSv= (ThongKeDoanhThuService) Naming.lookup("rmi://localhost:9090/thongKeDoanhThuService");
        ArrayList<Double> list=tkdoanhThuSv.TongDoanhThuTheothang(selectedYearStr);
        for(double s : list){
            DT=s;
            CP=s*0.8;
            LN=DT-CP;
            model.addRow(new Object[]{i, DT, CP, LN});
            dataset.addValue(DT, "Doanh thu", i+"");
            dataset.addValue(CP, "Chi phí", i+"");
            dataset.addValue(LN, "Lợi nhuận", i+"");
            i++;
        }


 
    }

}
