package com.blanc.recrute.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnectionFactory {

  private static final Logger logger = Logger.getLogger(MybatisConnectionFactory.class.getName());

  private static ThreadLocal<SqlSession> sqlSessionThreadLocal = ThreadLocal.withInitial(
      () -> null);

  private MybatisConnectionFactory() {
  }

  private static SqlSessionFactory initializeSqlSessionFactory() {

    try {
      String resource = "config/mybatis-config.xml";
      InputStream inputStream = Resources.getResourceAsStream(resource);
      return new SqlSessionFactoryBuilder().build(inputStream);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error!", e);
      throw new RuntimeException("Failed to initialize MyBatis", e);
    }
  }

  public static SqlSession getSqlSession() {
    SqlSession sqlSession = sqlSessionThreadLocal.get();
    if (sqlSession == null) {
      sqlSession = initializeSqlSessionFactory().openSession();
      sqlSessionThreadLocal.set(sqlSession);
    }
    return sqlSession;
  }


  public static void closeSqlSession() {
    SqlSession sqlSession = sqlSessionThreadLocal.get();
    if (sqlSession != null) {
      sqlSessionThreadLocal.remove();
      sqlSession.close();
    }
  }


}
