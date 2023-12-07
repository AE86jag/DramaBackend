package com.spmystery.episode.systemconfig.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class SystemConfig {

    private String key;

    private String value;

    private String group;

    private String remark;

    @JsonIgnore
    private LocalDateTime lastModifyTime;

    @JsonIgnore
    private LocalDateTime createTime;

    boolean keyOrValueIsEmpty(){
        return StringUtils.isEmpty(key) || StringUtils.isEmpty(value);
    }

    Map<String, String> toMap(){
        Map<String, String> result = new HashMap<>();
        result.put(key, value);
        return result;
    }
}
