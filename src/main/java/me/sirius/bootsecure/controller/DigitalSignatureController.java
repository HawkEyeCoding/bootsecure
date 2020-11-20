package me.sirius.bootsecure.controller;

import lombok.Data;
import me.sirius.bootsecure.digitalsignature.SignMessage;
import me.sirius.bootsecure.digitalsignature.VerifySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DigitalSignatureController {
    private final SignMessage signing;
    private final VerifySignature verifying;

    @Autowired
    public DigitalSignatureController(SignMessage signing, VerifySignature verifying) {
        this.signing = signing;
        this.verifying = verifying;
    }

    @PostMapping("/sign")
    public ResponseEntity<String> signMessage(@RequestBody Message message) {
        return ResponseEntity.ok(signing.signMessage(message.getPayload()));
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyMessage(@RequestBody Message message) {
        return ResponseEntity.ok(verifying.verifyMessage(message.getChecksum(), message.getPayload()));
    }
}

@Data
class Message {
    private String payload;
    private String checksum;
}