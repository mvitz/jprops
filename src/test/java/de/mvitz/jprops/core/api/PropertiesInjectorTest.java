package de.mvitz.jprops.core.api;

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

    @SuppressWarnings("unused")
    private UnknownType unknownType;

    @Test(expected = InvalidPropertiesException.class)
    public void shouldThrowExceptionForUnknownType() throws Exception {
        given(provider.get("unknownType")).willReturn("foo");
        injector.injectInto(this);
    }

    public interface UnknownType {
    }

}
