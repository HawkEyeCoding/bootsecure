package me.sirius.bootsecure;

import me.sirius.bootsecure.digitalsignature.SignMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

@SpringBootTest
public class SignMessageTest {
    @Autowired
    private SignMessage signMessage;

    @Test
    public void testVerifyMessage() {
        String signatureTest = "pJmu8n7Xi/wh+YIIXYwHJqrPOrpN2+Yo6RjvaSJ4IoqkHWz3Oc0CIhLc6+kQO+s9ngwo9XtQBUH+rneIyHJP" +
                "D8vBBjveR5UQGXfewohVpJTPcPh0Jmf0Bsz358tIITBjsqWTAibySg348+4rLaEDhCRALWNlh3E3mHRG79jhbt9ymPKCFrmC" +
                "suwB+JyVu4UiaZTIBMlQa63ZG0pTroabzwFaJVrEo2Y8iAm9uBSU2fCXyoYBAzxv0ZM+IDtghkm0NpLkmSm61YudLlguYlUWfTIu" +
                "PpSmRotsXdmma03ffwrLolEeCTdFvharvxavTDAh4FMJlpN+gdUDhyyqBvUtfQ==";
        String signature = signMessage.signMessage("this is a private message");
        System.out.println(signature);
        assertEquals("Messages are correct", signatureTest, signature);
    }


    @Test
    public void testVerifyMessageNotEqual() {
        String signatureTest = "pJm3ZG0pTroabzwFaJVrEo2Y8iAm9uBSU2fCXyoYBAzxv0ZM+IDtghkm0NpLkmSm61YudLlguYlUWfTIu" +
                "PpSmRotsXdmma03ffwrLolEeCTdFvharvxavTDAh4FMJlpN+gdUDhyyqBvUtfQ==";
        String signature = signMessage.signMessage("this is a private message");
        System.out.println(signature);
        assertNotEquals("Messages are not correct", signatureTest, signature);
    }
}
