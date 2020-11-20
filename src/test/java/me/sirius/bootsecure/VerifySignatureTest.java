package me.sirius.bootsecure;

import me.sirius.bootsecure.digitalsignature.VerifySignature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class VerifySignatureTest {

    @Autowired
    private VerifySignature verify;

    String signatureTest = "pJmu8n7Xi/wh+YIIXYwHJqrPOrpN2+Yo6RjvaSJ4IoqkHWz3Oc0CIhLc6+kQO+s9ngwo9XtQBUH+rneIyHJP" +
            "D8vBBjveR5UQGXfewohVpJTPcPh0Jmf0Bsz358tIITBjsqWTAibySg348+4rLaEDhCRALWNlh3E3mHRG79jhbt9ymPKCFrmC" +
            "suwB+JyVu4UiaZTIBMlQa63ZG0pTroabzwFaJVrEo2Y8iAm9uBSU2fCXyoYBAzxv0ZM+IDtghkm0NpLkmSm61YudLlguYlUWfTIu" +
            "PpSmRotsXdmma03ffwrLolEeCTdFvharvxavTDAh4FMJlpN+gdUDhyyqBvUtfQ==";

    @Test
    public void testVerifyMessage() {
        boolean verificationStatus = verify.verifyMessage(signatureTest, "this is a private message");
        assertTrue("Doesn't match", verificationStatus);
    }

    @Test
    public void testVerifyMessage_False() {
        boolean verificationStatus = verify.verifyMessage("asdasd", "this is a private message");
        assertFalse("Doesn't match", verificationStatus);
    }
}
