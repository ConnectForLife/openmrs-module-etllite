package org.openmrs.module.etllite.api.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.openmrs.module.etllite.api.BaseTest;
import org.openmrs.module.etllite.api.ETLTestHelper;
import org.openmrs.module.etllite.api.contract.MappingResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Mapping Request Builder Test Class
 *
 * @author nanakapa
 */
@RunWith(MockitoJUnitRunner.class)
public class MappingResponseBuilderTest extends BaseTest {

    private MappingResponseBuilder mappingResponseBuilder = new MappingResponseBuilder();

    @Test
    public void shouldBuildMappingFromMappingRequestBuilder() {
        //Given & When
        MappingResponse mappingResponse = mappingResponseBuilder.createFrom(ETLTestHelper.setUpDbMapping());

        // Then
        assertNotNull(mappingResponse);
        assertThat(mappingResponse.getId(), equalTo(ETLTestHelper.MAPPING_ID));
        assertThat(mappingResponse.getName(), equalTo(ETLTestHelper.MAPPING_NAME));
        assertThat(mappingResponse.getSource(), equalTo(ETLTestHelper.MAPPING_SOURCE));
        assertThat(mappingResponse.getQuery(), equalTo(ETLTestHelper.MAPPING_QUERY));
        assertThat(mappingResponse.getLoadTemplate(), equalTo(ETLTestHelper.MAPPING_LOAD_TEMPLATE));
        assertThat(mappingResponse.getTransformTemplate(), equalTo(ETLTestHelper.MAPPING_TRANSFORM_TEMPLATE));
        assertThat(mappingResponse.getCronExpression(), equalTo(ETLTestHelper.MAPPING_CRON));
    }
}
