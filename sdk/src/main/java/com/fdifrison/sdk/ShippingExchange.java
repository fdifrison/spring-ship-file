package com.fdifrison.sdk;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("ship")
public interface ShippingExchange {

    @GetExchange("byte")
    byte[] shipByteArray() throws IOException;

    @GetExchange("resource")
    Resource shipGenericResource() throws IOException;
}
