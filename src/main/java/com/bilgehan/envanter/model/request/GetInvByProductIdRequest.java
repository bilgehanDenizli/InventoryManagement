package com.bilgehan.envanter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInvByProductIdRequest implements Serializable {
    private long productId;
}
