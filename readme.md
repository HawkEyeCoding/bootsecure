**Installations**

Create JKS certificate using below command (keytool is a tool which comes with JDK installation):

`keytool -genkeypair -alias senderKeyPair -keyalg RSA -keysize 2048 -dname "CN=Mahendra" -validity 3650 -keystore sender_keystore.jks -storepass changeit`

this will create JKS java specific self signed certificate.

Now you need to export our public key from pair of public-private keys to self signed certificate using below command:

`keytool -exportcert -alias senderKeyPair -storetype -keystore sender_keystore.jks -file sender_certificate_jks.cer -rfc -storepass changeit`

this sender_certificate_jks.cer certificate can be published to clients.

Load the public key into Keystore using the importcert command as below:

`keytool -importcert -alias receiverKeyPair -storetype -keystore receiver_keystore.jks -file sender_certificate_jks.cer -rfc -storepass changeit`

-------
Start the application using below command:
`mvn spring-boot:run`

**URL to access API end points**:

Signing a message:
`POST http://127.0.0.1:8080/sign 
{
  "payload": "content is private"
}`

Verifying at receiver end:
`POST http://127.0.0.1:8080/verify
{
  "payload": "content is private",
  "checksum": "LHPj9JJMv199w/cNbpAlID2Y10qXxfmhaEt/KcA3K/z/8MZQ8NiMBmHtSWo9vFR2oPMx7ZAMaGAa9dk2G7xrVqYDXi78HIbHY2RQ3hYGvO23QJnw4i3gKlvE0bgK5sOks9B7sRhcduaQS6ZmFQAJOpyXicvyBrMe93kQEl34RTjNVE1cD1VrfhzkRgVM+qRcZZd/pQm9UiQje3NBEXPzMxdwpm7xSSKuTQ689jbF2TVW9HrJpt0V5iktxZWIem0Tq/5XndwnSaWYnCNTBdTONL97NTJLgOYnIYwn15HRFmEVEDwoTrLUfkTxL3GTaWWp62LfCsKJ+Mc92dEqLK61Kw=="
}`

Note: checksum is the digitally signed message received as the response of signing API.

