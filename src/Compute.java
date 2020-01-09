import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

//此类用用提供各种计算方法
public class Compute {

    //返回优秀，良好，中等，及格，不及格这些阶段的人数
    public static Map<String, Double> getSumInDifferentStage(){
        //获得数据文件中的学生集合
        TreeSet<Student> students = LoginView.getData();
        int excellent_num = 0;          //优秀人数
        int good_num = 0;               //良好人数
        int medium_num = 0;             //中等人数
        int pass_num = 0;               //及格人数
        int failed_num = 0;             //不及格人数
        //遍历学生集合进行统计
        Iterator<Student> iter = students.iterator();
        while(iter.hasNext()){
            Student s = iter.next();
            if(s.getAverageScore() >= 90 && s.getAverageScore() <= 100){
                ++excellent_num;
            }else if(s.getAverageScore() >= 80){
                ++good_num;
            }else if(s.getAverageScore() >= 70){
                ++medium_num;
            }else if(s.getAverageScore() >= 60){
                ++pass_num;
            }else if(s.getAverageScore() >= 0){
                ++failed_num;
            }
        }
        //创建一个用于存放各个阶段人数的对象
        Map<String, Double> s = new HashMap<String, Double>();
        s.put("优秀",0.0 + excellent_num);
        s.put("良好", 0.0 + good_num);
        s.put("中等", 0.0 + medium_num);
        s.put("及格", 0.0 + pass_num);
        s.put("不及格", 0.0 + failed_num);
        return s;
    }

    //返回各科平均成绩
    public static Map<String, Double> getCoursesAverage(){
        //获得数据文件中的学生集合
        TreeSet<Student> students = LoginView.getData();
        //创建一个存放课程名称和对应课程平均分的对象
        Map<String, Double> courseAverage = new HashMap<String, Double>();
        //创建一个存放课程的总分数的对象
        Map<String, Double> courseSum = new HashMap<String, Double>();

        //遍历所有学生对象，计算出每门课程的分数总和
        Iterator<Student> iter = students.iterator();
        while(iter.hasNext()){
            Student temp = iter.next();
            Iterator<Course> iter1 = temp.getCourses().iterator();
            while(iter1.hasNext()){
                Course temp1 = iter1.next();
                if(courseSum.containsKey(temp1.getCourseName())){
                    courseSum.put(temp1.getCourseName(), courseSum.get(temp1.getCourseName()) + temp1.getScore());
                }else{
                    courseSum.put(temp1.getCourseName(), temp1.getScore());
                }
            }
        }

        //通过每门课程分数总和求出每门课程的平均分
        Iterator<Map.Entry<String, Double> > iterator = courseSum.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Double> temp = iterator.next();
            //计算平均值
            courseAverage.put(temp.getKey(), temp.getValue() / students.size());
        }
        return courseAverage;
    }

}
