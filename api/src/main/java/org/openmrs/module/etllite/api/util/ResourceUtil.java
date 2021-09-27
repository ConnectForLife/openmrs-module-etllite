package org.openmrs.module.etllite.api.util;

import org.apache.commons.io.IOUtils;
import org.openmrs.module.etllite.api.exception.ETLRuntimeException;

import java.io.IOException;
import java.io.InputStream;

public final class ResourceUtil {

    public static String readResourceFile(String fileName) throws ETLRuntimeException {
        try (InputStream in = ResourceUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (in == null) {
                throw new ETLRuntimeException("Resource '" + fileName + "' doesn't exist");
            }
            return IOUtils.toString(in);
        } catch (IOException e) {
            throw new ETLRuntimeException(e);
        }
    }

    private ResourceUtil() {
    }

}
