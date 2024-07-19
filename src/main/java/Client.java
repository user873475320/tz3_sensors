import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.sensors.dto.MeasurementDTO;
import ru.sensors.dto.SensorDTO;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Client {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();
    private final String sensorName = "stray";

    public void createSensor() {
        String url = "http://localhost:8080/sensors/registration";
        Map<String, String> data = Map.of("name", sensorName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
    }

    public void send1000Measurements() {
        String url = "http://localhost:8080/measurements/add";

        for (int i = 0; i < 1000; i++) {
            double value = 10 + 30 * random.nextDouble();
            value = Double.parseDouble(new DecimalFormat("#.#").format(value));

            boolean raining = random.nextBoolean();


            MeasurementDTO data = new MeasurementDTO(value, raining, new SensorDTO(sensorName));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<MeasurementDTO> request = new HttpEntity<>(data, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            System.out.println(response.getStatusCode());
        }
    }

    public void print1000Measurements() {
        String url = "http://localhost:8080/measurements";
        String jsonResponse = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<MeasurementDTO> dtoList = objectMapper.readValue(jsonResponse, new TypeReference<List<MeasurementDTO>>() {});

            dtoList.forEach(System.out::println);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        Client client = new Client();

//        client.createSensor();
//        client.send1000Measurements();
        client.print1000Measurements();

    }
}
