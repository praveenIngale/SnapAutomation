/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.automation.snap;

import com.bc.ceres.core.ProgressMonitor;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.esa.sen2coral.algorithms.EmpiricalBathymetryOp;
import org.esa.snap.core.dataio.ProductIO;
import org.esa.snap.core.datamodel.Product;
import org.esa.snap.core.gpf.OperatorSpi;
import org.esa.snap.runtime.EngineConfig;
//simport org.openide.util.Exceptions;

/**
 *
 * @author pingale
 */
public class EmpiricalBathymetry_Operation {

    Product sourceProduct;
    Product targetProduct;
    OperatorSpi spi = new EmpiricalBathymetryOp.Spi();
    public EmpiricalBathymetry_Operation() {

        try {

            EngineConfig.instance().logLevel(Level.FINE);
            this.sourceProduct = ProductIO.readProduct("/Users/pingale/Downloads/S2A_MSIL2A_20170128T150711_N0204_R082_T19QFA_20170128T150713.SAFE/MTD_MSIL2A.dim");
            
            String[] s1 = this.sourceProduct.getBandNames();
            for (String s : s1) {
                System.out.println("Band : " + s);
            }

        } catch (IOException ex) {
            Logger.getLogger(EmpiricalBathymetryOp.class.getName()).log(Level.INFO, null, ex);
        }
        File bathymeryPointdataFile = new File("/Users/pingale/Desktop/south_puerto_bathymetry1.csv");
        //OperatorSpi spi = new EmpiricalBathymetryOp.Spi();
        EmpiricalBathymetryOp empBathyOp = (EmpiricalBathymetryOp) spi.createOperator();
        //EmpiricalBathymetryOp empBathyOp = new EmpiricalBathymetryOp();
        
        empBathyOp.setSourceProduct(this.sourceProduct);
        empBathyOp.setSourceBandNames(new String[] {"B2","B3"});
        empBathyOp.setBathymetryFile(bathymeryPointdataFile);
        empBathyOp.setnValue(10000.0);
       
       
        targetProduct = empBathyOp.getTargetProduct();
        try {
            ProductIO.writeProduct(targetProduct,"/Users/pingale/NetBeansProjects/Snap-Auto/Output_Dim/"+targetProduct.getName(), "BEAM-DIMAP");
            //ProductIO.writeProduct(targetProduct, "DeglintProduct.dim", "BEAM_DIMAP", ProgressMonitor.NULL);
        } catch (IOException ex) {
             Logger.getLogger(EmpiricalBathymetryOp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
