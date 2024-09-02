package com.fdifrison.sdk;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.io.IOException;

@HttpExchange("ship")
public interface ShippingExchange {


    @GetExchange("byte")
    byte[] shipByteArray() throws IOException;

    @GetExchange("resource")
    Resource shipGenericResource() throws IOException;

}
