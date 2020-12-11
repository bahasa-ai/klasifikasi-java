package ai.bahasa;

import ai.bahasa.resources.*;
import ai.bahasa.util.Request;
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
                         new ClientData(auth.getAuth(), model.getModel()));
      }

      instance = new Klasifikasi(modelMapping);
    }

    return instance;
  }

  public Map<String, ClientData> getModels() { return modelMapping; }

  public ClassifyResponse classify(String publicId, String query) throws Exception {

    ClientData clientData = this.modelMapping.get(publicId);
    Map<String, String> headers = new HashMap<>();
    headers.put("Authorization", String.format("Bearer %s", clientData.getAuth().getToken()));

    Map<String, String> body = new HashMap<>();
    body.put("query", query);

    String classifyUrl = String.format("%s/api/v1/classify/%s", url, publicId);
    ClassifyResponse response = requestClient.request(RequestMethod.POST, classifyUrl, headers, null, body, ClassifyResponse.class);

    return response;
  }

}
