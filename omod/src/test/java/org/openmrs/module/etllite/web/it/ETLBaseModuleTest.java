package org.openmrs.module.etllite.web.it;

import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;

import java.util.Properties;

public abstract class ETLBaseModuleTest extends BaseModuleWebContextSensitiveTest {

    @Override
    public Properties getRuntimeProperties() {
        Properties props = super.getRuntimeProperties();
        props.setProperty("hibernate.connection.password", "Password");
        props.setProperty("connection.password", "Password");
        return props;
    }
}
