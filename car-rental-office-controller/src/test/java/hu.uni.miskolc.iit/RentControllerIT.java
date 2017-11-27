package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.model.*;
import org.junit.*;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zsoltme on 2017.11.12..
 */

public class RentControllerIT {

    //TODO: Át kell írni. Lásd application modulban az IT tesztet
/*

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private Rent rent;
    private Rent rent2;

    private Long testCustomerId;
    private Long testVehicleId;

    @BeforeClass
    public static void beforeClassSetUp() throws Exception {
        //Creating one Vehicle and one User for test purposes
        //They will be deleted after the test is done
        TestRestTemplate beforeClassRestTemplate = new TestRestTemplate();
        HttpHeaders beforeClassHeaders = new HttpHeaders();

        CreateUserRequest userRequest = new CreateUserRequest();

        userRequest.setUserId("1");
        userRequest.setUserName("rentControllerTestUser");
        userRequest.setAddress("test");
        userRequest.setPhoneNumber("test");
        userRequest.setYearOfBirth(1990);
        userRequest.setDrivingLicenceNumber("test");

        DateFormat formatManufacture = new SimpleDateFormat("yyyy-MM");
        Date yearOfManufactureDate = null;
        try {
            yearOfManufactureDate = formatManufacture.parse("1950-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CreateVehicleRequest vehicleRequest = new CreateVehicleRequest();

        vehicleRequest.setId(1L);
        vehicleRequest.setType(VehicleType.CAR);
        vehicleRequest.setManufacturer("test");
        vehicleRequest.setYearOfManufacture(yearOfManufactureDate);
        vehicleRequest.setRentCost(10000.0);
        vehicleRequest.setPersons(4);
        vehicleRequest.setPerformance(1000);
        vehicleRequest.setVehicleStatus(VehicleStatusType.FREE);
        vehicleRequest.setPlateNumber("test");
        vehicleRequest.setVehicleIdentificationNumber("test");
        vehicleRequest.setDrawBar(true);

        HttpEntity<CreateUserRequest> userCreateRequestEntity = new HttpEntity<CreateUserRequest>(userRequest, beforeClassHeaders);

        ResponseEntity<User> userResponse = beforeClassRestTemplate.exchange(
                "http://localhost:8080/user/create", HttpMethod.POST, userCreateRequestEntity, User.class);


        HttpEntity<CreateVehicleRequest> vehicleCreateRequestEntity = new HttpEntity<CreateVehicleRequest>(vehicleRequest, beforeClassHeaders);

        ResponseEntity<Vehicle> vehicleResponse = beforeClassRestTemplate.exchange(
                "http://localhost:8080/vehicle/create", HttpMethod.POST, vehicleCreateRequestEntity, Vehicle.class);

    }

    @Before
    public void setUp() throws Exception {
        ParameterizedTypeReference<List<User>> responseUserType =
                new ParameterizedTypeReference<List<User>>() {
                };

        ResponseEntity<List<User>> responseUserGetAll = restTemplate.exchange(
                "http://localhost:8080/user/getAll", HttpMethod.GET, null, responseUserType);

        ParameterizedTypeReference<List<Vehicle>> responseVehicleType =
                new ParameterizedTypeReference<List<Vehicle>>() {
                };

        ResponseEntity<List<Vehicle>> responseVehicleGetAll = restTemplate.exchange(
                "http://localhost:8080/vehicle/getAll", HttpMethod.GET, null, responseVehicleType);


        for (int i = 0; i < responseUserGetAll.getBody().size(); i++) {
            if (responseUserGetAll.getBody().get(i).getUserName().equals("rentControllerTestUser")) {
                testCustomerId = responseUserGetAll.getBody().get(i).getId();
            }
        }

        for (int i = 0; i < responseVehicleGetAll.getBody().size(); i++) {
            if (responseVehicleGetAll.getBody().get(i).getManufacturer().equals("test")) {
                testVehicleId = responseVehicleGetAll.getBody().get(i).getId();
            }
        }

        rent = new Rent();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2017-02-01");
            endDate = format.parse("2017-03-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rent.setId(null);
        rent.setCustomerId(testCustomerId);
        rent.setCompanyId(0L);
        rent.setVehicleId(testVehicleId);
        rent.setStartDate(startDate);
        rent.setEndDate(endDate);
        rent.setDurationExtendable(true);
        rent.setExtendedHours(24);
        rent.setKmUsed(100);
        rent.setDayFee(150000.0);
        rent.setKmFee(100000.0);
        rent.setOtherFee(0.0);
        rent.setTotalFee(250000.0);
        rent.setPaid(false);

        rent2 = new Rent();

        //rent2.setId(2L);
        rent2.setCustomerId(testCustomerId);
        rent2.setCompanyId(0L);
        rent2.setVehicleId(testVehicleId);
        rent2.setStartDate(startDate);
        rent2.setEndDate(endDate);
        rent2.setDurationExtendable(false);
        rent2.setExtendedHours(48);
        rent2.setKmUsed(200);
        rent2.setDayFee(150000.0);
        rent2.setKmFee(200000.0);
        rent2.setOtherFee(0.0);
        rent2.setTotalFee(350000.0);
        rent2.setPaid(true);
    }

    @After
    public void tearDown() throws Exception {

    }

    @AfterClass
    public static void afterClassTearDown() throws Exception {
        TestRestTemplate afterClassRestTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();

        List<Long> userIdsToDelete = new ArrayList();
        List<Long> vehicleIdsToDelete = new ArrayList();
        List<Long> rentIdsToDelete = new ArrayList();

        ParameterizedTypeReference<List<Rent>> responseRentType;
        ParameterizedTypeReference<List<User>> responseUserType;
        ParameterizedTypeReference<List<Vehicle>> responseVehicleType;

        responseRentType = new ParameterizedTypeReference<List<Rent>>() {
        };
        responseUserType = new ParameterizedTypeReference<List<User>>() {
        };
        responseVehicleType = new ParameterizedTypeReference<List<Vehicle>>() {
        };

        ResponseEntity<List<Rent>> responseRentGetAll = afterClassRestTemplate.exchange(
                "http://localhost:8080/rent/getAll", HttpMethod.GET, null, responseRentType);

        ResponseEntity<List<User>> responseUserGetAll = afterClassRestTemplate.exchange(
                "http://localhost:8080/user/getAll", HttpMethod.GET, null, responseUserType);

        ResponseEntity<List<Vehicle>> responseVehicleGetAll = afterClassRestTemplate.exchange(
                "http://localhost:8080/vehicle/getAll", HttpMethod.GET, null, responseVehicleType);


        //Getting the Ids to delete for User,Vehicle,Rent
        for (int i = 0; i < responseUserGetAll.getBody().size(); i++) {
            if (!userIdsToDelete.contains(responseUserGetAll.getBody().get(i).getId()) && responseUserGetAll.getBody().get(i).getUserName().equals("rentControllerTestUser")) {
                userIdsToDelete.add(responseUserGetAll.getBody().get(i).getId());
            }
        }

        for (int i = 0; i < responseVehicleGetAll.getBody().size(); i++) {
            if (!vehicleIdsToDelete.contains(responseVehicleGetAll.getBody().get(i).getId()) && responseVehicleGetAll.getBody().get(i).getManufacturer().equals("test")) {
                vehicleIdsToDelete.add(responseVehicleGetAll.getBody().get(i).getId());
            }
        }

        for (int i = 0; i < responseRentGetAll.getBody().size(); i++) {
            if (userIdsToDelete.contains(responseRentGetAll.getBody().get(i).getCustomerId()) && !rentIdsToDelete.contains(responseRentGetAll.getBody().get(i).getId())) {
                rentIdsToDelete.add(responseRentGetAll.getBody().get(i).getId());
            }
        }

        //Deleting Rent,Vehicle,User by Ids
        for (int i = 0; i < rentIdsToDelete.size(); i++) {
            HttpEntity<Long> entityRentFindOne = new HttpEntity<Long>(rentIdsToDelete.get(i), headers);

            ResponseEntity<Rent> responseRentFindOne = afterClassRestTemplate.exchange(
                    "http://localhost:8080/rent/getById", HttpMethod.POST, entityRentFindOne, Rent.class);

            HttpEntity<Rent> deleteRentEntity = new HttpEntity<Rent>(responseRentFindOne.getBody(), headers);

            afterClassRestTemplate.exchange("http://localhost:8080/rent/delete", HttpMethod.DELETE, deleteRentEntity, (Class<Object>) null);
        }

        for (int i = 0; i < vehicleIdsToDelete.size(); i++) {
            HttpEntity<Vehicle> entityVehicleFindOne = new HttpEntity<Vehicle>(null, headers);

            ResponseEntity<Vehicle> responseVehicleFindOne = afterClassRestTemplate.exchange(
                    "http://localhost:8080/vehicle/getVehicle/" + vehicleIdsToDelete.get(i), HttpMethod.GET, entityVehicleFindOne, Vehicle.class);


            HttpEntity<Vehicle> deleteVehicleEntity = new HttpEntity<Vehicle>(responseVehicleFindOne.getBody(), headers);

            afterClassRestTemplate.exchange("http://localhost:8080/vehicle/delete", HttpMethod.DELETE, deleteVehicleEntity, (Class<Object>) null);
        }

        for (int i = 0; i < userIdsToDelete.size(); i++) {
            HttpEntity<User> entityUserFindOne = new HttpEntity<User>(null, headers);

            ResponseEntity<User> responseUserFindOne = afterClassRestTemplate.exchange(
                    "http://localhost:8080/user/getUser/" + userIdsToDelete.get(i), HttpMethod.GET, entityUserFindOne, User.class);

            HttpEntity<User> deleteUserEntity = new HttpEntity<User>(responseUserFindOne.getBody(), headers);

            afterClassRestTemplate.exchange("http://localhost:8080/user/delete", HttpMethod.DELETE, deleteUserEntity, (Class<Object>) null);
        }
    }

    @Test
    public void createRent() throws Exception {
        HttpEntity<Rent> entity = new HttpEntity<Rent>(rent, headers);

        ResponseEntity<Rent> response = restTemplate.exchange(
                "http://localhost:8080/rent/create", HttpMethod.POST, entity, Rent.class);

        rent.setId(response.getBody().getId());

        assertEquals(rent, response.getBody());
    }

    @Test
    public void getRentById() throws Exception {
        HttpEntity<Rent> entityCreate = new HttpEntity<Rent>(rent, headers);

        ResponseEntity<Rent> responseCreate = restTemplate.exchange(
                "http://localhost:8080/rent/create", HttpMethod.POST, entityCreate, Rent.class);

        HttpEntity<Long> entityFindOne = new HttpEntity<Long>(responseCreate.getBody().getId(), headers);

        ResponseEntity<Rent> responseFindOne = restTemplate.exchange(
                "http://localhost:8080/rent/getById", HttpMethod.POST, entityFindOne, Rent.class);

        rent.setId(responseCreate.getBody().getId());

        assertEquals(rent, responseFindOne.getBody());
    }

    @Test
    public void getRentByFilterOptions() throws Exception {
    }

    @Test
    public void getAllRent() throws Exception {
        List<Rent> expectedRentList = new ArrayList<Rent>();

        ResponseEntity<Integer> responseRentCount = restTemplate.exchange(
                "http://localhost:8080/rent/count", HttpMethod.GET, null, Integer.class);


        Long id = 1L;
        while (expectedRentList.size() != responseRentCount.getBody()) {
            HttpEntity<Long> entity = new HttpEntity<Long>(id, headers);

            ResponseEntity<Rent> responseFindOne = restTemplate.exchange(
                    "http://localhost:8080/rent/getById", HttpMethod.POST, entity, Rent.class);
            if (responseFindOne.getBody().getId() != null) {
                expectedRentList.add(responseFindOne.getBody());
            }
            id++;
        }

        ParameterizedTypeReference<List<Rent>> responseType =
                new ParameterizedTypeReference<List<Rent>>() {
                };

        ResponseEntity<List<Rent>> responseGetAll = restTemplate.exchange(
                "http://localhost:8080/rent/getAll", HttpMethod.GET, null, responseType);


        assertEquals(expectedRentList, responseGetAll.getBody());
    }

    @Test
    public void updateRent() throws Exception {
        HttpEntity<Rent> entityCreate = new HttpEntity<Rent>(rent, headers);

        ResponseEntity<Rent> responseCreate = restTemplate.exchange(
                "http://localhost:8080/rent/create", HttpMethod.POST, entityCreate, Rent.class);

        rent2.setId(responseCreate.getBody().getId());

        HttpEntity<Rent> entityUpdate = new HttpEntity<Rent>(rent2, headers);

        ResponseEntity<Rent> responseUpdate = restTemplate.exchange(
                "http://localhost:8080/rent/update", HttpMethod.POST, entityUpdate, Rent.class);

        assertEquals(rent2, responseUpdate.getBody());
    }

    @Test
    public void deleteRent() throws Exception {

        HttpEntity<Rent> entityCreate = new HttpEntity<Rent>(rent, headers);

        ResponseEntity<Rent> responseCreate = restTemplate.exchange(
                "http://localhost:8080/rent/create", HttpMethod.POST, entityCreate, Rent.class);

        rent.setId(responseCreate.getBody().getId());

        HttpEntity<Rent> deleteEntity = new HttpEntity<Rent>(rent, headers);
        HttpEntity<Long> findOneEntity = new HttpEntity<Long>(rent.getId(), headers);

        ResponseEntity<Rent> findOneResponse = restTemplate.exchange(
                "http://localhost:8080/rent/getById", HttpMethod.POST, findOneEntity, Rent.class);

        assertNotNull(findOneResponse.getBody().getId());

        restTemplate.exchange("http://localhost:8080/rent/delete", HttpMethod.DELETE, deleteEntity, (Class<Object>) null);

        findOneResponse = restTemplate.exchange(
                "http://localhost:8080/rent/getById", HttpMethod.POST, findOneEntity, Rent.class);
        assertNull(findOneResponse.getBody().getId());
    }

*/
}