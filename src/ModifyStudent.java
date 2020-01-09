import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class ModifyStudent extends JDialog implements ActionListener {

    private JLabel searchLable;         //查询标签
    private JTextField searchField;     //查询文本框，按照学号进行查询

    private JLabel sno_label;               //学号标签
    private JLabel name_label;              //姓名标签
    private JLabel birth_label;             //出生日期标签
    private JLabel course1_label;           //数据结构课程标签
    private JLabel course2_label;           //Java课程标签
    private JLabel course3_label;           //计算机网络课程标签
    private JLabel course4_label;           //数字逻辑课程标签
    private JLabel course5_label;           //Linux课程标签

    private JTextField sno_textfield;       //学号文本框
    private JTextField name_textfield;      //姓名文本框
    private JTextField birth_textfield;     //出生日期文本框
    private JTextField course1_textfield;   //数据结构课程文本框
    private JTextField course2_textfield;   //Java课程文本框
    private JTextField course3_textfield;   //计算机网络课程文本框
    private JTextField course4_textfield;   //数字逻辑课程文本框
    private JTextField course5_textfield;   //Linux课程文本框

    private JButton SEARCH;                 //查询按钮
    private JButton NOT;                    //取消查询按钮

    private JButton YES;                    //保存按钮
    private JButton NO;                     //取消保存按钮

    private JPanel panelC;
    private JPanel panelS;


    public ModifyStudent(Frame owner) {
        super(owner);
        this.setLayout(new BorderLayout(50, 50));
        this.setTitle("修改学生信息");
        this.setSize(600, 400);
        //使得对话框位于屏幕正中央
        this.setLocationRelativeTo(null);
        //设置此窗口为模态对话框，即在打开此窗口时不可与其他窗口有交互
        this.setModal(true);
        //设置此窗口不可随意放缩
        this.setResizable(false);

        /*----设置查询面板----*/
        JPanel searchPanel = new JPanel();
        searchLable = new JLabel("请输入学号:");
        searchField = new JTextField(10);
        searchPanel.add(searchLable);
        searchPanel.add(searchField);
        SEARCH = new JButton("查询");
        NOT = new JButton("取消");
        SEARCH.addActionListener(this);
        NOT.addActionListener(this);
        searchPanel.add(SEARCH);
        searchPanel.add(NOT);
        getContentPane().add(searchPanel, BorderLayout.NORTH);

        /*----修改学生基本信息面板----*/
        panelS = new JPanel();
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
        //向此面板西，东区域加入空的面板，使其匀称
        getContentPane().add(new JPanel(), BorderLayout.WEST);
        getContentPane().add(new JPanel(), BorderLayout.EAST);

        /*----添加设有保存，取消的按钮面板----*/
        YES = new JButton("保存");
        NO = new JButton("取消");
        YES.addActionListener(this);
        NO.addActionListener(this);
        panelS.add(YES);
        panelS.add(NO);
        getContentPane().add(panelS, BorderLayout.SOUTH);
        //在未查询前不可点击这两个按钮
        YES.setEnabled(false);
        NO.setEnabled(false);

        //在未查询前 文本框默认状态下不可编辑
        sno_textfield.setEditable(false);
        name_textfield.setEditable(false);
        birth_textfield.setEditable(false);
        course1_textfield.setEditable(false);
        course2_textfield.setEditable(false);
        course3_textfield.setEditable(false);
        course4_textfield.setEditable(false);
        course5_textfield.setEditable(false);

        this.setVisible(true);
    }

    //清空文本框
    public void clear(){
        searchField.setText("");
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
        if(e.getSource() == SEARCH){
            //为查询按钮设置监听器
            if(searchField.getText().length() == 0){
                //查询文本框为空，则提示
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
                return ;
            }else{
                //查询到后 设置文本框为可编辑
                sno_textfield.setEditable(true);
                name_textfield.setEditable(true);
                birth_textfield.setEditable(true);
                course1_textfield.setEditable(true);
                course2_textfield.setEditable(true);
                course3_textfield.setEditable(true);
                course4_textfield.setEditable(true);
                course5_textfield.setEditable(true);

                YES.setEnabled(true);
                NO.setEnabled(true);

                //若找到，则将已有的学生信息填写在对应文本框中
                sno_textfield.setText(student.getSno() + "");
                name_textfield.setText(student.getName());
                //将Date格式转化成特定字符串
                String strDataFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(strDataFormat);
                birth_textfield.setText(sdf.format(student.getBirth()));

                List<Course> course = student.getCourses();
                course1_textfield.setText(course.get(0).getScore() + "");
                course2_textfield.setText(course.get(1).getScore() + "");
                course3_textfield.setText(course.get(2).getScore() + "");
                course4_textfield.setText(course.get(3).getScore() + "");
                course5_textfield.setText(course.get(4).getScore() + "");
            }
        }else if(e.getSource() == NOT){
            //将查询文本框清空
            searchField.setText("");
            //关闭对话框
            this.dispose();
        }else if(e.getSource() == YES){
            //信息填写不完整，弹出提示
            if(sno_textfield.getText().trim().length() == 0 || name_textfield.getText().trim().length() == 0 || birth_textfield.getText().trim().length() == 0
                    || course1_textfield.getText().trim().length() == 0 || course2_textfield.getText().trim().length() == 0 || course3_textfield.getText().length() == 0
                    || course4_textfield.getText().trim().length() == 0 || course5_textfield.getText().trim().length() == 0){
                JOptionPane.showMessageDialog(null, "请填写完整学生信息");
                return ;
            }

            /*----处理文本框中的内容----*/
            long sno = Long.parseLong(sno_textfield.getText().trim());
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
            TreeSet<Student> students = LoginView.getData();
            //移除集合中旧的该学生对象
            Iterator<Student> iter = students.iterator();
            while(iter.hasNext()){
                Student temp = iter.next();
                if(temp.getSno() == sno){
                    iter.remove();
                }
            }
            //添加新修改的学生对象
            students.add(student);

            //刷新数据文件
            LoginView.saveData(students);
            JOptionPane.showMessageDialog(null, "修改成功");
            clear();
            // 文本框再次设置为不可编辑模式
            sno_textfield.setEditable(false);
            name_textfield.setEditable(false);
            birth_textfield.setEditable(false);
            course1_textfield.setEditable(false);
            course2_textfield.setEditable(false);
            course3_textfield.setEditable(false);
            course4_textfield.setEditable(false);
            course5_textfield.setEditable(false);
        }else if(e.getSource() == NO){
            clear();
            //关闭对话框
            this.dispose();
        }

    }
}
