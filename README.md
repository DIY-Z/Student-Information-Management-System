# 学生成绩管理系统

​		基于Java的GUI编写，使用了SWING,AWT,JFreeChart，通过文件存储进行数据存储。

## 功能介绍

​		有两种模式，一种是学生模式，另一种是教师模式。在学生模式下只能进行查看学生信息操作；在教师模式下能够进行学生信息的增添、删除、修改、查询、显示，以及可以通过柱状图，饼状图查看课程平均成绩情况，学生优秀、良好、中等、及格、不及格的人数分布情况。这些功能全部通过GUI的形式呈现，能够实现文件存储功能。

## 架构设计

​		此实验中实现的每一个功能都用了一个类来实现，共用到12个类，分别为AddStudent,Compute,Course,DeleteStudent,LoginView,ManageView,ModifyStudent,PaintBarStudent,PaintPieChart,SearchStudent,ShowStudent,Student。运行程序后进入登录界面，该界面由LoginView类实现。选择学生模式可以直接登录，无需输入用户名和密码，不过只能提供查询成绩功能，这一功能在类SearchStudent中实现；选择教师模式后，进入管理界面，此界面通过ManageView类实现。管理界面中的删除，修改，查询，增加，显示学生信息的功能依次通过DeleteStudent,ModifyStudent,SearchStudent,AddStudent,ShowStudent这五个类实现。课程平均成绩的柱状图显示，学生及格情况的柱状图和饼状图显示利用JFreeChart类库中的方法实现，在实验中通过两个专门实现画图功能的PaintBarChart,PaintPieChart实现饼状图，柱状图的显示。柱状图、饼状图的数据来源是类Compute，此类用于计算课程平均成绩，学生平均成绩。

##  软件演示截图

### 1.  登录界面

![](https://github.com/DIY-Z/Student-Information-Management-System/blob/master/Readme中的图片/登录界面.png)

### 2.  修改界面

![](https://github.com/DIY-Z/Student-Information-Management-System/blob/master/Readme中的图片/修改界面.png)

### 3.  删除界面

![](https://github.com/DIY-Z/Student-Information-Management-System/blob/master/Readme中的图片/删除界面.png)

### 4. 显示界面

![](https://github.com/DIY-Z/Student-Information-Management-System/blob/master/Readme中的图片/显示界面.png)

### 5. 查询界面

![](https://github.com/DIY-Z/Student-Information-Management-System/blob/master/Readme中的图片/查询界面.png)

### 6. 管理界面（教师模式）

![](https://github.com/DIY-Z/Student-Information-Management-System/blob/master/Readme中的图片/管理界面（教师模式）.png)

### 7. 学生成绩情况柱状图

![](https://github.com/DIY-Z/Student-Information-Management-System/blob/master/Readme中的图片/学生成绩情况柱状图.png)

### 8. 学生成绩情况饼状图

![](https://github.com/DIY-Z/Student-Information-Management-System/blob/master/Readme中的图片/学生成绩情况饼状图.png)

### 9. 课程平均成绩柱状图

![](https://github.com/DIY-Z/Student-Information-Management-System/blob/master/Readme中的图片/课程平均成绩柱状图.png)



