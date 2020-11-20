package me.sirius.bootsecure.digitalsignature;

import me.sirius.bootsecure.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Arrays;
import java.util.Base64;

@Component
public class SignMessage {
    Logger logger = LoggerFactory.getLogger(SignMessage.class);

    public String signMessage(String message) {
        byte[] digitalSignature = {};
        try {
            PrivateKey privateKey = Utils.loadPrivateKey();

            Signature signature = Signature.getInstance(Utils.SIGNING_ALGORITHM);
            signature.initSign(privateKey);

            signature.update(message.getBytes());
            digitalSignature = signature.sign();
            logger.info(Arrays.toString(Base64.getEncoder().encode(digitalSignature)));
        } catch (Exception exception) {
            logger.error("Error occurred", exception);
        }
        return new String(Base64.getEncoder().encode(digitalSignature));
    }
}