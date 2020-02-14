package tensorclient;

import java.util.List;
import java.util.Scanner;
import tensorclient.Console;
import tensorclient.datamodels.CityDataModel;
import tensorclient.responses.*;
import tensorclient.ApiRunner;

public class Console {
    private final ApiRunner runner = new ApiRunner();

    public static void main(String args[]) {
        Console clientConsole = new Console();
        clientConsole.run();
    }

    public Console() {
        // do nothing
    }

    /*
    Runs TensorClientConsole.
     */
    public void run() {
        String fullStarLine = "****************************************" +
                "****************************************";
        Scanner sc = new Scanner(System.in);
        boolean userExit = false;
        int choice;

        while(!userExit) {
            System.out.println(fullStarLine);
            System.out.println("                              TensorClient Console                              ");
            System.out.println(fullStarLine);
            System.out.println("1. Check account info");
            System.out.println("2. List brand and interest categories for search filtering");
            System.out.println("3. List countries for search filtering");
            System.out.println("4. List languages for search filtering");
            System.out.println("5. Generate new report");
            System.out.println("6. Search previously generated reports");
            System.out.println("7. Exit");
            System.out.println("Please enter the number of your choice and press [ENTER]: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    try {
                        AccountInfoResponse response = runner.doAccountInfoRequest();
                        displayAccountInfoResponse(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    continue;
                case 2:
                    System.out.println("Enter how many brands/interests you want to display and press [ENTER]: ");
                    int brandsInterestsLimit = sc.nextInt();
                    BrandsInterestsResponse response = runner.doInterestCategoriesRequest();
                    displayBrandsInterestsResponse(response, brandsInterestsLimit);
                    continue;
                case 3:
                    System.out.println("Please enter a search string to filter by and press [ENTER]: ");
                    sc.nextLine();
                    String query = sc.nextLine();
                    boolean locationLimitChoiceValid = false;
                    int locationLimit = 0;
                    while (!locationLimitChoiceValid) {
                        System.out.println("Input how many results you want to display and press [ENTER]: ");
                        locationLimit = sc.nextInt();
                        if (locationLimit > 0) { locationLimitChoiceValid = true; }
                        else { System.out.println("Invalid choice. Please try again."); }
                    }
                    int locationTypeChoice;
                    String locationType = "";
                    boolean locationTypeChoiceValid = false;
                    while (!locationTypeChoiceValid) {
                        System.out.println("Input the number corresponding to the results you want to display.");
                        System.out.println("1. Cities only");
                        System.out.println("2. States only");
                        System.out.println("3. Countries only");
                        System.out.println("4. All locations");
                        locationTypeChoice = sc.nextInt();
                        switch (locationTypeChoice) {
                            case 1:
                                locationType = "city";
                                locationTypeChoiceValid = true;
                                continue;
                            case 2:
                                locationType = "state";
                                locationTypeChoiceValid = true;
                                continue;
                            case 3:
                                locationType = "country";
                                locationTypeChoiceValid = true;
                                continue;
                            case 4:
                                locationType = "";
                                locationTypeChoiceValid = true;
                                continue;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                continue;
                        }
                    }
                    //System.out.println("Getting LocationsResponse");
                    LocationsResponse locationsResponse = runner.doLocationsRequest(query, locationLimit, locationType);
                    //System.out.println("Displaying LocationsResponse");
                    displayLocationsResponse(locationsResponse, query, locationLimit, locationType);
                    continue;
                case 4:
                    // List languages
                    // call TensorWebAPI function
                    continue;
                case 5:
                    // Generate new report
                    // call TensorWebAPI function
                    continue;
                case 6:
                    // Search previously generated reports
                    // call TensorWebAPI function
                    continue;
                case 7:
                    System.out.println("Exiting...");
                    userExit = true;
                    continue;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }
        }
    }

    /*
    Displays info from an AccountInfoResponse object.
     */
    public void displayAccountInfoResponse(AccountInfoResponse resp) {
        UserInfoModel user = resp.getUser();
        UserCreditsModel credits = user.getCredits();
        System.out.println("--------------- ACCOUNT INFO --------------------");
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Credits: ");
        System.out.println("\tReports: " + credits.getReports());
        System.out.println("\tExports: " + credits.getExports());
        System.out.println("\tPosts: " + credits.getPosts());
        System.out.println("-------------------------------------------------");
    }

    /*
    Displays info from a BrandsInterestsResponse object.
     */
    public void displayBrandsInterestsResponse(BrandsInterestsResponse resp, int limit) {
        boolean success = resp.isSuccess();
        if (!success) {
            System.out.println("The response did not succeed for some reason");
            return;
        }
        BrandsInterestsDataModel dataModel = resp.getData();
        System.out.println("Listing the first " + Integer.toString(limit) + " entries for brands and interests.");
        System.out.println("Brands: ");
        List<BrandsEntryModel> brands = dataModel.getBrands();
        for (int i = 0; i < Math.min(limit, dataModel.getBrands().size()); ++i) {
            System.out.println("\tID: " + Integer.toString(brands.get(i).getId()));
            System.out.println("\tName: " + brands.get(i).getName());
        }
        List<InterestsEntryModel> interests = dataModel.getInterests();
        System.out.println("Interests: ");
        for (int i = 0; i < Math.min(limit, dataModel.getInterests().size()); ++i) {
            System.out.println("\tID: " + Integer.toString(interests.get(i).getId()));
            System.out.println("\tName: " + interests.get(i).getName());
        }
    }

    /*
    Displays information from a LocationsResponse object.
     */
    public void displayLocationsResponse(LocationsResponse response, String query, int limit, String locationType) {
        System.out.println("--------------------Locations--------------------");
        int numIters = 0;
        if (locationType.equals("city") || locationType.equals("")) {
            System.out.println("-----CITIES-----");
            for (CityDataModel model : response.getCities()) {
                if (locationType.equals("") && numIters > limit/2) { break; }
                else if (!locationType.equals("") && numIters < limit) { break; }
                System.out.println("ID: " + model.getId());
                System.out.println(String.format("\tName: %s (%s)", model.getInfo().getName(), model.getCountryCode()));
                System.out.println("\tState ID: " + model.getStateId());
                System.out.println("\tCountry ID: " + model.getCountryId());
                System.out.println("\tTitle: " + model.getInfo().getTitle());
                System.out.println("- - - - - - - ");
                numIters++;
            }
        } if (locationType.equals("state") || locationType.equals("")) {
            //System.out.println("-----STATES-----");
            //for (CityDataModel model : response.getCities()) {
                // There are no state objects in the Tensor Social API yet.
            //}
        } if (locationType.equals("country") || locationType.equals("")) {
            System.out.println("----COUNTRIES----");
            for (CityDataModel model : response.getCities()) {
                if (locationType.equals("") && numIters > limit/2) { break; }
                else if (!locationType.equals("") && numIters < limit) { break; }
                System.out.println("ID: " + model.getId());
                System.out.println(String.format("\tName: %s (%s)", model.getInfo().getName(), model.getCountryCode()));
                System.out.println("\tCountry ID: " + model.getCountryId());
                System.out.println("\tTitle: " + model.getInfo().getTitle());
                System.out.println("- - - - - - - ");
                numIters++;
            }
        }
    }
}
