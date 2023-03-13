package com.maven.court.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 功能一:重数据源获取数据库的链接
 * 功能二：将数据源绑定到本地线程
 * 功能三：释放线程时和本地线程解除绑定
 */
public class JDBCUtils {
    /**
     * 数据源成员变量设置为静态资源，保证大对象的单例性，同时保证静态方法中可以访问
     */
    private static DataSource dataSource;
    /**
     * 由于 ThreadLocal 对象需要作为绑定数据时 k-v 对中的 key，所以要保证唯一性
     * 加 static 声明为静态资源即可保证唯一性
     */

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<java.sql.Connection>();
    private static Connection connection;

    //在静态代码块中初始化数据源
    static {
        try {
            //操作的分析
            //从 jdbc.properties 文件中读取到数据库的链接
            //为了保证程序代码的可移植性，需要基于一个确定的基准来读取这个文件
            //确定的基准：类路径的根目录。resource 目录下的类容经过构件中的打包操作后会确定放在WEB-INF/classes 目录下
            //WEB-INF/classes 目录存放编译好的 *.class 字节码文件，所以这个目录我们就称之为类路径。
            //类路径无论在本地或服务器运行都是确定的一个基准。
            //操作代码
            //1、获取当前类的类加载器
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();

            //2、通过类加载器对象从类路径根目录下读取文件
            InputStream stream = classLoader.getResourceAsStream("jdbc.properties");

            //3、使用 properties 类分装属性文件中的数据
            Properties properties = new Properties();
            properties.load(stream);

            //4、根据 properties 对象来创建数据源对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *数据库链接
     *
     */
    public static java.sql.Connection getConnection(){
        Connection connection = null;

        try {
            //1、尝试从当前线程检查是否存在已绑定的Connection 对象
             connection = threadLocal.get();
            //2、检查 Connection 对象是否为null
            if(connection == null){
                //3、如果为null,则从数据源获取数据库链接
                connection = dataSource.getConnection();
                //4、获取到数据库链接后绑定到当前线程
                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return connection;
    }

    /**
     * 数据库链接释放
     *
     */
    public static void releaseConnection(java.sql.Connection connection){
        JDBCUtils.connection = connection;
        if(connection != null ){

            try {
                //在数据库链接池中将当前链接对象标记为空闲
                connection.close();

                //将当前数据库链接从当前线程上移除
                threadLocal.remove();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        }

    }


}
