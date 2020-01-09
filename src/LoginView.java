import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LoginView extends JFrame implements ActionListener {

    //序列化对象时会用到
    public static ObjectOutputStream objectOutputStream = null;
    //反序列化对象时会用到
    public static ObjectInputStream objectInputStream = null;

    private final String teacher_username = "admin";               //老师模式下指定的用户名，仅有一个，不允许修改
    private final String teacher_password = "123456";     //老师模式下指定的登录密码，不允许修改

    //定义显示，输入用户名，密码对应的标签，文本框
    private JLabel username_label;
    private JTextField username_textfield;
    private JLabel password_label;
    private JTextField password_textfield;
    //设置2个单选按钮，分别是教师，学生
    private JRadioButton teacher_radio_button;
    private JRadioButton student_radio_button;

    private JButton OK;     //确定
    private JButton NO;     //取消
    public LoginView(){
        this.setTitle("用户登录");
        initView();
    }
    //设置界面
    private void initView() {
        /*----标题部分----*/
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("欢迎来到学生信息管理系统");
        title.setFont(new Font("楷体", Font.BOLD, 25));
        titlePanel.add(title);

        getContentPane().add(titlePanel, BorderLayout.NORTH);
        /*----输入部分----*/
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        username_label = new JLabel("用户名");
        username_textfield = new JTextField();

        password_label = new JLabel("密码（最高为6位）");
        password_textfield = new JTextField();
        username_label.setBounds(180,90,50,20);
        username_textfield.setBounds(240,90,140,20);
        password_label.setBounds(120,130,200,20);
        password_textfield.setBounds(240,130,140,20);
        inputPanel.add(username_label);
        inputPanel.add(username_textfield);
        inputPanel.add(password_label);
        inputPanel.add(password_textfield);
        /*----选择部分----*/
        ButtonGroup buttonGroup = new ButtonGroup();        //用它来放下面的单选按钮
        teacher_radio_button = new JRadioButton("教师", true);
        student_radio_button = new JRadioButton("学生", false);
        buttonGroup.add(teacher_radio_button);
        buttonGroup.add(student_radio_button);
        teacher_radio_button.setBounds(260, 180, 50,30);
        student_radio_button.setBounds(340,180,50,30);
        inputPanel.add(teacher_radio_button);
        inputPanel.add(student_radio_button);
        getContentPane().add(inputPanel, BorderLayout.CENTER);
        /*----按钮部分----*/
        JPanel buttonPanel = new JPanel();
        OK = new JButton("登录");
        NO = new JButton("取消");
        OK.addActionListener(this);
        NO.addActionListener(this);
        buttonPanel.add(OK);
        buttonPanel.add(NO);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setLocationRelativeTo(null);       //居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(600,200,600,400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == OK){
            if(teacher_radio_button.isSelected()){
                //选中的是教师账号登录
                if(username_textfield.getText().equals(teacher_username) && password_textfield.getText().equals(teacher_password)){
                    new ManageView();
                }else{
                    JOptionPane.showMessageDialog(null, "对不起,您输入的用户名或密码有误,请重新输入");
                    username_textfield.setText("");
                    password_textfield.setText("");
                }
            }else if(student_radio_button.isSelected()){
                //选中的是学生账号登录
                new ShowStudent(this);
            }
        }else{
            //关闭窗口
            System.exit(0);
        }
    }
    //将学生数据存到文件中去
    public static void saveData(TreeSet<Student> s){
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("src\\data.dat"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对象序列化
        try {
            objectOutputStream.writeObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //关闭文件
        try {
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从文件中读取数据
    public static TreeSet<Student> getData(){
        TreeSet<Student> s = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream("src\\data.dat"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s = (TreeSet<Student>) objectInputStream.readObject();
        } catch (IOException e) {
            //若读取对象流失败，则重新初始化学生集合
            s = new TreeSet<Student>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    public static void main(String[] args){
        new LoginView();
    }
}
