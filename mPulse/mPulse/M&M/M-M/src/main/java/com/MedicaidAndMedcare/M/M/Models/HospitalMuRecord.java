package com.MedicaidAndMedcare.M.M.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)   // ignore the other ~12 fields
public class HospitalMuRecord {

    private String region;

    @JsonProperty("region_code")
    private String regionCode;

    private String period;

    @JsonProperty("pct_hospitals_mu")
    private Double pctHospitalsMu;

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getRegionCode() { return regionCode; }
    public void setRegionCode(String regionCode) { this.regionCode = regionCode; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public Double getPctHospitalsMu() { return pctHospitalsMu; }
    public void setPctHospitalsMu(Double pctHospitalsMu) { this.pctHospitalsMu = pctHospitalsMu; }
}