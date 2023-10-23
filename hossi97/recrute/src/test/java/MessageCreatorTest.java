import com.google.gson.Gson;
import com.hossi.recrute.common.response.data.Message;
import com.hossi.recrute.common.response.data.MessageCreator;
import com.hossi.recrute.common.response.service.ServicePrefix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.hossi.recrute.common.response.service.ServicePrefix.RCT;

public class MessageCreatorTest {

    @Test
    public void testCreateMessage() {
        // given
        MessageCreator messageCreator = MessageCreator.getInstance();

        // when
        Message<String> message = messageCreator.create(RCT, "001", true, "Invalid");
        String resMessage = new Gson().toJson(message);

        // then
        Assertions.assertEquals(resMessage,
            "{\"code\":\"RCT001\","
            + "\"description\":\"채용공고 상세 페이지 조회\","
            + "\"result\":{\"success\":true,\"message\":\"Invalid\"},"
            + "\"data\":\"\"}");
    }
}
