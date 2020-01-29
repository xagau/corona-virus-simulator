/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rnaught;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Sean Beecroft
 */
public class Rnaught {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 
        double numberOfCases = 2;
        double infectedIndividuals = 0;
        double population = 5000000;
        double rnaught = 2.5;
        long numberOfVentilators = 10000;
        long usedVentilators = 0;
        long days = 30;
        long deaths = 0;
        double mortality = 0.021;
        double charges = 0;
        
        double ventilatorPerDayCost = 6667.21;
        String currencySymbol = "CAD";
        
        for( long i = 1; i < days; i++ ){
            double newCases = numberOfCases * rnaught;
            DecimalFormat df = new DecimalFormat("0.0000000000");
            DecimalFormat mdf = new DecimalFormat("0.00");
            System.out.println("=Day:                                  " + i + "=");
            System.out.println("New Cases:                             " + (int)Math.floor(newCases));
            System.out.println("Number of Confirmed/Probable Cases:    " + (int)Math.floor(numberOfCases));
            long valueVentilators = (long)Math.floor(numberOfVentilators-usedVentilators);
            if( valueVentilators < 0 ) { 
                valueVentilators = 0;
            }
            
            double dayCost = usedVentilators * ventilatorPerDayCost;
            String currencyString = NumberFormat.getCurrencyInstance().format(dayCost);
            //Handle the weird exception of formatting whole dollar amounts with no decimal
            currencyString = currencyString.replaceAll("\\.00", "");

            System.out.println("Direct (Per Day) Cost to Social System:" + mdf.format(Math.abs(dayCost)) + " " + currencySymbol);
            charges += usedVentilators * 6667.21;

            String currencyString2 = NumberFormat.getCurrencyInstance().format(charges);
            //Handle the weird exception of formatting whole dollar amounts with no decimal
            currencyString2 = currencyString2.replaceAll("\\.00", "");

            
            numberOfCases += newCases;
            infectedIndividuals = numberOfCases - deaths;
            if( infectedIndividuals >= population ){
                infectedIndividuals = population;
                numberOfCases = population;
                newCases = 0;
            }

            long newDeaths = (int)Math.floor(infectedIndividuals * mortality);
            deaths += newDeaths;
            
            usedVentilators = (long)Math.floor(numberOfCases * 0.40);
            usedVentilators = usedVentilators - newDeaths;
            if( (numberOfVentilators - usedVentilators) < 0 ){
                numberOfVentilators = 0;
                usedVentilators = 10000; 
                mortality = 0.15;
            }
            
            System.out.println("Cumulative Cost to Social System:      " + mdf.format(Math.abs(charges)) + " " + currencySymbol);
            System.out.println("Available Ventilators:                 " + valueVentilators);
            System.out.println("Used Ventilators:                      " + Math.abs(usedVentilators));
            System.out.println("Infected Individuals:                  " + (int)Math.floor(newCases + infectedIndividuals));
            System.out.println("% of population:                       " + df.format((infectedIndividuals / population)*100));
            System.out.println("New Deaths:                            " + Math.abs(newDeaths));
            System.out.println("Cumulative Deaths:                     " + Math.abs(deaths));
            System.out.println("");
            System.out.println("");
            
            
        }
        
        
    }
    
}
