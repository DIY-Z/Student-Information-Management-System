import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.Iterator;
import java.util.Map;

public class PaintPieChart {
    private Map<String, Double> item;
    private String title;
    public PaintPieChart(Map<String, Double> item,String title) {
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

        this.item = item;
        this.title = title;
        //画饼图
        paint();
    }

    //画图
    private void paint() {
        //建立一个饼图
        DefaultPieDataset dpd = new DefaultPieDataset();
        Iterator<Map.Entry<String, Double> > iter = item.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, Double> temp = iter.next();
            //关键字key对应横坐标的各个项，值value对应纵坐标的各个项
            dpd.setValue(temp.getKey(), temp.getValue());
        }
        JFreeChart chart = ChartFactory.createPieChart(title,dpd, true, true, false);
        ChartFrame chartFrame = new ChartFrame(title, chart);
        chartFrame.pack();
        chartFrame.setVisible(true);
    }
}
