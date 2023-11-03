import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class EncodingTest {

    @Test
    public void encodeISOtoUTF() {
        System.out.println(new String("í\u0099\u008Dê¸¸ë\u008F\u0099".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }

}
