package iuh.fit.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class ThongKeTheoNam extends JPanel {

    private JTable table;
    private JComboBox<Integer> startYearComboBox; // Hộp chọn năm bắt đầu
    private JComboBox<Integer> endYearComboBox;   // Hộp chọn năm kết thúc
    private DefaultTableModel model;
    private DefaultCategoryDataset dataset;
    private ChartPanel chartPanel;

    // Map để lưu trữ doanh thu, chi phí và lợi nhuận cho từng năm
    

    public ThongKeTheoNam() {
       
        setLayout(new BorderLayout());

        // Tạo bảng
        String[] columnNames = {"Năm", "Doanh thu", "Chi phí", "Lợi nhuận"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Tạo panel tìm kiếm với JComboBox cho năm bắt đầu và năm kết thúc
        JPanel searchPanel = new JPanel();
        startYearComboBox = new JComboBox<>();
        endYearComboBox = new JComboBox<>();
        JButton searchButton = new JButton("Tìm kiếm");

        // Thêm các năm vào JComboBox
        for (int year = 2024; year <= 2024; year++) {
            startYearComboBox.addItem(year);
            endYearComboBox.addItem(year);
        }

        searchPanel.add(new JLabel("Năm bắt đầu:"));
        searchPanel.add(startYearComboBox);
        searchPanel.add(new JLabel("Năm kết thúc:"));
        searchPanel.add(endYearComboBox);
        searchPanel.add(searchButton);

        // Thêm action listener cho nút tìm kiếm
        searchButton.addActionListener(e -> {
			try {
				filterData();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        // Thêm các thành phần vào frame
        add(searchPanel, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Tạo dataset cho biểu đồ
        dataset = new DefaultCategoryDataset();

        // Tạo và hiển thị biểu đồ
        JFreeChart chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        add(chartPanel, BorderLayout.NORTH);

        // Khởi tạo map để lưu trữ dữ liệu
//        dataMap = new HashMap<>();
    ;
    }

   

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Thống kê Doanh thu, Chi phí và Lợi nhuận giữa các năm",
                "Năm",
                "Số tiền (đ)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }
//    private static void initFlatlaf() {
//        FlatRobotoFont.install();
//        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
//        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
//        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
//        FlatLaf.registerCustomDefaultsSource("style");
//        FlatIntelliJLaf.setup();
//    }
    private void filterData() throws SQLException {

       
    }

}
