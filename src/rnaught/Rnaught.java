/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rnaught;

import java.text.DecimalFormat;

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
        long days = 11;
        long deaths = 0;
        
        for( long i = 1; i < days; i++ ){
            double newCases = numberOfCases * rnaught;
            DecimalFormat df = new DecimalFormat("0.0000000000");
            System.out.println("Day:" + i);
            System.out.println("New Cases:" + (int)Math.floor(newCases));
            System.out.println("Number of Confirmed/Probable Cases:" + (int)Math.floor(numberOfCases));
            System.out.println("Infected Individuals:" + (int)Math.floor(newCases + infectedIndividuals));
            System.out.println("% of population:" + df.format((infectedIndividuals / population)*100));
            long newDeaths = (int)Math.floor(infectedIndividuals * 0.15);
            deaths += newDeaths;
            System.out.println("New Deaths:" + newDeaths);
            System.out.println("Cumulative Deaths:" + deaths);
            System.out.println("-----------------------");
            System.out.println("");
            
            numberOfCases += newCases;
            infectedIndividuals = numberOfCases - deaths;
            
        }
        
        
    }
    
}
