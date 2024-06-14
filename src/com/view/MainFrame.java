/*
 * Created by JFormDesigner on Wed Jun 12 21:42:31 HKT 2024
 */

package com.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.bean.lesson;
import com.bean.student;
import com.service.lessonSerive;
import com.service.lessonSerivelmpl;
import com.service.studentSerive;
import com.service.studentSerivelmpl;

import java.util.List;
import java.util.Vector;

/**
 * @author Kurisu
 */
public class MainFrame extends JFrame {
    public lessonSerive serive = new lessonSerivelmpl();
    public studentSerive studentSerive = new studentSerivelmpl();
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JTable staffListTable;
    private JScrollPane jscrollpane;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("高校成绩管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭窗口
        setBounds(200, 20, 1200, 700);// 设置页面大小
        setResizable(false);// 设置页面不可拖拽改变大小
        getContentPane().setLayout(null);
        setVisible(true);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        StuInfo();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public void StuInfo() {
        List<lesson> lessonList = serive.getLessons();
        String[] header = new String[lessonList.size() + 2];
        header[0] = "id";
        header[1] = "姓名";
        int i = 2;
        for (lesson cur : lessonList) {
            header[i] = cur.getname();
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(header, 0);
        staffListTable = new JTable(model);
        jscrollpane = new JScrollPane(staffListTable);
        jscrollpane.setBounds(10, 50, 800, 600);
        getContentPane().add(jscrollpane);
        setstuinfo(new student());
    }

    public void setstuinfo(student student) {
        DefaultTableModel model = (DefaultTableModel) staffListTable.getModel();
        model.setRowCount(0);
        List<student> students = studentSerive.getStudents();
        for (student curstu : students) {
            Vector v = new Vector<>();
            v.add(curstu.getid());
            v.add(curstu.getname());
            for (lesson curLesson : curstu.getLessons()) {
                v.add(curLesson.getscore());
            }
            model.addRow(v);
        }
    }
}
