import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Map;

public class PaintBarChart extends JDialog {
    private Map<String, Double> item;              //关键字key对应横坐标的各个项，值value对应纵坐标的各个项
    private String abscissaName;            //横坐标名字
    private String ordinateName;            //纵坐标名字
    private String title;

    public PaintBarChart(Map<String, Double> item, String abscissaName, String ordinateName, String title) {
        /*----进行相关操作使得显示不会出现乱码----*/
        //创建主题样式
        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));
        //设置图例的字体
        standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));
        //应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);

        this.title = title;
        this.item = item;
        this.abscissaName = abscissaName;
        this.ordinateName = ordinateName;
        this.setTitle(title);
        this.setContentPane(createPanel());

        //设置其为不可伸展
        this.setResizable(false);
        //使其位于屏幕中央
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    public CategoryDataset createDataset() //创建柱状图数据集
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Iterator<Map.Entry<String, Double> > iter = item.entrySet().iterator();
        int cnt = 0;
        while(iter.hasNext()){
            Map.Entry<String, Double> temp = iter.next();
            //关键字key对应横坐标的各个项，值value对应纵坐标的各个项
            dataset.setValue(temp.getValue(), ""+(++cnt) ,temp.getKey());
        }
        return dataset;
    }

    public JFreeChart createChart(CategoryDataset dataset) //用数据集创建一个图表
    {
        //创建一个JFreeChart
        JFreeChart chart= ChartFactory.createBarChart(title, abscissaName, ordinateName, dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot=(CategoryPlot)chart.getPlot();//获得图标中间部分，即plot
        CategoryAxis categoryAxis=plot.getDomainAxis();//获得横坐标
        categoryAxis.setLabelFont(new Font("微软雅黑",Font.BOLD,12));//设置横坐标字体
        return chart;
    }

    public JPanel createPanel()
    {
        JFreeChart chart =createChart(createDataset());
        //将chart对象放入Panel面板中去，ChartPanel类已继承Jpanel
        return new ChartPanel(chart);
    }
}
