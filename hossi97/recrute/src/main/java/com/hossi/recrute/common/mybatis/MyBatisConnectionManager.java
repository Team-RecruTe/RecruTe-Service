package com.hossi.recrute.common.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class MyBatisConnectionManager {
    private static final Logger logger = Logger.getLogger(MyBatisConnectionManager.class.getName());
    private static final SqlSessionFactory sqlSessionFactory;

    private MyBatisConnectionManager() {
    }

    static {
        try {
            String resource = "config/myBatisConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(false);
    }

    public static void closeSqlSession(SqlSession sqlSession) {
        sqlSession.close();
    }
}
