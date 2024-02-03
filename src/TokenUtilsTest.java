import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenUtilsTest {
    private String validToken;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

         validToken = TokenUtils.generateToken("Danny");
    }

    @Test
    public void verify_validToken_Test(){
        String verification = TokenUtils.verifyToken(validToken);
        assertEquals("verification pass",verification);
    }

    @Test
    public void verify_InvalidToken_Test(){
        String invalidToken = "000hshsuksksls";
        String verification = TokenUtils.verifyToken(invalidToken);
        assertEquals("verification fails",verification);
    }
}