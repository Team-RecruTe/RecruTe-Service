import com.hossi.recrute.common.service.MailSender;
import org.apache.ibatis.io.Resources;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.hossi.recrute.common.service.MailType.GOOGLE;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MailSendTest {
    MailSender mailSender = new MailSender(GOOGLE);
    static Properties properties = new Properties();

    @BeforeAll
    static void setUp() {
        try{
            InputStream mail = Resources.getResourceAsStream("mail.properties");
            properties.load(mail);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSendMail(){
        // given
        String userEmail = properties.get("mail").toString();

        // when
        boolean actual = mailSender.sendEmail(userEmail);

        // then
        assertTrue(actual);
    }
}
