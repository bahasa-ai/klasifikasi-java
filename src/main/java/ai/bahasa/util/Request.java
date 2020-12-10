package ai.bahasa.util;

import ai.bahasa.resources.APIResponse;
import ai.bahasa.resources.RequestMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Request {

  private HttpURLConnection buildConnection(String url, Map<String, String> headers, Map<String, String> queryParams) throws Exception {
    Set<Map.Entry<String, String>> iterableQueryParams = (queryParams != null) ? queryParams.entrySet() : Collections.emptySet();
    StringBuilder tempUrl = new StringBuilder(url + "?");
    for (Map.Entry<String, String> data : iterableQueryParams) {
      tempUrl.append(String.format("%s=%s&",data.getKey(), data.getValue()));
    }

    tempUrl.setLength(tempUrl.length() - 1); // Remove "?" or "&" at the end of string
    URL fullUrl = new URL(tempUrl.toString());
    HttpURLConnection con = (HttpURLConnection) fullUrl.openConnection();

    Set<Map.Entry<String, String>> iterableHeaders = (headers != null) ? headers.entrySet() : Collections.emptySet();
    for (Map.Entry<String, String> data : iterableHeaders) {
      con.setRequestProperty(data.getKey(), data.getValue());
    }

    return con;
  }

  public <T> T request(
          RequestMethod method,
          String url,
          Map<String, String> headers,
          Map<String, String> queryParams,
          Map<String, String> body,
          Class<T> classOutput
  ) throws Exception {

    HttpURLConnection  conn = buildConnection(url,headers, queryParams);

    APIResponse response = doRequest(method, conn, body);

    int statusCode = response.getStatusCode();
    String responseBody = response.getResponseBody();
    T result = null;
    if (statusCode >= 200 && statusCode < 300) {
      result = new Gson().fromJson(responseBody, classOutput);
    } else {
      throw new Exception(String.format("Error : %s", responseBody));
    }

    return result;
  }

  private APIResponse doRequest(RequestMethod method, HttpURLConnection con, Map<String, String> body) throws Exception {
    con.setRequestMethod(method.getText());

    String jsonRequestBody = (body != null) ? new GsonBuilder().create().toJson(body) : null;

    if (method == RequestMethod.POST && jsonRequestBody != null) {
      con.setDoOutput(true);
      con.setRequestProperty("Accept-Charset", "utf-8");
      con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
      OutputStream stream = con.getOutputStream();
      stream.write(jsonRequestBody.getBytes(StandardCharsets.UTF_8));
      stream.close();
    }

    int statusCode = con.getResponseCode();

    InputStream inp = null;
    if (statusCode < 300 && statusCode >= 200) {
      inp = con.getInputStream();
    } else {
      inp = con.getErrorStream();
    }
    String responseBody = parseResponseBody(inp);

    return new APIResponse(statusCode, responseBody);
  }

  private String parseResponseBody(InputStream responseStream) throws Exception {
    try (final Scanner scanner = new Scanner(responseStream, StandardCharsets.UTF_8)) {
      final String responseBody = scanner.useDelimiter("\\A").next();
      responseStream.close();
      return responseBody;
    }
  }

}
