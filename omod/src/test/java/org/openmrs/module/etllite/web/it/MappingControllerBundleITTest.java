package org.openmrs.module.etllite.web.it;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.etllite.api.ETLTestHelper;
import org.openmrs.module.etllite.api.contract.ConfigRequestWrapper;
import org.openmrs.module.etllite.api.contract.MappingRequest;
import org.openmrs.module.etllite.api.dao.MappingDao;
import org.openmrs.module.etllite.api.domain.Config;
import org.openmrs.module.etllite.api.domain.Mapping;
import org.openmrs.module.etllite.api.exception.MappingAlreadyExistsException;
import org.openmrs.module.etllite.api.exception.MappingNotFoundException;
import org.openmrs.module.etllite.api.service.ConfigService;
import org.openmrs.module.etllite.api.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Mapping Controller Integration Test Class
 *
 * @author nanakapa
 */
@WebAppConfiguration
public class MappingControllerBundleITTest extends ETLBaseModuleTest {

    private static final String MAPPINGS_URL = "/etllite/mappings/";

    private MockMvc mockMvc;

    private Mapping mapping;

    private MappingRequest mappingRequest;

    private Config config;

    @Autowired
    private ConfigService configService;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private MappingDao mappingDao;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws IOException, InterruptedException {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mapping = ETLTestHelper.setUpNewMapping();
        mappingRequest = ETLTestHelper.setUpMappingRequest();

        setUpDatabase();
        createETLDatabase();
    }

    @Test
    public void shouldCreateMappingAndReturnNewMappingAsJson() throws Exception {
        mockMvc.perform(post(MAPPINGS_URL).contentType(MediaType.parseMediaType(ETLTestHelper.APPLICATION_JSON_UTF8))
                .content(json(mappingRequest).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(ETLTestHelper.APPLICATION_JSON_UTF8));
    }

    @Test
    public void shouldUpdateMappingAndReturnUpdatedMappingAsJson() throws Exception {
        //Given
        Mapping dbMapping = createMapping();
        dbMapping.setLoadTemplate("updated template");

        mockMvc.perform(put(MAPPINGS_URL + dbMapping.getId()).contentType(MediaType.parseMediaType(ETLTestHelper.APPLICATION_JSON_UTF8))
                .content(json(mappingRequest).getBytes(StandardCharsets.UTF_8)))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldDeleteMapping() throws Exception {
        //Given
        Mapping dbMapping = createMapping();

        mockMvc.perform(delete(MAPPINGS_URL + dbMapping.getId(), dbMapping.getId()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldReturnMappingAsJsonForTheSpecifiedMapping() throws Exception {
        //Given
        Mapping dbMapping = createMapping();

        mockMvc.perform(get(MAPPINGS_URL)
                .param("lookup", "By Id")
                .param("id", dbMapping.getId().toString()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldReturnAllMappingForASpecifiedETLSourceAsJson() throws Exception {
        //Given
        Mapping dbMapping = createMapping();

        mockMvc.perform(get(MAPPINGS_URL)
                .param("lookup", "By Source")
                .param("source", dbMapping.getSource()))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldReturnStatusOkWhenTestMappingIsInvoked() throws Exception {
        //Given
        Mapping dbMapping = createTestMapping();

        mockMvc.perform(get(MAPPINGS_URL + dbMapping.getId() + "/test"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @After
    public void cleanDataBase() {
        mappingDao.deleteAll();
    }

    private Mapping createMapping() throws MappingAlreadyExistsException, MappingNotFoundException {
        return mappingService.create(ETLTestHelper.setUpNewMapping());
    }

    private Mapping createTestMapping() throws MappingAlreadyExistsException, MappingNotFoundException {
        return mappingService.create(ETLTestHelper.setUpTestMapping());
    }

    private void createETLDatabase() {
        List<Config> configs = new ArrayList<>();
        configs.add(config);
        ConfigRequestWrapper configRequestWrapper = new ConfigRequestWrapper();
        configRequestWrapper.setDatabases(configs);
        configRequestWrapper.setServices(ETLTestHelper.SERVICES);
        configService.createOrUpdateConfigs(configRequestWrapper);
    }

    private void setUpDatabase() {
        config = new Config();
        config.setName(ETLTestHelper.ETL_DB_NAME);
        config.setType(ETLTestHelper.ETL_DB_TYPE);
        config.setQuery(ETLTestHelper.ETL_TEST_QUERY);
        config.setUser(ETLTestHelper.H2_TEST_DB_USER);
        config.setDbPassword(ETLTestHelper.H2_TEST_DB_PASSWORD);
        config.setUrl(ETLTestHelper.H2_TEST_DB_URL);
    }

    private String json(Object obj) throws IOException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
