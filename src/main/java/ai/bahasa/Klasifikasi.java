package ai.bahasa;

import ai.bahasa.resources.*;
import ai.bahasa.util.Request;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Klasifikasi {

  private static Request requestClient = new Request();

  public static String url = "https://api.klasifikasi.com";

  private static Klasifikasi instance;

  private final Map<String, ClientData> modelMapping;

  public Klasifikasi(Map<String, ClientData> modelMapping) {
    this.modelMapping = modelMapping;
  }

  public static Klasifikasi build(BuildParams[] params) throws Exception {
    if (instance == null) {

      if (params == null) {
        throw new Exception("Params cant be null !");
      }

      Map<String, ClientData> modelMapping = new HashMap<>();

      for (BuildParams data : params) {

        Map<String, String> clientData = new HashMap<>();
        clientData.put("clientId", data.getClientId());
        clientData.put("clientSecret", data.getClientSecret());

        String requestTokenUrl = String.format("%s/api/v1/auth/token", url);
        ClientAuth auth =
            requestClient.request(RequestMethod.POST, requestTokenUrl, null,
                                  null, clientData, ClientAuth.class);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization",
                    String.format("Bearer %s", auth.getAuth().getToken()));
        String activeClienturl =
            String.format("%s/api/v1/auth/activeClient", url);
        ClientModel model =
            requestClient.request(RequestMethod.GET, activeClienturl, headers,
                                  null, null, ClientModel.class);

        modelMapping.put(model.getModel().getPublicId(),
                         new ClientData(auth.getAuth(), model.getModel(), data));
      }

      instance = new Klasifikasi(modelMapping);
    }

    return instance;
  }

  public Map<String, ClientData> getModels() { return modelMapping; }

  public ClassifyResponse classify(String publicId, String query) throws Exception {

    ClientData clientData = this.modelMapping.get(publicId);
    if (clientData == null) {
      throw new Exception(String.format("Model not found ! (%s)", publicId));
    }

    Map<String, String> headers = new HashMap<>();
    headers.put("Authorization", String.format("Bearer %s", getActiveToken(clientData)));

    Map<String, String> body = new HashMap<>();
    body.put("query", query);

    String classifyUrl = String.format("%s/api/v1/classify/%s", url, publicId);
    ClassifyResponse response = requestClient.request(RequestMethod.POST, classifyUrl, headers, null, body, ClassifyResponse.class);

    return response;
  }

  public LogsResponse logs(String publicId, Date startedAt, Date endedAt, int skip, int take) throws Exception {

    ClientData clientData = this.modelMapping.get(publicId);
    if (clientData == null) {
      throw new Exception(String.format("Model not found ! (%s)", publicId));
    }

    if (startedAt == null || endedAt == null) {
      throw new Exception("Date parameter cant be null !");
    }

    Map<String, String> headers = new HashMap<>();
    headers.put("Authorization", String.format("Bearer %s", getActiveToken(clientData)));

    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("startedAt", startedAt.toInstant().toString());
    queryParams.put("endedAt", endedAt.toInstant().toString());
    queryParams.put("skip", String.format("%d", skip));
    queryParams.put("take", String.format("%d", take));

    String getLogsUrl = String.format("%s/api/v1/history/%s", url, publicId);
    LogsResponse response = requestClient.request(RequestMethod.GET, getLogsUrl, headers, queryParams, null, LogsResponse.class);
    return response;
  }

  private String getActiveToken(ClientData clientData) throws Exception {
    Date now = new Date();
    if (now.before(new Date(clientData.getAuth().getExpiredAfter()))) {
      Map<String, String> body = new HashMap<>();
      body.put("clientId", clientData.getBuildParams().getClientId());
      body.put("clientSecret", clientData.getBuildParams().getClientSecret());

      String requestTokenUrl = String.format("%s/api/v1/auth/token", url);
      ClientAuth auth = requestClient.request(RequestMethod.POST, requestTokenUrl, null, null, body, ClientAuth.class);

      clientData.setAuth(auth.getAuth());

    }
    return clientData.getAuth().getToken();
  }
}
