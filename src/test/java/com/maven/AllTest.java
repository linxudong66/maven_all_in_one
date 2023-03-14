package com.maven;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.maven.court.dao.BaseDao;
import com.maven.court.entity.Emp;
import com.maven.court.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class AllTest {
   private BaseDao<Emp> baseDao = new BaseDao<>();

   @Test
   public void testGetSingleBean(){

      String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp where emp_id=?";
      Emp emp = baseDao.getSingleBean(sql, Emp.class, 1);
      System.out.println("emp = "+emp);
   }
   @Test
   public void testListBean(){

      String sql = "select emp_id empId,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp";

      List<Emp> empl = baseDao.getBeanList(sql, Emp.class);
      for (Emp emp : empl) {
         System.out.println("emp = " + emp);
      }
   }



   @Test
   public void testUpdate() {

      String sql = "update t_emp set emp_position=? where emp_id=?";

      String empPosition = "minister";
      String empId = "3";

      int affectedRowNumber = baseDao.update(sql, empPosition, empId);

      System.out.println("affectedRowNumber = " + affectedRowNumber);

   }


   @Test
    public void connTest(){
       Connection connection = JDBCUtils.getConnection();
       System.out.println("connection =" + connection );

       JDBCUtils.releaseConnection(connection);
   }

   @Test
   public void testSubString(){
      String substring = "aaa.png".substring("aaa.png".lastIndexOf("."));
      System.out.println("substring = " + substring);
   }

}
