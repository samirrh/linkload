package io.sh.linkload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkResponse {
    private Long linkId;
    private String linkName;
    private String userName;
    private String url;
    private String description;
    private Integer views;
    private Boolean showViews;
}
