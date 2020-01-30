package work.flipped.community;

import work.flipped.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter() {
        String text = "这里可以⭐赌⭐博，可以⭐嫖⭐娼⭐，可以吸毒，可以开票，，哈哈哈!";
        String s = sensitiveFilter.filter(text);
        System.out.println(s);
    }

}
