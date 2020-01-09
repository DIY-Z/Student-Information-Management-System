import java.io.Serializable;
import java.util.*;
public class Student implements Comparable<Student>,Serializable{
    private long sno;		//学号
    private String name;	//姓名
    private Date birth;		//出生日期
    private List<Course> courses;	//所修课程
    private double courseOfSumScore;		//专业课程总分数

    public Student(long sno, String name, Date birth) {
        this.sno = sno;
        this.name = name;
        this.birth = birth;
        this.courses = new ArrayList<Course>();
        this.courseOfSumScore = 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (sno ^ (sno >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (sno != other.sno)
            return false;
        return true;
    }

    //计算课程分数
    public void computeCourseScore() {
        this.courseOfSumScore = 0;
        Iterator<Course> iter = courses.iterator();
        while(iter.hasNext()) {
            this.courseOfSumScore += iter.next().getScore();
        }
    }
    //获得学号
    public long getSno() {
        return sno;
    }
    //修改学号
    public void setSno(long sno) {
        this.sno = sno;
    }
    //获得姓名
    public String getName() {
        return name;
    }
    //修改姓名
    public void setName(String name) {
        this.name = name;
    }
    //获得出生日期
    public Date getBirth() {
        return birth;
    }
    //修改出生日期
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    //添加课程
    public void addCourse(Course e){
        courses.add(e);
        courseOfSumScore += e.getScore();
    }
    //获得所修课程
    public List<Course> getCourses() {
        return courses;
    }
    //获得课程总分数
    public double getCourseOfSumScore() {
        return courseOfSumScore;
    }
    @Override
    public int compareTo(Student o) {
        //按照平均分排序
        if(this.getAverageScore() < o.getAverageScore())
            return -1;
        else if(this.getAverageScore() > o.getAverageScore())
            return 1;
        return 0;
    }
    //获得平均成绩
    public double getAverageScore(){
        return courseOfSumScore / (double)courses.size();
    }
    public String toString(){
        return courses.toString();
    }
}
