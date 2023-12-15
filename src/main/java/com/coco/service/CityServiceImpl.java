package com.coco.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import com.coco.domain.City;
import com.coco.repository.CityRepository;

public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Value("${api.ctyCode}")
	private String ctyCode;
	
	@Value("${api.serviceKry}")
	private String serviceKey;
	
	@Override
	public List<City> getAllCityes() {
		return cityRepository.findAll();
	}

	@Override
	@PostConstruct
    public void updateCityList() {
        try {
            String urlTemplate = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/1613000/TrainInfoService/getCtyCodeList")
                    .queryParam("serviceKey", URLEncoder.encode(serviceKey, "UTF-8"))
                    .queryParam("_type", "xml")
                    .toUriString();

            URL url = new URL(urlTemplate);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            String response = sb.toString();
            List<City> cities = parseCitiesFromXml(response);
            cityRepository.saveAll(cities);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<City> parseCitiesFromXml(String xml) {
        // TODO: XML 파싱 로직을 구현하여 XML 응답을 List<City>로 변환
        throw new UnsupportedOperationException("XML parsing not implemented yet.");
    }

}
