package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//����JDBC����MySQL���ݿ�
public class DbUtil {
    private String dbUrl="jdbc:mysql://localhost:3306/jabberwocky?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8";
    private String dbUserName="root";
    private String dbPassword="1234567890";
    private String jdbcName="com.mysql.cj.jdbc.Driver";
    //��ȡ���ݿ�����
    public Connection getCon (){
        try{
            Class.forName(jdbcName);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        Connection con=null;
        try {
            con=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    //�ر����ݿ�����
    public void closeCon(Connection con) throws Exception{
        if(con!=null) {
            con.close();
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil=new DbUtil();
        try {
            dbUtil.getCon();
            System.out.println("���ݿ����ӳɹ���");
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("���ݿ�����ʧ�ܣ�");
        }
    }
}
