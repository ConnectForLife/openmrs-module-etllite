package org.openmrs.module.etllite.api.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openmrs.module.etllite.api.BaseTest;
import org.openmrs.module.etllite.api.constants.Constants;
import org.openmrs.module.etllite.api.service.ETLService;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ETLJobEventListenerTest extends BaseTest {

    @Mock
    private ETLService etlService;

    @InjectMocks
    private ETLJobEventListener etlJobEventListener = new ETLJobEventListener();

    @Test
    public void shouldReturnProperSubject() {
        assertThat(etlJobEventListener.getSubject(), equalTo(Constants.SUBJECT_RUNNER));
    }

    @Test
    public void shouldHandleEvent() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Constants.PARAM_MAPPING, "mapping");
        etlJobEventListener.handleEvent(properties);

        verify(etlService).doETL(eq("mapping"), eq(properties));
    }
}
