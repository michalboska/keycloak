package org.keycloak.forms.login.freemarker.model;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author <a href="mailto:michal@boska.me">Michal Boska</a>
 * @version $Revision: 1 $
 */
public class NonceBean {

    private static final int NONCE_LENGTH_BYTES = 32;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final String value;

    public NonceBean() {
        var bytes = new byte[NONCE_LENGTH_BYTES];
        SECURE_RANDOM.nextBytes(bytes);
        value = Base64.getEncoder().encodeToString(bytes);
    }

    public NonceBean(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
