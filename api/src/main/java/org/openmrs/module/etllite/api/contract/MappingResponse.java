package org.openmrs.module.etllite.api.contract;

/**
 * Mapping Response Class
 *
 * @author nanakapa
 */
public class MappingResponse {

    private Integer id;

    private String source;

    private String loadTemplate;

    private String transformTemplate;

    private String name;

    private String cronExpression;

    private String query;

    private int fetchSize;

    private int testResultsSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLoadTemplate() {
        return loadTemplate;
    }

    public void setLoadTemplate(String loadTemplate) {
        this.loadTemplate = loadTemplate;
    }

    public String getTransformTemplate() {
        return transformTemplate;
    }

    public void setTransformTemplate(String transformTemplate) {
        this.transformTemplate = transformTemplate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
