import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class ManageView extends JFrame implements ActionListener {
    private JMenuBar menuBar;               //菜单栏
    private JMenu studentManageMenu;        //点击后会列出学生管理操作列表
    private JMenuItem addStudentMenuItem;   //点击后会显示添加学生信息操作界面
    private JMenuItem deleteStudentMenuItem;    //点击后会显示删除学生信息操作界面
    private JMenuItem modifyStudentMenuItem;    //点击后会显示修改学生信息操作界面
    private JMenuItem searchStudentMenuItem;    //点击后会显示查询学生信息操作界面
    private JMenuItem showStudentMenuItem;      //点击后会显示所有学生的成绩列表
    private JMenu scoreAnalyseMenu;         //成绩分析菜单
    private JMenuItem courseMenuItem;       //点击后会显示课程平均成绩柱状图
    private JMenu studentsMenu;     //点击后会显示学生成绩分析情况
    private JMenuItem studentsPieMenuItem;      //点击后会显示学生成绩情况饼状图
    private JMenuItem studentsBarMenuItem;      //点击后会显示学生成绩情况柱状图

    public ManageView(){
        super("学生信息管理系统");
        setLayout(new BorderLayout(35,2));
        menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        //创建学生管理菜单
        studentManageMenu = new JMenu("学生管理");
        //创建学生管理菜单下的菜单项
        addStudentMenuItem = new JMenuItem("添加学生信息");
        deleteStudentMenuItem = new JMenuItem("删除学生信息");
        modifyStudentMenuItem = new JMenuItem("修改学生信息");
        searchStudentMenuItem = new JMenuItem("查询学生信息");
        showStudentMenuItem = new JMenuItem("展示学生信息");
        //向学生管理菜单内添加菜单项
        studentManageMenu.add(addStudentMenuItem);
        studentManageMenu.add(deleteStudentMenuItem);
        studentManageMenu.add(modifyStudentMenuItem);
        studentManageMenu.add(searchStudentMenuItem);
        studentManageMenu.add(showStudentMenuItem);

        //创建成绩分析菜单
        scoreAnalyseMenu = new JMenu("成绩分析");
        //创建成绩分析下的菜单及菜单项
        courseMenuItem = new JMenuItem("课程平均成绩情况");
        studentsMenu = new JMenu("学生成绩情况");
        studentsPieMenuItem = new JMenuItem("饼状图");
        studentsBarMenuItem = new JMenuItem("柱状图");
        //向成绩分析菜单中加入菜单项
        scoreAnalyseMenu.add(courseMenuItem);
        scoreAnalyseMenu.add(studentsMenu);
        studentsMenu.add(studentsBarMenuItem);
        studentsMenu.add(studentsPieMenuItem);
        //向菜单栏中依次插入菜单
        menuBar.add(studentManageMenu);
        menuBar.add(scoreAnalyseMenu);
        this.add(menuBar, BorderLayout.NORTH);
        getContentPane().add(new JLabel(new ImageIcon("src\\BackGround.png")),  BorderLayout.CENTER);
        //为菜单项设置监听器
        addStudentMenuItem.addActionListener(this);
        deleteStudentMenuItem.addActionListener(this);
        modifyStudentMenuItem.addActionListener(this);
        searchStudentMenuItem.addActionListener(this);
        showStudentMenuItem.addActionListener(this);
        courseMenuItem.addActionListener(this);
        studentsPieMenuItem.addActionListener(this);
        studentsBarMenuItem.addActionListener(this);
        this.setSize(689, 460);
     //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //将此窗口置于屏幕中央
        this.setLocationRelativeTo(null);
        //设置窗体为不可扩展
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addStudentMenuItem){
            //打开录入学生信息对话框
            new AddStudent(this);
        }else if(e.getSource() == deleteStudentMenuItem){
            //打开删除学生信息对话框
            new DeleteStudent(this);
        } else if(e.getSource() == modifyStudentMenuItem){
            //打开修改对话框
            new ModifyStudent(this);
        } else if(e.getSource() == searchStudentMenuItem){
            //打开查询对话框
            new SearchStudent(this);
        }else if(e.getSource() == showStudentMenuItem) {
            //打开列出学生成绩列表对话框
            new ShowStudent(this);
        } else if(e.getSource() == studentsPieMenuItem){
            //显示学生状况饼状图
            new PaintPieChart(Compute.getSumInDifferentStage(), "学生成绩情况饼状图");
        }else if(e.getSource() == studentsBarMenuItem){
            //显示学生状况柱状图
            new PaintBarChart(Compute.getSumInDifferentStage(), "学生成绩情况", "人员数量", "学生成绩情况柱状图");
        }else if(e.getSource() == courseMenuItem){
            //显示课程平均成绩柱状图
            new PaintBarChart(Compute.getCoursesAverage(), "课程名称", "课程平均分", "课程平均成绩柱状图");
        }
    }
}
