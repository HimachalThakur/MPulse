package com.MedicaidAndMedcare.M.M.Controllers;//package com.MedicaidAndMedcare.M.M.Controllers;

import com.MedicaidAndMedcare.M.M.Service.HospitalMuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalMuController {

    private final HospitalMuService service;

    public HospitalMuController(HospitalMuService service) {
        this.service = service;
    }

    // GET /api/hospitals/meaningful-use?year=2014
    @GetMapping("/meaningful-use")
    public ResponseEntity<String> cricticalAccessHospital() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.fetchData());
    }
}