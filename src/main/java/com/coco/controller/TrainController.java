package com.coco.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coco.service.TrainService;


@Controller
@RequestMapping("/train")
public class TrainController {
	
	@Autowired
	private TrainService trainService;
	
	// 도시 코드 가져오기
	@GetMapping("/cityCodes")
    public String listCityCodes(Model model) {
        try {
            String cityCodes = trainService.getCityCodes();
            model.addAttribute("cityCodes", cityCodes);
        } catch (Exception e) {
            model.addAttribute("error", "도시 코드 가져오기 오류: " + e.getMessage());
        }
        return "cityCodes";
    }
	
	// 시/도별 기차역 목록조회
	@GetMapping("/stations")
    public String listStations(@RequestParam("cityCode") String cityCode, Model model) {
        try {
            String stations = trainService.getTrainStationByCityCode(cityCode);
            model.addAttribute("stations", stations);
        } catch (Exception e) {
            model.addAttribute("error", "스테이션 가져오기 오류: " + e.getMessage());
        }
        return "stations";
    }
	
	// 차량 종류 목록
	@GetMapping("/vhcleKndList")
	public String listVhcleKnd(Model model) {
		try {
			String vhcleKndList = trainService.getVhcleKndList();
			model.addAttribute("vhcleKndList", vhcleKndList);
		} catch (Exception e) {
			model.addAttribute("error", "차량 종류 목록 가져오기 오류: " + e.getMessage());
		}
		return "vhcleKndList";
	}
	
	// 출/도착지 기반 열차정보 조회
	@GetMapping("/trainInfo")
	public String trainInfo(
			@RequestParam("depPlaceId") String depPlaceId,
			@RequestParam("arrPlaceId") String arrPlaceId,
			@RequestParam("depPlandTime") String depPlandTime,
			Model model) {
		try {
			String trainInfo = trainService.getStrtpntAlocFndTrainInfo(depPlaceId, arrPlaceId, depPlandTime);
			model.addAttribute("trainInfo", trainInfo);
		} catch (Exception e) {
			model.addAttribute("error", "열차 정보 불러오기 오류: " + e.getMessage());
		}
		return "trainInfo";
	}
	
}
