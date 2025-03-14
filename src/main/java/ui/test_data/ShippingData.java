package ui.test_data;

import org.json.JSONObject;
import static file_io.JsonFileHandler.readJsonFile;

public class ShippingData {
    private final JSONObject jsonData;

    public ShippingData(){
        final String filePath = "src/test/resources/ui-test-data/amazon-shipping-data.json";
        jsonData = readJsonFile(filePath);
    }
    public static ShippingData getShippingData(){
        return new ShippingData();
    }
    public String getPhoneNumber(){
        return jsonData.getString("phoneNumber");
    }
    public String getPassword(){
        return jsonData.getString("password");
    }
    public String getFullName(){
        return jsonData.getString("fullName");
    }
    public String getStreetName(){
        return jsonData.getString("streetName");
    }
    public String getBuildingName(){
        return jsonData.getString("buildingName");
    }
    public String getCityArea(){
        return jsonData.getString("cityArea");
    }
    public String getDistrict(){
        return jsonData.getString("district");
    }
    public String getLandMark(){
        return jsonData.getString("landMark");
    }
}
