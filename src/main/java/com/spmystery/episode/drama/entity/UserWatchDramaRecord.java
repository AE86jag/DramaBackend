package com.spmystery.episode.drama.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserWatchDramaRecord {
    private Integer id;
    private String userId;
    private String dramaId;
    private Integer dramaIndex;
    private LocalDateTime lastModifyTime;
    private LocalDateTime createTime;
}
