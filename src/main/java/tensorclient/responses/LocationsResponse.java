package tensorclient.responses;

import tensorclient.datamodels.CityDataModel;
import tensorclient.datamodels.CountryDataModel;
import tensorclient.datamodels.LocationDataModel;
import tensorclient.datamodels.StateDataModel;

import java.util.List;

public class LocationsResponse {
    private List<CityDataModel> cities;
    private List<StateDataModel> states;
    private List<CountryDataModel> countries;

    public LocationsResponse() {
        // do nothing
    }

    public LocationsResponse(List<CityDataModel> cities, List<StateDataModel> states, List<CountryDataModel> countries) {
        this.cities = cities;
        this.states = states;
        this.countries = countries;
    }

    public List<CityDataModel> getCities() {
        return cities;
    }

    public List<StateDataModel> getStates() {
        return states;
    }

    public List<CountryDataModel> getCountries() {
        return countries;
    }
}
