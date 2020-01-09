import java.io.Serializable;
public class Course implements Serializable {

    public static String[] courselist = {"数据结构", "Java", "计算机网络", "数字逻辑", "Linux"};

    private String courseName;		//课程名称
    private double score;				//课程分数
    public Course(String courseName, double score) {
        this.courseName = courseName;
        this.score = score;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
}
