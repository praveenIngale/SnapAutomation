/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.automation.snap;

import com.bc.ceres.core.ProgressMonitor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.esa.sen2coral.algorithms.DepthInvariantIndicesOp;
import org.esa.snap.core.dataio.ProductIO;
import org.esa.snap.core.datamodel.Product;
import org.esa.snap.runtime.EngineConfig;
//import org.openide.util.Exceptions;

/**
 *
 * @author pingale
 * DepthInvariantIndicesOp is DepthInvariantIndices Algorithm implementation class
 */
public class DepthInvariant_Operation {
    Product sourceProduct;
    Product targetProduct;
    public DepthInvariant_Operation(){
    try {
            // instantiating the logger 
            EngineConfig.instance().logLevel(Level.FINE);
            
            // assigning the sourceProduct to read from ProductIO reader
            this.sourceProduct = ProductIO.readProduct("/Users/pingale/Downloads/S2A_MSIL2A_20170128T150711_N0204_R082_T19QFA_20170128T150713.SAFE/MTD_MSIL2A.dim");
            
            //Display the bands available in the source product
            String[] s1 = this.sourceProduct.getBandNames();
            for (String s : s1) {
                System.out.println("Band : " + s);
            }

        } catch (IOException ex) {
            Logger.getLogger(DepthInvariantIndicesOp.class.getName()).log(Level.SEVERE, null, ex);
        }    
        // Instantiating the DepthInvariantIndices class (to call the deglint algorithm) and calibrate the properties required.
        DepthInvariantIndicesOp depInvariantOp= new DepthInvariantIndicesOp();
        
        depInvariantOp.setSourceProduct(this.sourceProduct);
        depInvariantOp.setSourceBandNames(new String[]{"B2", "B3"});
        depInvariantOp.setDeepWaterVector(new String("deepBottom"));
        depInvariantOp.setSameBottomVectors(new String("sameBottom"));
        
        // Execute Algorithm and Output saved in targetProduct
        targetProduct = depInvariantOp.getTargetProduct();
           
                   
        try {
            // Saving the TargetProduct to external file through ProductIO writer
            ProductIO.writeProduct(targetProduct,"/Users/pingale/NetBeansProjects/Snap-Auto/Output_Dim/"+targetProduct.getName(), "BEAM-DIMAP");
        } catch (IOException ex) {
            Logger.getLogger(DepthInvariantIndicesOp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
