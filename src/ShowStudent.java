import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ShowStudent extends JDialog implements ActionListener {


    public ShowStudent(Frame owner) {
        super(owner);
        this.setTitle("显示全体学生成绩列表");
        this.setSize(700, 400);
        //使得窗口显示在屏幕中央
        this.setLocationRelativeTo(null);
        /*----设计学生成绩列表----*/
        JPanel panel = new JPanel();
        //设置表格列名
        String[] columnNames = {"学号", "姓名","数据结构", "Java", "计算机网络", "数字逻辑", "Linux","平均成绩"};
        String[][] tableValues = new String[0][0];
        DefaultTableModel tablemodel = new DefaultTableModel(tableValues, columnNames){
            //重写DefaultTableModal的getColumnClass方法，使得其按照自己的类型对应的排序，而不再仅使用Object类型的排序
            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };
        //创建表格
        JTable table = new JTable();
        table.setModel(tablemodel);
        RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tablemodel);
        table.setRowSorter(sorter);


        TreeSet<Student> students = LoginView.getData();
        Iterator<Student> iter1 = students.iterator();
        while(iter1.hasNext()){
            Student s = iter1.next();
            List<Course> courseList = s.getCourses();
            String[] tableContent = new String[courseList.size() + 2];
            tablemodel.addRow(new String[]{s.getSno()+"", s.getName()+"", courseList.get(0).getScore()+"", courseList.get(1).getScore()+"",
                    courseList.get(2).getScore()+"", courseList.get(3).getScore()+"", courseList.get(4).getScore()+"", s.getAverageScore()+""});

        }

        //滚动面板
        JScrollPane scrollPane = new JScrollPane(table);
        //将滚动面板插入到窗体的内容面板中
        getContentPane().add(scrollPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
