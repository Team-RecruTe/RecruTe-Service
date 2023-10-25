import com.google.gson.Gson;
import com.hossi.recrute.common.util.http.message.Message;
import com.hossi.recrute.common.util.http.message.MessageCreator;
import com.hossi.recrute.common.util.http.message.ResponseData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.hossi.recrute.common.util.code.ServicePrefix.RCT;

public class MessageCreatorTest {

    @Test
    public void testCreateMessageWithoutData() {
        // given

        // when
        Message<String> message = MessageCreator.create();
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
        ResponseData responseData = new ResponseData.Builder().set("rctId", 1).build();

        // when
        Message<Map<String, Object>> message = MessageCreator.create(responseData);
        String resMessage = new Gson().toJson(message);

        // then
        Assertions.assertEquals(resMessage,
            "{\"code\":\"RCT002\","
            + "\"description\":\"채용공고 지원\","
            + "\"result\":{\"success\":true,\"message\":\"Applied\"},"
            + "\"data\":{\"rctId\":1}}");
    }
}
