

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TokenUtils {

//    private static final String SECRET_KEY = "secretKey";


    public static String generateToken(String userName){
        String secretKey = "1LyJmUyvWc2rAtn+8pCv7lG9XdTqN9tBFlb/PV4upOM";
        long expirationTimeMillis = 86400000;

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userName);
        claims.put("iat", System.currentTimeMillis());
        claims.put("exp", System.currentTimeMillis() + expirationTimeMillis);

        String header = encodeBase64("{\"alg\":\"HS256\",\"typ\":\"JWT\"}");
        String payload = encodeBase64(mapToJson(claims));

        String signature = generateHmacSHA256(header + "." + payload, secretKey);

        return header + "." + payload + "." + signature;
    }

    public static String verifyToken(String token) {
        String secretKey = "1LyJmUyvWc2rAtn+8pCv7lG9XdTqN9tBFlb/PV4upOM";

        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return "verification fails";
        }

        String header = parts[0];
        String payload = parts[1];
        String providedSignature = parts[2];

        String calculatedSignature = generateHmacSHA256(header + "." + payload, secretKey);

        if (providedSignature.equals(calculatedSignature)) {
            return "verification pass";
        } else {
            return "verification fails";
        }
    }

    private static String encodeBase64(String input) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String generateHmacSHA256(String data, String secretKey) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashedSecret = sha256.digest(secretKey.getBytes(StandardCharsets.UTF_8));

            return encodeBase64(new String(hmacSha256(data, hashedSecret)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static byte[] hmacSha256(String data, byte[] key) throws Exception {
        javax.crypto.Mac sha256_HMAC = javax.crypto.Mac.getInstance("HmacSHA256");
        javax.crypto.spec.SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(key, "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
    }

    private static String mapToJson(Map<String, Object> map) {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            result.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("}");
        return result.toString();
    }


    }

