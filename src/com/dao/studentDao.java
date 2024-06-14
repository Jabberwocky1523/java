package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.lesson;
import com.bean.student;

public class studentDao extends lessonDao {
    public List<student> getStudent() {
        String sql = "select * from student";
        List<student> ans = new ArrayList<>();
        try {
            PreparedStatement prst = con.prepareStatement(sql);
            ResultSet executeQuery = prst.executeQuery();
            while (executeQuery.next()) {
                student temp = new student();
                List<lesson> lessons = getlesson();
                temp.setid(executeQuery.getInt("id"));
                temp.setname(executeQuery.getString("name"));
                for (lesson lesson : lessons) {
                    lesson cur = lesson;
                    cur.setscore(executeQuery.getInt(lesson.getname()));
                    temp.getLessons().add(cur);
                }
                ans.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public student findByid(int id) {
        String sql = "select * from student where id = ?";
        student ans = new student();
        try {
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setInt(1, id);
            ResultSet executeQuery = prst.executeQuery();
            if (executeQuery.next()) {
                ans.setid(executeQuery.getInt("id"));
                ans.setname(executeQuery.getString("name"));
                List<lesson> lessons = getlesson();
                for (lesson lesson : lessons) {
                    lesson cur = lesson;
                    cur.setscore(executeQuery.getInt(lesson.getname()));
                    ans.getLessons().add(cur);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public int addstudent(student student) {
        int temp = 0;
        String sql = "insert into student (id,name) values (?,?)";
        try {
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setInt(1, student.getid());
            prst.setString(2, student.getname());
            int a = prst.executeUpdate();
            if (a != 0) {
                temp = a;
            }
            for (lesson lesson : student.getLessons()) {
                sql = "update student set " + lesson.getname() + " = " + lesson.getscore() + " where id = "
                        + student.getid();
                prst = con.prepareStatement(sql);
                a = prst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int deletestudent(student student) {
        int temp = 0;
        String sql = "DELETE from student where id = ?";
        try {
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setInt(1, student.getid());
            int a = prst.executeUpdate();
            if (a != 0) {
                temp = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int updateStudent(student student) {
        int temp = 0;
        String sql = "Update student SET name = ? where id = ?";
        try {
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setInt(2, student.getid());
            prst.setString(1, student.getname());
            int a = prst.executeUpdate();
            if (a != 0) {
                temp = a;
            }
            for (lesson lesson : student.getLessons()) {
                sql = "update student set " + lesson.getname() + " = " + lesson.getscore() + " where id = "
                        + student.getid();
                prst = con.prepareStatement(sql);
                a = prst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int addColoumn(lesson lesson) {
        int temp = 0;
        String sql = "alter table student add " + lesson.getname() + " int default 0";
        try {
            PreparedStatement prst = con.prepareStatement(sql);
            int a = prst.executeUpdate();
            temp = a;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int deleteColoumn(lesson lesson) {
        int temp = 0;
        String sql = "alter table student drop " + lesson.getname();
        try {
            PreparedStatement prst = con.prepareStatement(sql);
            int a = prst.executeUpdate();
            temp = a;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int updateColoumn(lesson lesson, lesson nlesson) {
        int temp = 0;
        String sql = "alter table student change " + lesson.getname() + " " + nlesson.getname() + " int default 0";
        try {
            PreparedStatement prst = con.prepareStatement(sql);
            int a = prst.executeUpdate();
            temp = a;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
