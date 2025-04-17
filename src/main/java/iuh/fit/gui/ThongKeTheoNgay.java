package iuh.fit.gui;

import com.toedter.calendar.JDateChooser;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ThongKeTheoNgay extends JPanel {

    private JTable table;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private DefaultTableModel model;
    private DefaultCategoryDataset dataset;
    private JDatePickerImpl datePicker;
    private JDatePickerImpl datePicker1;
    
    public ThongKeTheoNgay() {
        setLayout(new BorderLayout());

        // Create chart
        dataset = new DefaultCategoryDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));

        // Create table
        String[] columnNames = {"Ngày", "Doanh thu"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
 

        
        JPanel searchPanel = new JPanel();
        
        JButton searchButton = new JButton("Tìm kiếm");
        
        Properties p = new Properties();
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, null);
        
        Properties p1 = new Properties();
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
       
        datePicker.addActionListener(e -> {
            // Lấy giá trị ngày đã chọn
            Date selectedDate = (Date) datePicker.getModel().getValue();
            
            if (selectedDate != null) {
                // Định dạng ngày để hiển thị
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = dateFormat.format(selectedDate);
                
                // Hiển thị ngày vào ô của JDatePicker
                datePicker.getJFormattedTextField().setText(formattedDate);
            }
        });
        
        
        
        searchPanel.add(new JLabel("Ngày:"));
        searchPanel.add(datePicker);
        
        searchPanel.add(searchButton);


        // Add components to the frame
        add(searchPanel, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(chartPanel, BorderLayout.NORTH);
        
        searchButton.addActionListener(e ->{
                

        	
        }
      );

        
        
    }
    

    private void updattechart(String formattedDate) throws SQLException {
		
	}

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Thống kê Doanh thu",
                "Ngày",
                "Số tiền(đ)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    

}
