package com.bilgehan.envanter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetHistoryRequest implements Serializable {
    private int page;
    private int limit;
}
