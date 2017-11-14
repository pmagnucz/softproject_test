package hu.uni.miskolc.iit;

    import hu.uni.miskolc.iit.model.Rent;
    import org.junit.After;
    import org.junit.Before;
    import org.junit.Test;
    import org.springframework.boot.test.web.client.TestRestTemplate;
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

public class RentControllerTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    private Rent rent;
    private Rent rent2;

    @Before
    public void setUp() throws Exception {
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

        //rent.setId(6L);
        rent.setId(null);
        rent.setCustomerId(1L);
        rent.setCompanyId(0L);
        rent.setVehicleId(1L);
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
        rent2.setCustomerId(1L);
        rent2.setCompanyId(0L);
        rent2.setVehicleId(1L);
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

    @Test
    public void createRent() throws Exception {
        HttpEntity<Rent> entity = new HttpEntity<Rent>(rent,headers);

        ResponseEntity<Rent> response = restTemplate.exchange(
                "http://localhost:8080/rent/create", HttpMethod.POST, entity, Rent.class);

        rent.setId(response.getBody().getId());

        assertEquals(rent,response.getBody());
    }

    @Test
    public void getRentById() throws Exception {
        HttpEntity<Rent> entityCreate = new HttpEntity<Rent>(rent,headers);

        ResponseEntity<Rent> responseCreate = restTemplate.exchange(
                "http://localhost:8080/rent/create", HttpMethod.POST, entityCreate, Rent.class);

        HttpEntity<Long> entityFindOne = new HttpEntity<Long>(responseCreate.getBody().getId(),headers);

        ResponseEntity<Rent> responseFindOne = restTemplate.exchange(
                "http://localhost:8080/rent/getById", HttpMethod.POST, entityFindOne, Rent.class);

        rent.setId(responseCreate.getBody().getId());

        assertEquals(rent,responseFindOne.getBody());
    }

    @Test
    public void getRentByFilterOptions() throws Exception {
    }

    @Test
    public void getAllRent() throws Exception {
        List<Rent> expectedRentList = new ArrayList<Rent>();

        ResponseEntity<Integer> responseRentCount = restTemplate.exchange(
                "http://localhost:8080/rent/count", HttpMethod.GET, null, Integer.class);

        for (int i=1;i<100;i++) {
            if(expectedRentList.size() != responseRentCount.getBody()) {
                HttpEntity<Long> entity = new HttpEntity<Long>(Long.valueOf(i),headers);

                ResponseEntity<Rent> responseFindOne = restTemplate.exchange(
                        "http://localhost:8080/rent/getById", HttpMethod.POST, entity, Rent.class);
                if(responseFindOne.getBody().getId() != null) {
                    expectedRentList.add(responseFindOne.getBody());
                }
            }
        }

        ParameterizedTypeReference<List<Rent>> responseType =
                new ParameterizedTypeReference<List<Rent>>() {
                };

        ResponseEntity<List<Rent>> responseGetAll  = restTemplate.exchange(
                "http://localhost:8080/rent/getAll",HttpMethod.GET,null, responseType);


        assertEquals(expectedRentList,responseGetAll.getBody());
    }

    @Test
    public void updateRent() throws Exception {
        HttpEntity<Rent> entityCreate = new HttpEntity<Rent>(rent,headers);

        ResponseEntity<Rent> responseCreate = restTemplate.exchange(
                "http://localhost:8080/rent/create", HttpMethod.POST, entityCreate, Rent.class);

        rent2.setId(responseCreate.getBody().getId());

        HttpEntity<Rent> entityUpdate = new HttpEntity<Rent>(rent2,headers);

        ResponseEntity<Rent> responseUpdate = restTemplate.exchange(
                "http://localhost:8080/rent/update", HttpMethod.POST, entityUpdate, Rent.class);

        assertEquals(rent2,responseUpdate.getBody());
    }

    @Test
    public void deleteRent() throws Exception {

        HttpEntity<Rent> entityCreate = new HttpEntity<Rent>(rent,headers);

        ResponseEntity<Rent> responseCreate = restTemplate.exchange(
                "http://localhost:8080/rent/create", HttpMethod.POST, entityCreate, Rent.class);

        rent.setId(responseCreate.getBody().getId());

        HttpEntity<Rent> deleteEntity = new HttpEntity<Rent>(rent,headers);
        HttpEntity<Long> findOneEntity = new HttpEntity<Long>(rent.getId(),headers);

        ResponseEntity<Rent> findOneResponse = restTemplate.exchange(
                "http://localhost:8080/rent/getById", HttpMethod.POST, findOneEntity, Rent.class);

        assertNotNull(findOneResponse.getBody().getId());

        restTemplate.exchange("http://localhost:8080/rent/delete",HttpMethod.DELETE,deleteEntity, (Class<Object>) null);

        findOneResponse = restTemplate.exchange(
                "http://localhost:8080/rent/getById", HttpMethod.POST, findOneEntity, Rent.class);
        assertNull(findOneResponse.getBody().getId());
    }

}