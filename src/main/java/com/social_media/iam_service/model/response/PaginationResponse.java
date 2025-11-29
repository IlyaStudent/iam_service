package com.social_media.iam_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> implements Serializable {
    private List<T> data;
    private PaginationResponseData paginationData;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaginationResponseData implements Serializable {
        private Long total;
        private int limit;

        private int page;
        private int pages;
    }

}
