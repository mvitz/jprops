package de.mvitz.jprops.core.impl.typeconverter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import de.mvitz.jprops.core.api.InvalidPropertiesException;

public class EnumConverterTest {

    private final EnumConverter<Enum> converter = new EnumConverter<>(Enum.class);

    private enum Enum {
        INSTANCE, FOO_BAR;
    }

    @Test(expected = InvalidPropertiesException.class)
    public void convertingInknownEnumMemberNameShouldThrowException() throws Exception {
        converter.convert("UNKNOWN");
    }

    @Test
    public void convertingShouldReplaceMinusWithHyphen() throws Exception {
        assertThat(converter.convert("foo-bar")).isEqualTo(Enum.FOO_BAR);
    }

    @Test
    public void convertingShouldReplaceLowerToUpperCase() throws Exception {
        assertThat(converter.convert("Instance")).isEqualTo(Enum.INSTANCE);
    }

}
