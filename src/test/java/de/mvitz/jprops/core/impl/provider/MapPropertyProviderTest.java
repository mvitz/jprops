package de.mvitz.jprops.core.impl.provider;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class MapPropertyProviderTest {

    private MapPropertyProvider provider;

    @Before
    public void setUp() {
        final Map<String, String> map = new HashMap<>();
        map.put("abc", "foo");
        provider = new MapPropertyProvider(map);
    }

    @Test
    public void shouldReturnValueForKnownKey() throws Exception {
        assertThat(provider.get("abc")).isEqualTo("foo");
    }

    @Test
    public void shouldReturnNullForUnknownKey() throws Exception {
        assertThat(provider.get("foo")).isNull();
    }

}
