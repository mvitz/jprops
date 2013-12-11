package de.mvitz.jprops.core.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesInjectorTest {

    @Mock
    private PropertyProvider provider;
    @InjectMocks
    private PropertiesInjector injector;

    private UnknownType unknownType;

    @Test
    public void shouldThrowExceptionForUnknownType() throws Exception {
        given(provider.get("unknownType")).willReturn("foo");
        try {
            injector.injectInto(this);
            failBecauseExceptionWasNotThrown(InvalidPropertiesException.class);
        } catch (final InvalidPropertiesException e) {
            assertThat(e).hasNoCause().hasMessage("No converter found for type: UnknownType");
            assertThat(unknownType).isNull();
        }
    }

    public interface UnknownType {
    }

}
