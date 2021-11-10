package io.sh.linkload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkRequest {
    private Long linkId;
    private String linkName;
    private String url;
    private String description;
    private Integer visits;
    private Boolean showVisits;
}
