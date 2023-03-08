package utils;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String email;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void generateUserInfo(){
        Faker faker = new Faker();
        setFirstName(faker.name().firstName());
        setLastName(faker.name().lastName());
        setUserName(faker.name().username());
        setPassword("Clown@13");

    }
    public void generateEmployeeAddress(){
        Faker faker = new Faker();
        setStreet(faker.address().streetAddress());
        setCity(faker.address().city());
        setState(faker.address().state());
        setZipCode(faker.address().zipCode());
        setEmail(faker.internet().emailAddress());
    }
    public void saveUserInfo(String firstName, String lastName, String username, String password) throws IOException, ParseException {
        String fileName="./src/test/resources/Users.json";
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject userObj = new JSONObject();

        userObj.put("firstname",firstName);
        userObj.put("lastname",lastName);
        userObj.put("username",username);
        userObj.put("password",password);
        jsonArray.add(userObj);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

    public static JSONObject loadJson(String file, int index) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(file));
        JSONArray jsonArray = (JSONArray) obj;
        return (JSONObject) jsonArray.get(index);
    }

    public static int genearteRandomNumber(int min, int max){
        return (int) Math.round(Math.random() * (max - min) + min);
    }
    public static void updateJSONObject(String fileName, String key, String value, int index) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray = (JSONArray) object;
        JSONObject jsonObject = (JSONObject) jsonArray.get(index);
        jsonObject.put(key, value);

        FileWriter file = new FileWriter(fileName);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    }

    public static void replaceJsonFile() throws IOException {
        String fileName="./src/test/resources/Users.json";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write("[]");
        fileWriter.flush();
        fileWriter.close();
    }
}


//gradle clean test
//allure generate allure-results --clean -output allure-report
//allure serve allure-results