package org.openmrs.module.etllite.api.contract;

/**
 * Mapping Request Class
 *
 * @author nanakapa
 */
public class MappingRequest {

    private String name;

    private String query;

    private String cronExpression;

    private String loadTemplate;

    private String source;

    private String transformTemplate;

    private int fetchSize;

    private int testResultsSize;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getLoadTemplate() {
        return loadTemplate;
    }

    public void setLoadTemplate(String loadTemplate) {
        this.loadTemplate = loadTemplate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTransformTemplate() {
        return transformTemplate;
    }

    public void setTransformTemplate(String transformTemplate) {
        this.transformTemplate = transformTemplate;
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public int getTestResultsSize() {
        return testResultsSize;
    }

    public void setTestResultsSize(int testResultsSize) {
        this.testResultsSize = testResultsSize;
    }
}
