
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
    "type",
    "reason",
    "index_uuid",
    "shard",
    "index"
})
@Generated("jsonschema2pojo")
public class Cause {

    @JsonProperty("type")
    private String type;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("index_uuid")
    private String indexUuid;
    @JsonProperty("shard")
    private String shard;
    @JsonProperty("index")
    private String index;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("index_uuid")
    public String getIndexUuid() {
        return indexUuid;
    }

    @JsonProperty("index_uuid")
    public void setIndexUuid(String indexUuid) {
        this.indexUuid = indexUuid;
    }

    @JsonProperty("shard")
    public String getShard() {
        return shard;
    }

    @JsonProperty("shard")
    public void setShard(String shard) {
        this.shard = shard;
    }

    @JsonProperty("index")
    public String getIndex() {
        return index;
    }

    @JsonProperty("index")
    public void setIndex(String index) {
        this.index = index;
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
