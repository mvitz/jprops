package de.mvitz.jprops.core.impl.typeconverter;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.mvitz.jprops.core.api.InvalidPropertiesException;

@RunWith(Parameterized.class)
public class BooleanConverterTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "true", true }, { "True", true }, { "TRUE", true },
                { "yes", true }, { "Yes", true }, { "YES", true }, { "on", true }, { "On", true },
                { "ON", true }, { "1", true }, { "false", false }, { "False", false },
                { "FALSE", false }, { "no", false }, { "No", false }, { "NO", false },
                { "off", false }, { "Off", false }, { "OFF", false }, { "0", false } });
    }

    private final BooleanConverter converter = new BooleanConverter();

    private final String value;
    private final boolean expected;

    public BooleanConverterTest(final String value, final boolean expected) {
        this.value = value;
        this.expected = expected;
    }

    @Test
    public void givenValueShouldEvaluateCorrect() throws Exception {
        assertThat(converter.convert(value)).as("Converting " + value).isEqualTo(expected);
    }

    @Test(expected = InvalidPropertiesException.class)
    public void unknownValueThrowsException() throws Exception {
        converter.convert("2");
    }

}
