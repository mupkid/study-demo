package org.ohx.studyhttpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import java.io.IOException;

/**
 * @author mudkip
 * @date 2022/12/27
 */
public class MyResponseHandler implements ResponseHandler {

    @Override
    public Object handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        return null;
    }
}
