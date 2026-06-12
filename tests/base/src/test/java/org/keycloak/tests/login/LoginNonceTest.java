package org.keycloak.tests.login;

import org.keycloak.testframework.annotations.InjectRealm;
import org.keycloak.testframework.annotations.KeycloakIntegrationTest;
import org.keycloak.testframework.injection.LifeCycle;
import org.keycloak.testframework.oauth.OAuthClient;
import org.keycloak.testframework.oauth.annotations.InjectOAuthClient;
import org.keycloak.testframework.realm.ManagedRealm;
import org.keycloak.testframework.ui.annotations.InjectPage;
import org.keycloak.testframework.ui.page.LoginPage;
import org.keycloak.tests.model.CustomProvidersServerConfig;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@KeycloakIntegrationTest(config = CustomProvidersServerConfig.class)
public class LoginNonceTest {

    @InjectRealm(lifecycle = LifeCycle.METHOD)
    protected ManagedRealm realm;

    @InjectOAuthClient
    OAuthClient oauth;

    @InjectPage
    protected LoginPage loginPage;

    @Test
    public void testTemplateContainsNoncesInScriptTags() {
        oauth.openLoginForm();
        loginPage.assertCurrent();

        var inlineScriptsWithoutNonce = loginPage.getInlineScriptsWithoutNonce();

        assertTrue(
                inlineScriptsWithoutNonce.isEmpty(),
                String.format("Page contains %d scripts without nonce: %s",
                        inlineScriptsWithoutNonce.size(),
                        inlineScriptsWithoutNonce
                )
        );
    }
}
