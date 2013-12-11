package de.mvitz.jprops.core.impl.provider;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;

import org.junit.Test;

public class PropertiesProviderTest {

    @Test
    public void loadingPropertyFileShouldWork() throws Exception {
        final PropertiesProvider provider = PropertiesProvider.properties("/test.properties");
        assertThat(provider.get("abc")).as("existing key 'abc'").isEqualTo("foo");
        assertThat(provider.get("foo")).as("non existing key 'foo'").isNull();
    }

    @Test
    public void loadingPropertyXmlFileShouldWork() throws Exception {
        final PropertiesProvider provider = PropertiesProvider.xml("/properties.xml");
        assertThat(provider.get("abc")).as("existing key 'abc'").isEqualTo("foo");
        assertThat(provider.get("foo")).as("non existing key 'foo'").isNull();
    }

    @Test
    public void givenPropertiesShouldWork() throws Exception {
        final Properties properties = new Properties();
        properties.put("abc", "foo");
        final PropertiesProvider provider = new PropertiesProvider(properties);
        assertThat(provider.get("abc")).as("existing key 'abc'").isEqualTo("foo");
        assertThat(provider.get("foo")).as("non existing key 'foo'").isNull();
    }

}
