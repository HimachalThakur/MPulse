package com.MedicaidAndMedcare.M.M.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
@Service
public class HospitalMuServiceImpl implements HospitalMuService {

    private static final String API_URL = "https://healthit.gov/data/open-api/?source=Meaningful-Use-Acceleration-Scorecard.csv";
    private static final Logger logger = LoggerFactory.getLogger(HospitalMuServiceImpl.class);


    @Autowired
    RestTemplate restTemplate;

    @Override
    public String fetchData() {
        logger.info("Connecting to the endpoint: {}", API_URL);

        // Step 1: Fetch data from the API
        String jsonData = restTemplate.getForObject(API_URL, String.class);
        logger.info("Raw JSON Data: {}", jsonData);

        // Step 2: Parse JSON data
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> dataList;
        try {
            dataList = objectMapper.readValue(jsonData, new TypeReference<List<Map<String, String>>>() {});
        } catch (Exception e) {
            logger.error("Error parsing JSON data", e);
            return "Error processing data.";
        }

        // Step 3: Filter data for period 2014 and non-empty pct_hospitals_mu
        List<Map<String, String>> filteredData = dataList.stream()
                .filter(data -> "2014".equals(data.get("period")) && data.get("pct_hospitals_mu") != null && !data.get("pct_hospitals_mu").isEmpty())
                .collect(Collectors.toList());

        // Step 4: Sort data by pct_hospitals_mu in descending order
        filteredData.sort((a, b) -> Double.compare(
                Double.parseDouble(b.get("pct_hospitals_mu")),
                Double.parseDouble(a.get("pct_hospitals_mu"))
        ));

        // Step 5: Format the output
        List<String> formattedData = filteredData.stream()
                .map(data -> String.format("State: %s, Percentage: %s%%", data.get("region"), data.get("pct_hospitals_mu")))
                .collect(Collectors.toList());

        // Step 6: Return the result
        if (formattedData.isEmpty()) {
            logger.warn("No data found after processing.");
            return "No data available.";
        }

        formattedData.forEach(logger::info);
        return String.join("\n", formattedData);
    }



}