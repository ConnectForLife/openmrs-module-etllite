package org.openmrs.module.etllite.api.service.it;

import org.junit.Test;
import org.openmrs.api.APIAuthenticationException;
import org.openmrs.module.etllite.api.dao.MappingDao;
import org.openmrs.module.etllite.api.service.MappingService;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MappingService Integration Test Class
 */
public class MappingServiceBundleUnauthorizedIT extends BaseModuleContextSensitiveTest {

    @Autowired
    private MappingService mappingService;

    @Autowired
    private MappingDao mappingDao;

    @Override
    public void authenticate() {
        // disable default authentication
    }

    @Test(expected = APIAuthenticationException.class)
    public void shouldForbidUnauthorizedUserToFind() {
        mappingService.findAll();
    }
}
