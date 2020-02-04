/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 * These numbers have been tweaked to reflect numbers being published.
 * The purpose is to predict the outbreak into future days.
 *
 */
package r0;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Sean Beecroft
 */
public class Rnought {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 
        double numberOfCases = 1;
        double infectedIndividuals = 1;
        long population = 7000000000L;
        double rnought = 4.08 ; // 3.5;
        long numberOfVentilators = 10000;
        long usedVentilators = 0;
        long days = 50;
        double deaths = 0;
        double mortality = 1.60;
        double complications = 0.39;
        double charges = 0;
        double latency = 0.319;
        double deathLatency = 0.085;
        
        //double quarentineQuotient = 0.15;
        
        double ventilatorPerDayCost = 6667.21;
        String currencySymbol = "CAD";
        
        
        for( long i = 1; i < days; i++ ){
            double newCases = infectedIndividuals * ((rnought))  ;
            DecimalFormat df = new DecimalFormat("0.0000000000");
            DecimalFormat mdf = new DecimalFormat("0.00");
            System.out.println("=Day:                                      " + i + "=");
            System.out.println("Number of Confirmed/Probable Cases:        " + (int)Math.floor(numberOfCases));
            System.out.println("Infected Individuals with Complications:   " + (int)Math.floor((numberOfCases * complications )));
            System.out.println("New/Undetected Cases:                      " + (int)Math.floor(newCases));
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

            //newCases = newCases * quarentineQuotient;
            //numberOfCases += newCases;
            
            infectedIndividuals = infectedIndividuals * rnought * latency;
            
            if( infectedIndividuals >= population ){
                infectedIndividuals = population;
                numberOfCases = population;
                newCases = 0;
            }

            double newDeaths = Math.floor(infectedIndividuals * complications * mortality * deathLatency);
            infectedIndividuals = ( infectedIndividuals ) - newDeaths;
            deaths += newDeaths;
            
            usedVentilators = (long)Math.floor(numberOfCases * complications);
            usedVentilators = usedVentilators - (long)newDeaths;
            if( (numberOfVentilators - usedVentilators) < 0 ){
                usedVentilators = 10000; 
                mortality = 0.20;
            }
            
            System.out.println("Cumulative Cost to Social System:      " + mdf.format(Math.abs(charges)) + " " + currencySymbol);
            System.out.println("Available Ventilators:                 " + (numberOfVentilators - usedVentilators));
            System.out.println("Used Ventilators:                      " + Math.abs(usedVentilators));
            System.out.println("Infected Individuals:                  " + (int)Math.floor(numberOfCases + infectedIndividuals));
            System.out.println("% of population:                       " + df.format((infectedIndividuals / population)*100));
            System.out.println("New Deaths:                            " + Math.abs(newDeaths));
            System.out.println("Cumulative Deaths:                     " + Math.abs(deaths));
            System.out.println("");
            System.out.println("");
            
            numberOfCases += newCases;
            
        }
        
        
    }
    
}
