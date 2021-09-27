package org.openmrs.module.etllite.api.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openmrs.module.etllite.api.BaseTest;
import org.openmrs.module.etllite.api.ETLTestHelper;
import org.openmrs.module.etllite.api.domain.Config;
import org.openmrs.module.etllite.api.util.EncryptionUtil;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConfigBuilderTest extends BaseTest {

    private static final String ENCRYPTED_PW = "Encrypted Password";

    @Mock
    private EncryptionUtil encryptionUtil;

    @InjectMocks
    private ConfigBuilder configBuilder = new ConfigBuilder();

    @Test
    public void shouldCreateConfig() {
        when(encryptionUtil.encryptAsString(ETLTestHelper.ETL_DB_PWD)).thenReturn(ENCRYPTED_PW);

        Config config = configBuilder.createConfig(ETLTestHelper.setUpETLDatabase1(), new Config());

        assertNotNull(config);
        assertThat(config.getName(), equalTo(ETLTestHelper.ETL_DB_NAME));
        assertThat(config.getType(), equalTo(ETLTestHelper.ETL_DB_TYPE));
        assertThat(config.getUrl(), equalTo(ETLTestHelper.ETL_DB_URL));
        assertThat(config.getUser(), equalTo(ETLTestHelper.ETL_DB_USER));
        assertThat(config.getQuery(), equalTo(ETLTestHelper.ETL_TEST_QUERY));
        assertThat(config.getDbPassword(), equalTo(ENCRYPTED_PW));
    }

    @Test
    public void shouldDecryptPassword() {
        when(encryptionUtil.decryptAsString(ENCRYPTED_PW)).thenReturn(ETLTestHelper.ETL_DB_PWD);

        String password = configBuilder.decryptPassword(ENCRYPTED_PW);

        assertThat(password, equalTo(ETLTestHelper.ETL_DB_PWD));
    }

}
