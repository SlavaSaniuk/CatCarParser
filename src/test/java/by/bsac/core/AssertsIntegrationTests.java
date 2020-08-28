package by.bsac.core;

import by.bsac.assertions.Asserts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AssertsIntegrationTests {

    @Test
    void notNull_objectIsNull_shouldThrowRuntimeAssertionException() {

        Assertions.assertThrows(Asserts.RuntimeAssertionException.class, () -> Asserts.notNull(null, ""));

    }

    @Test
    void notNull_objectIsNotNull_shouldDontThrowRuntimeAssertionException() {

        Boolean exception;
        Asserts.notNull("", "");
        exception = false;

        Assertions.assertNotNull(exception);
        Assertions.assertFalse(exception);

    }

}
