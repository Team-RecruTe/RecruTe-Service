import com.hossi.recrute.common.mybatis.MyBatisConnectionManager;
import com.hossi.recrute.member.dto.request.CheckDupReqDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class EncodingTest {

    static int num = 1;

    static class Encoding extends EncodingTest {
        public void setNum() {
            num = 3;
        }
        public void getNum() {
            System.out.println(num);
        }
    }

    @Test
    public void encodeISOtoUTF() {
        System.out.println(new String("í\u0099\u008Dê¸¸ë\u008F\u0099".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));

        Encoding encoding1 = new Encoding();
        Encoding encoding2 = new Encoding();

        encoding1.setNum();

        encoding1.getNum();
        encoding2.getNum();
    }

    @Test
    public void multi() {
        MyRunnable task = new MyRunnable();
        for (int i = 0; i < 1; i++) {
            new Thread(task).start();
        }
    }

    static class MyRunnable implements Runnable {
        public void run() {
            // 병렬로 실행될 코드
            CheckDupReqDto checkDupReqDto = new CheckDupReqDto("username");

            try (SqlSession sqlSession = MyBatisConnectionManager.getSqlSession()) {
                System.out.println(1);

                sqlSession.selectOne("selectCountByUsername", checkDupReqDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
