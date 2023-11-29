import com.hossi.recrute.common.service.code.ServiceCode;
import com.hossi.recrute.common.service.code.ServiceCodeManager;
import com.hossi.recrute.common.service.code.ServicePrefix;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static com.hossi.recrute.common.service.code.MemberCode.MBR001;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicePrefixTest {
    private final ServiceCodeManager serviceCodeManager = ServiceCodeManager.getInstance();

    @Test
    public void testServiceCode() throws UnsupportedEncodingException {
        // given
        ServicePrefix servicePrefix = ServicePrefix.MBR;
        String serviceNumber = "001";

        // when
        ServiceCode serviceCode = serviceCodeManager.getServiceCode(servicePrefix, serviceNumber);

        // then
        assertEquals(serviceCode, MBR001);
        assertEquals(serviceCode.getDescription(), "회원가입 페이지 조회");

    }
}
