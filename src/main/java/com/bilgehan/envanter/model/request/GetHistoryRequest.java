package com.bilgehan.envanter.model.request;

import lombok.Data;

@Data
public class GetHistoryRequest {
    private int page;
    private int limit;
}
