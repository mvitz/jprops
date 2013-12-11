package de.mvitz.jprops.core.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.mvitz.jprops.core.api.PropertiesInjector;
import de.mvitz.jprops.core.api.PropertyProvider;

public final class FieldScopeTest {

    public String publicField;
    protected String protectedField;
    String packageField;
    private String privateField;

    @Before
    public void setUp() {
        new PropertiesInjector(new PropertyProvider() {
            @Override
            public String get(final String key) {
                switch (key) {
                case "publicField":
                case "protectedField":
                case "packageField":
                case "privateField":
                    return "foo";
                default:
                    return null;
                }
            }
        }).injectInto(this);
    }

    @Test
    public void injectionIntoPublicFieldShouldWork() throws Exception {
        assertThat(publicField).isEqualTo("foo");
    }

    @Test
    public void injectionIntoProtectedFieldShouldWork() throws Exception {
        assertThat(protectedField).isEqualTo("foo");
    }

    @Test
    public void injectionIntoPackageFieldShouldWork() throws Exception {
        assertThat(packageField).isEqualTo("foo");
    }

    @Test
    public void injectionIntoPrivateFieldShouldWork() throws Exception {
        assertThat(privateField).isEqualTo("foo");
    }

}
