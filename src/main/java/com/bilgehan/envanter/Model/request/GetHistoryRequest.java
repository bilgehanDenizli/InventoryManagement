package com.bilgehan.envanter.Model.request;

import lombok.Data;

@Data
public class GetHistoryRequest {
    private int page;
    private int limit;
}
