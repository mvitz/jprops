package de.mvitz.jprops.api;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PropertiesTest {

    @Before
    public void setUp() {
        Properties.create(this);
    }

    @Test
    public void create_forPrivateField_injectsValue() throws Exception {
        assertThat(privateField).isEqualTo("foo");
    }

    @Test
    public void create_forProtectedField_injectsValue() throws Exception {
        assertThat(protectedField).isEqualTo("foo");
    }

    @Test
    public void create_forPackageField_injectsValue() throws Exception {
        assertThat(packageField).isEqualTo("foo");
    }

    @Test
    public void create_forPublicField_injectsValue() throws Exception {
        assertThat(publicField).isEqualTo("foo");
    }

    private String privateField;
    protected String protectedField;
    String packageField;
    public String publicField;

}
