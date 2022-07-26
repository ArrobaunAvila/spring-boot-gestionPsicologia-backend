
package com.colsubsidio.gestionpsicologicabackend.dto.updatekibana;

import java.util.HashMap;
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
    "bulk",
    "search"
})
@Generated("jsonschema2pojo")
public class Retries {

    @JsonProperty("bulk")
    private Integer bulk;
    @JsonProperty("search")
    private Integer search;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bulk")
    public Integer getBulk() {
        return bulk;
    }

    @JsonProperty("bulk")
    public void setBulk(Integer bulk) {
        this.bulk = bulk;
    }

    @JsonProperty("search")
    public Integer getSearch() {
        return search;
    }

    @JsonProperty("search")
    public void setSearch(Integer search) {
        this.search = search;
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
