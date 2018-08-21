
import org.la4j.matrix.sparse.CRSMatrix;
import org.la4j.matrix.sparse.SparseMatrix;
import org.la4j.vector.Vector;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

/*
Class to process input the data and process it, i.e. clean, parse and normalize
 */
public class Preprocessor {

    public Preprocessor(String fileName) {

        /*LA4J */
        int NUM_COLUMNS = 9;
        SparseMatrix a = new CRSMatrix(999,NUM_COLUMNS);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        HashMap<String, Integer> POSITIONS = new HashMap<>();
        POSITIONS.put("ST",0);
        POSITIONS.put("LM",1);
        POSITIONS.put("CF",2);
        POSITIONS.put("GK",3);
        POSITIONS.put("RW",4);
        POSITIONS.put("CM",5);
        POSITIONS.put("LW",6);
        POSITIONS.put("CDM",7);
        POSITIONS.put("CAM",8);
        POSITIONS.put("RB",9);
        POSITIONS.put("LB",10);
        POSITIONS.put("LWB",11);
        POSITIONS.put("RM",12);
        POSITIONS.put("RWB",13);
        POSITIONS.put("CB",14);


        try {

            br = new BufferedReader(new FileReader(fileName));
            // skip first line
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                line = line.replace("+","."); // for youth players
                line = line.replace("-","."); // for youth players
                String[] data = line.split(cvsSplitBy);
                a.set(i,0,Double.parseDouble(data[4])); //age
                a.set(i,1, Double.parseDouble(data[7]));// overall
                a.set(i,2, Double.parseDouble(data[8]));// potential
                a.set(i,3, Double.parseDouble(data[22]));// dribbling
                a.set(i,4, Double.parseDouble(data[23]));// finishing
                a.set(i,5, Double.parseDouble(data[39])); // short passing
                a.set(i,6, Double.parseDouble(data[44])); // standing tackle
                a.set(i,7, Double.parseDouble(data[26])); // GK handling
                a.set(i,8, POSITIONS.get(data[63].split(" ")[0]));


                i++;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < NUM_COLUMNS -1 ; i++) {
            Vector v = a.getColumn(i);
            a.setColumn(i, v.normalize());
        }




    }


}
