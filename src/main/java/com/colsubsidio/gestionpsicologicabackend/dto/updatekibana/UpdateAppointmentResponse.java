
package com.colsubsidio.gestionpsicologicabackend.dto.updatekibana;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "took",
    "timed_out",
    "total",
    "updated",
    "deleted",
    "batches",
    "version_conflicts",
    "noops",
    "retries",
    "throttled_millis",
    "requests_per_second",
    "throttled_until_millis",
    "failures"
})
@Generated("jsonschema2pojo")
public class UpdateAppointmentResponse {

    @JsonProperty("took")
    private Integer took;
    @JsonProperty("timed_out")
    private Boolean timedOut;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("updated")
    private Integer updated;
    @JsonProperty("deleted")
    private Integer deleted;
    @JsonProperty("batches")
    private Integer batches;
    @JsonProperty("version_conflicts")
    private Integer versionConflicts;
    @JsonProperty("noops")
    private Integer noops;
    @JsonProperty("retries")
    private Retries retries;
    @JsonProperty("throttled_millis")
    private Integer throttledMillis;
    @JsonProperty("requests_per_second")
    private Double requestsPerSecond;
    @JsonProperty("throttled_until_millis")
    private Integer throttledUntilMillis;
    @JsonProperty("failures")
    private List<Failure> failures = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("took")
    public Integer getTook() {
        return took;
    }

    @JsonProperty("took")
    public void setTook(Integer took) {
        this.took = took;
    }

    @JsonProperty("timed_out")
    public Boolean getTimedOut() {
        return timedOut;
    }

    @JsonProperty("timed_out")
    public void setTimedOut(Boolean timedOut) {
        this.timedOut = timedOut;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("updated")
    public Integer getUpdated() {
        return updated;
    }

    @JsonProperty("updated")
    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    @JsonProperty("deleted")
    public Integer getDeleted() {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @JsonProperty("batches")
    public Integer getBatches() {
        return batches;
    }

    @JsonProperty("batches")
    public void setBatches(Integer batches) {
        this.batches = batches;
    }

    @JsonProperty("version_conflicts")
    public Integer getVersionConflicts() {
        return versionConflicts;
    }

    @JsonProperty("version_conflicts")
    public void setVersionConflicts(Integer versionConflicts) {
        this.versionConflicts = versionConflicts;
    }

    @JsonProperty("noops")
    public Integer getNoops() {
        return noops;
    }

    @JsonProperty("noops")
    public void setNoops(Integer noops) {
        this.noops = noops;
    }

    @JsonProperty("retries")
    public Retries getRetries() {
        return retries;
    }

    @JsonProperty("retries")
    public void setRetries(Retries retries) {
        this.retries = retries;
    }

    @JsonProperty("throttled_millis")
    public Integer getThrottledMillis() {
        return throttledMillis;
    }

    @JsonProperty("throttled_millis")
    public void setThrottledMillis(Integer throttledMillis) {
        this.throttledMillis = throttledMillis;
    }

    @JsonProperty("requests_per_second")
    public Double getRequestsPerSecond() {
        return requestsPerSecond;
    }

    @JsonProperty("requests_per_second")
    public void setRequestsPerSecond(Double requestsPerSecond) {
        this.requestsPerSecond = requestsPerSecond;
    }

    @JsonProperty("throttled_until_millis")
    public Integer getThrottledUntilMillis() {
        return throttledUntilMillis;
    }

    @JsonProperty("throttled_until_millis")
    public void setThrottledUntilMillis(Integer throttledUntilMillis) {
        this.throttledUntilMillis = throttledUntilMillis;
    }

    @JsonProperty("failures")
    public List<Failure> getFailures() {
        return failures;
    }

    @JsonProperty("failures")
    public void setFailures(List<Failure> failures) {
        this.failures = failures;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
