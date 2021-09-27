package org.openmrs.module.etllite.api;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;

public class ETLLiteContextTest extends BaseModuleContextSensitiveTest {

    @Test
    public void shouldSetupContext() {
        Assert.assertNotNull(Context.getAdministrationService());
    }
}
