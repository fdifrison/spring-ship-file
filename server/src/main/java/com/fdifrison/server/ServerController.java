package com.fdifrison.server;

import com.fdifrison.sdk.ShippingExchange;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController implements ShippingExchange {

    private final Path path = Path.of("server/src/main/resources/file.txt");

    /*
    The difference between returning a byte[] or a more generic Resource (convertible in a ByteResource) is that the
    latter has an additional header Accept-Ranges.
    In the presence of an Accept-Ranges header, the browser may try to resume an interrupted download instead of
    trying to restart the download.
     */

    @Override
    public byte[] shipByteArray() throws IOException {
        return Files.readAllBytes(path);
    }

    @Override
    public Resource shipGenericResource() throws IOException {
        var bytes = Files.readAllBytes(path);
        return new ByteArrayResource(bytes);
    }
}
