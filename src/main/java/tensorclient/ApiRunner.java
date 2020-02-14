package tensorclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import tensorclient.datamodels.CityDataModel;
import tensorclient.datamodels.CountryDataModel;
import tensorclient.datamodels.StateDataModel;
import tensorclient.responses.AccountInfoResponse;
import tensorclient.responses.BrandsInterestsDataModel;
import tensorclient.responses.BrandsInterestsResponse;
import tensorclient.responses.LocationsResponse;

public class ApiRunner {

    private final String api_token = "f19f558720d10b2e813a7b38032f08e2a27bb079";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public ApiRunner() {
        // do nothing
    }

    /*
    Calls Tensor Social API to query account info.
    Returns String - The account info JSON in a string
    TODO: Parse JSON string
     */
    public AccountInfoResponse doAccountInfoRequest() throws Exception {
        try {
            String url = "http://api.tensorsocial.com/account/info?api_token=" + api_token;
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response resp = client.newCall(request).execute();
            if (!resp.isSuccessful()) { throw new IOException(); }

            String jsonData = resp.body().string();
            AccountInfoResponse response = gson.fromJson(jsonData, AccountInfoResponse.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    Calls Tensor Social API to get a list of all interest categories usable
    as a search filter.
    This call does not require an api key.
    Returns String - The interest categories JSON in a String
     */
    public BrandsInterestsResponse doInterestCategoriesRequest() {
        try {
            String url = "http://api.tensorsocial.com/brands";
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response resp = client.newCall(request).execute();
            if (!resp.isSuccessful()) { throw new IOException(); }

            String jsonData = resp.body().string();
            BrandsInterestsResponse response = gson.fromJson(jsonData, BrandsInterestsResponse.class);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    Queries Tensor Social API for locations with the specified parameters.
    Params:
        String query - Search query
        int limit - Number of results to display. Default 10.
        String types - Limits results to the according level.
            Possible values: "city", "state", "country"
    Returns String - The interest categories JSON in a String
     */
    public LocationsResponse doLocationsRequest(String query, int limit, String type) {
        try {
            StringBuilder url = new StringBuilder("http://api.tensorsocial.com/locations");
            if (limit > 0) {
                url.append("?limit=");
                url.append(limit);
            } if (type.equals("")) {
                // do nothing
            } else {
                url.append("?types="); url.append(type);
            }

            Request request = new Request.Builder().url(url.toString()).build();
            Response resp = client.newCall(request).execute();
            if (!resp.isSuccessful()) { throw new IOException(); }
            String jsonData = resp.body().string();

            List<CityDataModel> citiesList = new ArrayList<>();
            List<StateDataModel> statesList = new ArrayList<>();
            List<CountryDataModel> countriesList = new ArrayList<>();
            JsonElement responseElement = JsonParser.parseString(jsonData);
            JsonArray locationsResponse = responseElement.getAsJsonArray();

            for (JsonElement elem : locationsResponse) {
                JsonObject elemObject = elem.getAsJsonObject();
                JsonArray typeArray = elemObject.get("type").getAsJsonArray();
                String locationTypeString = typeArray.get(0).getAsString();
                if (locationTypeString.equals("country") && (type.equals("country") || type.equals(""))) {
                    countriesList.add(new CountryDataModel(elemObject));
                } else if (locationTypeString.equals("state") && (type.equals("state") || type.equals(""))) {
                    // NOTE: This is unused yet. Will be updated as Tensor Social API is updated.
                    statesList.add(new StateDataModel(elemObject));
                } else if (locationTypeString.equals("city") && (type.equals("city") || type.equals(""))){
                    citiesList.add(new CityDataModel(elemObject));
                }
            }

            return new LocationsResponse(citiesList, statesList, countriesList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
