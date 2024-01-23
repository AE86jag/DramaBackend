package com.spmystery.episode.drama.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserWatchDramaRecord {
    private Integer id;
    private String userId;
    private Long dramaId;
    private Integer dramaIndex;
    private String dramaTitle;
    private Integer dramaTotal;
    private LocalDateTime lastModifyTime;
    private LocalDateTime createTime;
}
