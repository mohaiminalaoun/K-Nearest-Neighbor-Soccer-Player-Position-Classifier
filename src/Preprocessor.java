
import org.la4j.matrix.sparse.CRSMatrix;
import org.la4j.matrix.sparse.SparseMatrix;
import org.la4j.vector.Vector;

import java.io.*;

/*
Class to process input the data and process it, i.e. clean, parse and normalize
 */
public class Preprocessor {

    public Preprocessor(String fileName) {

        /*LA4J */
        SparseMatrix a = new CRSMatrix(999,5);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(fileName));
            // skip first line
            br.readLine();
            int i = 0;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                line = line.replace("+","."); // for youth players
                String[] data = line.split(cvsSplitBy);
                a.set(i,0,Double.parseDouble(data[4])); //age
                a.set(i,1, Double.parseDouble(data[7]));// overall
                a.set(i,2, Double.parseDouble(data[8]));// potential
                a.set(i,3, Double.parseDouble(data[22]));// dribbling
                a.set(i,4, Double.parseDouble(data[23]));// finishing


                i++;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            Vector v = a.getColumn(i);
            a.setColumn(i, v.normalize());
        }



    }


}
