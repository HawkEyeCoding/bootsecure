package me.sirius.bootsecure.digitalsignature;

import me.sirius.bootsecure.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

@Component
public class VerifySignature {
    Logger logger = LoggerFactory.getLogger(SignMessage.class);

    public boolean verifyMessage(String signedMessage, String message) {
        boolean isMessageValid = false;
        try {
            PublicKey publicKey = Utils.loadPublicKey();

            Signature signature = Signature.getInstance(Utils.SIGNING_ALGORITHM);
            signature.initVerify(publicKey);

            signature.update(message.getBytes());

            isMessageValid = signature.verify(Base64.getDecoder().decode(signedMessage));
            logger.info("Signature " + (isMessageValid ? "correct" : "incorrect"));
        } catch (Exception exception) {
            logger.error("Error occurred", exception);
        }
        return isMessageValid;
    }
}
