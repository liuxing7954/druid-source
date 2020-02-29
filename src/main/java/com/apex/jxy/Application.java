package com.apex.jxy;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.IOException;
import java.util.Properties;

public class Application {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(Application.class.getClassLoader().getResourceAsStream("application.yml"));
        DruidDataSource ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        for (int i = 0; i < 20; i++) {
            DruidPooledConnection connection = ds.getConnection();
            try{
                System.out.println("获取到连接: " + connection.getConnection().hashCode());
                boolean execute = connection.createStatement().execute("select 1");
                System.out.println("执行结果: " + execute);
            }finally {
                connection.close();
            }
        }

    }
}
