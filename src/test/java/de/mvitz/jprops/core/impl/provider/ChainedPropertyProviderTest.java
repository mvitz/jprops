package de.mvitz.jprops.core.impl.provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.mvitz.jprops.core.api.PropertyProvider;

@RunWith(MockitoJUnitRunner.class)
public class ChainedPropertyProviderTest {

    @Mock
    private PropertyProvider first;
    @Mock
    private PropertyProvider second;
    private ChainedPropertyProvider provider;

    @Before
    public void setUp() {
        provider = new ChainedPropertyProvider(first, second);
    }

    @Test
    public void shouldReturnPropertyFromFirstProviderIfPresent() throws Exception {
        given(first.get("abc")).willReturn("foo");
        assertThat(provider.get("abc")).isEqualTo("foo");
        verifyZeroInteractions(second);
    }

    @Test
    public void shouldReturnPropertyFromSecondProviderIfFristReturnedNull() throws Exception {
        given(second.get("abc")).willReturn("foo");
        assertThat(provider.get("abc")).isEqualTo("foo");
        verify(first).get("abc");
    }

    @Test
    public void shouldReturnNullIfNoProviderHasProperty() throws Exception {
        assertThat(provider.get("abc")).isNull();
        verify(first).get("abc");
        verify(second).get("abc");
    }

}
