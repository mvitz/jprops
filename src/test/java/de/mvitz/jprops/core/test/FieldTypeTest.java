package de.mvitz.jprops.core.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.mvitz.jprops.core.api.PropertiesInjector;
import de.mvitz.jprops.core.api.PropertyProvider;

public class FieldTypeTest {

    @Before
    public void setUp() {
        new PropertiesInjector(new PropertyProvider() {
            @Override
            public String get(final String key) {
                switch (key) {
                case "booleanField":
                case "booleanWrapperField":
                case "intField":
                case "intWrapperField":
                case "stringField":
                    return "1";
                case "enumField":
                    return "An-InstAnce";
                default:
                    return null;
                }
            }
        }).injectInto(this);
    }

    private String stringField;

    @Test
    public void injectingStringShouldWork() throws Exception {
        assertThat(stringField).isEqualTo("1");
    }

    private int intField;
    private Integer intWrapperField;

    @Test
    public void injectingIntShouldWork() throws Exception {
        assertThat(intField).as("int").isEqualTo(1);
        assertThat(intWrapperField).as("Integer").isEqualTo(1);
    }

    private boolean booleanField;
    private Boolean booleanWrapperField;

    @Test
    public void injectingBooleanShouldWork() throws Exception {
        assertThat(booleanField).as("boolean").isEqualTo(true);
        assertThat(booleanWrapperField).as("Boolean").isEqualTo(true);
    }

    public static enum Enum {
        AN_INSTANCE;
    }

    private Enum enumField;

    @Test
    public void injectingEnumShouldWork() throws Exception {
        assertThat(enumField).isEqualTo(Enum.AN_INSTANCE);
    }

}
