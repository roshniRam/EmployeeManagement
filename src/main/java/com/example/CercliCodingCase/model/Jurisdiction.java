package com.example.CercliCodingCase.model;

import lombok.Getter;
import lombok.ToString;


/**
 * Represents a jurisdiction entity for storing country-specific tax rules and leave policies.
 */
@Getter
@ToString
public class Jurisdiction {

    private final String countryId;
    private final String countryName;
    private final double taxRate;
    private final int annualLeaveDays;

    /**
     * Constructor for Jurisdiction.
     */
    public Jurisdiction(final String countryId, final String countryName, final double taxRate, final int annualLeaveDays) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.taxRate = taxRate;
        this.annualLeaveDays = annualLeaveDays;
    }
}
