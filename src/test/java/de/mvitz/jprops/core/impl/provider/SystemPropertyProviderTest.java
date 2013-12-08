package de.mvitz.jprops.core.impl.provider;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class SystemPropertyProviderTest {

    @Test
    public void nonPrefixProviderShouldUseGivenKey() throws Exception {
        System.setProperty("abc", "foo");
        try {
            assertThat(new SystemPropertyProvider().get("abc")).isEqualTo("foo");
        } finally {
            System.clearProperty("abc");
        }
    }

    @Test
    public void prefixProviderShouldUseGivenPrefixAndKey() throws Exception {
        System.setProperty("abc", "foo");
        try {
            assertThat(new SystemPropertyProvider("ab").get("c")).isEqualTo("foo");
        } finally {
            System.clearProperty("abc");
        }
    }

}
