import com.google.gson.Gson;
import com.hossi.recrute.common.response.data.Message;
import com.hossi.recrute.common.response.data.MessageCreator;
import com.hossi.recrute.common.response.data.ResponseData;
import com.hossi.recrute.common.response.service.ServicePrefix;
import com.mysql.cj.protocol.x.OkBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.hossi.recrute.common.response.service.ServicePrefix.RCT;

public class MessageCreatorTest {

    @Test
    public void testCreateMessageWithoutData() {
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

    @Test
    public void testCreateMessageWithData() {
        // given
        MessageCreator messageCreator = MessageCreator.getInstance();

        // when
        ResponseData responseData = new ResponseData();
        responseData.set("rctId", 1);
        Message<Map<String, Object>> message = messageCreator.create(RCT, "002", true, "Applied", responseData);
        String resMessage = new Gson().toJson(message);

        // then
        Assertions.assertEquals(resMessage,
            "{\"code\":\"RCT002\","
            + "\"description\":\"채용공고 지원\","
            + "\"result\":{\"success\":true,\"message\":\"Applied\"},"
            + "\"data\":{\"rctId\":1}}");
    }
}
