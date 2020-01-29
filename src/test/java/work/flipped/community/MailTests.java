package work.flipped.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import work.flipped.community.util.MailClient;

@SpringBootTest
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTestMail() {
        mailClient.sendMail("1123630646@qq.com","Test", "TestMail");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username","flipped");

        // 生成网页
        String content = templateEngine.process("mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("1123630646@qq.com","HTMLMail", content);
    }
}
