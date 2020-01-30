package work.flipped.community;

import work.flipped.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTestMail() {
        mailClient.sendMail("704681371@qq.com","Test", "Test Again");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username","sunday");

        // 生成网页
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("704681371@qq.com","HTML Test", content);
    }
}
