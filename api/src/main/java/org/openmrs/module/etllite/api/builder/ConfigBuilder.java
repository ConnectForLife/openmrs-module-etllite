package org.openmrs.module.etllite.api.builder;

import org.apache.commons.lang.StringUtils;
import org.openmrs.module.etllite.api.domain.Config;
import org.openmrs.module.etllite.api.util.EncryptionUtil;

/**
 * Builder class for Database configurations
 *
 * @author nanakapa
 */
public class ConfigBuilder {

    private EncryptionUtil encryptionUtil;

    /**
     * Encrypts the database password and creates the Config object from Config request object.
     *
     * @param configRequest config object from request
     * @param newConfig     config to be added or updated
     * @return Updated Config object
     */
    public Config createConfig(Config configRequest, Config newConfig) {
        newConfig.setName(configRequest.getName());
        newConfig.setType(configRequest.getType());
        newConfig.setUrl(configRequest.getUrl());
        newConfig.setUser(configRequest.getUser());
        if (StringUtils.isNotEmpty(configRequest.getDbPassword())) {
            newConfig.setDbPassword(encryptionUtil.encryptAsString(configRequest.getDbPassword()));
        }
        newConfig.setQuery(configRequest.getQuery());
        return newConfig;
    }

    /**
     * Decrypts the database password
     *
     * @param dbPassword encrypted database password
     * @return decrypted database password
     */
    public String decryptPassword(String dbPassword) {
        String decryptedPassword = null;

        if (StringUtils.isEmpty(dbPassword)) {
            decryptedPassword = "";
        } else {
            decryptedPassword = encryptionUtil.decryptAsString(dbPassword);
        }
        return decryptedPassword;
    }

    public void setEncryptionUtil(EncryptionUtil encryptionUtil) {
        this.encryptionUtil = encryptionUtil;
    }
}
