package com.fdifrison.client;

import com.fdifrison.sdk.ShippingExchange;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("retrieve")
public class ClientController {

    private final ShippingExchange shippingExchange;

    public ClientController(ShippingExchange shippingExchange) {
        this.shippingExchange = shippingExchange;
    }

    @GetMapping(value = "byte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getBytes() throws IOException {
        return shippingExchange.shipByteArray();
    }

    @GetMapping(value = "resource", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource getResource() throws IOException {
        return shippingExchange.shipGenericResource();

    }

}
