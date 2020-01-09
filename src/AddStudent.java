import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public class AddStudent extends JDialog implements ActionListener {

    JLabel sno_label;               //学号标签
    JLabel name_label;              //姓名标签
    JLabel birth_label;             //出生日期标签
    JLabel course1_label;           //数据结构课程标签
    JLabel course2_label;           //Java课程标签
    JLabel course3_label;           //计算机网络课程标签
    JLabel course4_label;           //数字逻辑课程标签
    JLabel course5_label;           //Linux课程标签

    JTextField sno_textfield;       //学号文本框
    JTextField name_textfield;      //姓名文本框
    JTextField birth_textfield;     //出生日期文本框
    JTextField course1_textfield;   //数据结构课程文本框
    JTextField course2_textfield;   //Java课程文本框
    JTextField course3_textfield;   //计算机网络课程文本框
    JTextField course4_textfield;   //数字逻辑课程文本框
    JTextField course5_textfield;   //Linux课程文本框

    private JButton YES;                //保存按钮
    private JButton NO;                 //取消按钮

    public AddStudent(Frame owner) {
        super(owner);
        this.setLayout(new BorderLayout(50,50));
        this.setTitle("添加学生信息");
        this.setSize(650, 400);
        //使得对话框位于屏幕正中央
        this.setLocationRelativeTo(null);
        //设置此窗口不可随意放缩
        this.setResizable(false);
        //设置此窗口为模态对话框，即在打开此窗口时不可与其他窗口有交互
        this.setModal(true);

        /*----添加基本信息面板----*/
        JPanel panelS = new JPanel();
        //设置标签和文本框
        sno_label = new JLabel("学号");
        sno_textfield = new JTextField(10);
        name_label = new JLabel("姓名");
        name_textfield = new JTextField(10);
        birth_label = new JLabel("出生日期(yyyy-MM-dd)");
        birth_textfield = new JTextField(10);
        course1_label = new JLabel("数据结构");
        course1_textfield = new JTextField(10);
        course2_label = new JLabel("Java");
        course2_textfield = new JTextField(10);
        course3_label = new JLabel("计算机网络");
        course3_textfield = new JTextField(10);
        course4_label = new JLabel("数字逻辑");
        course4_textfield = new JTextField(10);
        course5_label = new JLabel("Linux");
        course5_textfield = new JTextField(10);
        //此面板用于放上面的标签和文本框
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(8, 2, 0, 6));   //采用网格布局,使得它们排列均匀
        contentPanel.add(sno_label);
        contentPanel.add(sno_textfield);
        contentPanel.add(name_label);
        contentPanel.add(name_textfield);
        contentPanel.add(birth_label);
        contentPanel.add(birth_textfield);
        contentPanel.add(course1_label);
        contentPanel.add(course1_textfield);
        contentPanel.add(course2_label);
        contentPanel.add(course2_textfield);
        contentPanel.add(course3_label);
        contentPanel.add(course3_textfield);
        contentPanel.add(course4_label);
        contentPanel.add(course4_textfield);
        contentPanel.add(course5_label);
        contentPanel.add(course5_textfield);

        //将此面板插入到窗体中
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        //向此面板中加入空的面板，其匀称
        getContentPane().add(new JPanel(), BorderLayout.NORTH);
        getContentPane().add(new JPanel(), BorderLayout.WEST);
        getContentPane().add(new JPanel(), BorderLayout.EAST);

        /*----添加设有保存，取消的按钮面板----*/
        YES = new JButton("确定");
        NO = new JButton("取消");
        YES.addActionListener(this);
        NO.addActionListener(this);
        panelS.add(YES);
        panelS.add(NO);
        getContentPane().add(panelS, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    //清空文本框
    public void clear(){
        sno_textfield.setText("");
        name_textfield.setText("");
        birth_textfield.setText("");
        course1_textfield.setText("");
        course2_textfield.setText("");
        course3_textfield.setText("");
        course4_textfield.setText("");
        course5_textfield.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == YES){
            //信息填写不完整，弹出提示
            if(sno_textfield.getText().trim().length() == 0 || name_textfield.getText().trim().length() == 0 || birth_textfield.getText().trim().length() == 0
            || course1_textfield.getText().trim().length() == 0 || course2_textfield.getText().trim().length() == 0 || course3_textfield.getText().length() == 0
            || course4_textfield.getText().trim().length() == 0 || course5_textfield.getText().trim().length() == 0){
                JOptionPane.showMessageDialog(null, "请填写完整学生信息");
                return ;
            }

            /*----处理文本框中的内容----*/
            long sno = Long.parseLong(sno_textfield.getText().trim());

            //看是否存在该学生
            TreeSet<Student> students = LoginView.getData();
            Iterator<Student> iter = students.iterator();
            while(iter.hasNext()){
                Student temp = iter.next();
                if(temp.getSno() == sno){
                    JOptionPane.showMessageDialog(null, "该学生已存在");
                    clear();
                    return ;
                }
            }

            String name = name_textfield.getText().trim();
            String birth = birth_textfield.getText().trim();
            //将日期字符串转化为Date格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date datetime = null;
            try {
                datetime = simpleDateFormat.parse(birth);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            //产生对应的学生的对象
            Student student = new Student(sno, name, datetime);
            //添加课程成绩
            student.addCourse(new Course(course1_label.getText().trim(), Double.parseDouble(course1_textfield.getText().trim())));
            student.addCourse(new Course(course2_label.getText().trim(), Double.parseDouble(course2_textfield.getText().trim())));
            student.addCourse(new Course(course3_label.getText().trim(), Double.parseDouble(course3_textfield.getText().trim())));
            student.addCourse(new Course(course4_label.getText().trim(), Double.parseDouble(course4_textfield.getText().trim())));
            student.addCourse(new Course(course5_label.getText().trim(), Double.parseDouble(course5_textfield.getText().trim())));

            //将学生对象添加到学生集合中
            students.add(student);
            //刷新数据文件
            LoginView.saveData(students);
            JOptionPane.showMessageDialog(null, "添加成功");
            clear();
        }else if(e.getSource() == NO){
            clear();
            this.dispose();
        }

    }
}
