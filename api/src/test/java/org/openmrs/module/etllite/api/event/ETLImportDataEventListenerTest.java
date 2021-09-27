package org.openmrs.module.etllite.api.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openmrs.module.etllite.api.BaseTest;
import org.openmrs.module.etllite.api.constants.Constants;
import org.openmrs.module.etllite.api.service.ETLService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ETLImportDataEventListenerTest extends BaseTest {

    @Mock
    private ETLService etlService;

    @InjectMocks
    private ETLImportDataEventListener etlImportDataEventListener = new ETLImportDataEventListener();

    @Test
    public void shouldReturnProperSubject() {
        assertThat(etlImportDataEventListener.getSubject(), equalTo(Constants.ETL_EVENT_IMPORT_DATA));
    }

    @Test
    public void shouldHandleEvent() {
        List<String> mappings = new ArrayList<>();
        mappings.add("mapping1");
        mappings.add("mapping2");

        Map<String, Object> params = new HashMap<>();
        params.put(Constants.PARAM_MAPPINGS, mappings);

        etlImportDataEventListener.handleEvent(params);

        verify(etlService, times(2)).doETL(anyString(), anyMap());
    }

}
