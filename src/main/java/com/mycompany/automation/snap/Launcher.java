/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.automation.snap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author pingale
 * Launcher for atmospheric corrections and Empirical Bathymetry
 */
public class Launcher {
    public static void main(String[] args) {
        
        Deglint_Operation deglint = new Deglint_Operation();
        DepthInvariant_Operation depthInvariant = new DepthInvariant_Operation();
        EmpiricalBathymetry_Operation empBathy = new EmpiricalBathymetry_Operation();
    }   
    
}
    
    

