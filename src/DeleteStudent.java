import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;

public class DeleteStudent extends JDialog implements ActionListener {

    private JLabel snoLabel;        //查询标签
    private JTextField snoField;    //查询文本框
    private JButton snoButton;      //查询按钮

    private JButton YES;            //删除按钮
    private JButton NO;             //取消按钮

    public DeleteStudent(Frame owner) {
        super(owner);
        this.setTitle("删除学生");
        this.setSize(300, 150);
        //使得对话框位于屏幕中央
        this.setLocationRelativeTo(null);
        //设置此窗口不可随意放缩
        this.setResizable(false);
        //设置此窗口为模态对话框，即在打开此窗口时不可与其他窗口有交互
        this.setModal(true);

        /*----添加查找面板----*/
        JPanel panel1 = new JPanel();
        snoLabel = new JLabel("学号:");
        snoField = new JTextField(10);
        panel1.add(snoLabel);
        panel1.add(snoField);

        /*----添加控制面板----*/
        JPanel panel2 = new JPanel();
        YES = new JButton("删除");
        NO = new JButton("取消");
        YES.addActionListener(this);
        NO.addActionListener(this);
        panel2.add(YES);
        panel2.add(NO);

        getContentPane().add(panel1, BorderLayout.NORTH);
        getContentPane().add(panel2, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == YES){
            if(snoField.getText().trim().length() == 0){
                JOptionPane.showMessageDialog(null,"请填写你要删除的学生学号");
                return ;
            }else{
                long sno = Long.parseLong(snoField.getText().trim());
                TreeSet<Student> students = LoginView.getData();
                Iterator<Student> iter = students.iterator();
                //是否找到该学生
                boolean isFind = false;
                while(iter.hasNext()){
                    Student s = iter.next();
                    if(s.getSno() == sno){
                        iter.remove();
                        isFind = true;
                        break;
                    }
                }

                //将数据存到数据文件中
                LoginView.saveData(students);
                if(!isFind){
                    JOptionPane.showMessageDialog(null, "未找到该学生");
                    return ;
                }
                JOptionPane.showMessageDialog(null, "删除成功");
                snoField.setText("");
            }
        }else if(e.getSource() == NO){
            //关闭窗体
            this.dispose();
        }

    }
}
