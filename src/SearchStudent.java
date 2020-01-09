import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class SearchStudent extends JDialog implements ActionListener {

    private JLabel searchLable;         //学号标签
    private JTextField searchField;     //学号文本框
    private JButton YES;                //查询按钮
    private JButton NO;                 //取消按钮
    private JTable table;               //表格面板
    public SearchStudent(Frame owner) {
        super(owner);
        this.setTitle("成绩查询");
        this.setSize(300, 400);
        //将此窗口置于屏幕中央
        this.setLocationRelativeTo(null);

        //设置此窗口不可随意放缩
        //this.setResizable(false);

        //设置此窗口为模态对话框，即在打开此窗口时不可与其他窗口有交互
        this.setModal(true);
        /*-----添加查询面板-------*/
        JPanel panel1 = new JPanel();
        searchLable = new JLabel("学号:");
        searchField = new JTextField(10);
        panel1.add(searchLable);
        panel1.add(searchField);
        getContentPane().add(panel1, BorderLayout.NORTH);

        /*----添加设有保存，取消的按钮面板----*/
        JPanel panel2 = new JPanel();
        YES = new JButton("查询");
        NO = new JButton("取消");
        YES.addActionListener(this);
        NO.addActionListener(this);
        panel2.add(YES);
        panel2.add(NO);
        getContentPane().add(panel2, BorderLayout.SOUTH);

        /*----负载学生成绩信息的表格面板----*/
        JPanel panel3 = new JPanel();
        //这里用到高级组件——表格模型TabelModel
        //设置表格列名
        String[] columnNames = {"学号", "姓名","课程名称","成绩"};
        //设置表格内容
        String[][] tableValues = new String[0][0];
        //创建表格
        TableModel tablemodel = new DefaultTableModel(tableValues, columnNames);
        table = new JTable();
        table.setModel(tablemodel);
        //滚动面板
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == YES){
            if(searchField.getText().length() == 0){
                //学号文本框为空，则提示
                JOptionPane.showMessageDialog(null,"请输入学号!!!");     //JOptionPane不用创建，可直接拿来使用
                return ;
            }
            /*----从学生集合中查找----*/
            TreeSet<Student> students = LoginView.getData();
            Iterator<Student> iter = students.iterator();
            Student student = null;
            boolean isFind = false;     //标记是否找到
            while(iter.hasNext()){
                student = iter.next();
                if(student.getSno() == Long.parseLong(searchField.getText())){
                    isFind = true;
                    break;
                }
            }
            if(!isFind){
                //未找到，则弹出提示对话框
                JOptionPane.showMessageDialog(null, "没有此学生");
            }else{
                DefaultTableModel tm = new DefaultTableModel(new String[0][0], new String[]{"学号", "姓名","课程名称","成绩"});
                table.setModel(tm);
                List<Course> course = student.getCourses();
                Iterator<Course> courseIterator = course.iterator();
                while(courseIterator.hasNext()){
                    Course course1 = courseIterator.next();
                    tm.addRow(new String[]{student.getSno()+"", student.getName(), course1.getCourseName(), course1.getScore()+""});
                }
            }
            //将文本框清空
            searchField.setText("");
        }else if(e.getSource() == NO){
            //将文本框清空
            searchField.setText("");
            this.dispose();         //关闭对话框
        }
    }
}
