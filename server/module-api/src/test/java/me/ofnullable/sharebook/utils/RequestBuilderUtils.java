package me.ofnullable.sharebook.utils;

import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class RequestBuilderUtils {

    public static MockMultipartHttpServletRequestBuilder putMultipart(String url) {
        var builder = MockMvcRequestBuilders.multipart(url);
        builder.with(request -> {
            request.setMethod("PUT");
            return request;
        });
        return builder;
    }

}
