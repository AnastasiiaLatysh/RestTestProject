package main.controllers;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResponseInfo {
    /*
     * The response object which holds the details of the response.
     */
    private static StringBuffer responseString = new StringBuffer();
    private static RestResponse restResponse = new RestResponse();

    public static RestResponse getRestResponseInfo(HttpResponse response) throws IOException {
        if (response != null) {
            /*
			 * Obtaining the response body from the response stream.
			 */
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                responseString.append(line);
            }
			/*
			 * Setting values for the response object
			 */
            restResponse.setResponseBody(responseString.toString());
            restResponse.setResponseCode(response.getStatusLine().getStatusCode());
            restResponse.setResponseMessage(response.getStatusLine().getReasonPhrase());
            Header[] rheaders = response.getAllHeaders();
            for (Header header : rheaders) {
                restResponse.setHeader(header.getName(), header.getValue());
            }
        }
        responseString.setLength(0);
        return restResponse;
    }
}
