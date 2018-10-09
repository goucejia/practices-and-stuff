/*

*/


import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.text.PDFTextStripper;

public class extract_text {
    public static void main (String args[]) throws IOException {
        File dir = new File("./result_folder/");
        boolean result = false;
        if (!dir.exists()) {
            try {
                dir.mkdir();
                result = true;
            }
            catch (SecurityException e) {
                System.out.println("Failed to create folder");
            }
            if(result){
                System.out.println("created result folder");
            }
        }

        File[] files = new File("ABS_FILEPATH\\pdf_data").listFiles();
//        File[] files = new File("./../pdf_data/").listFiles();

        PDFTextStripper stripper = new PDFTextStripper();
        for (File file : files) {
            String name = (file.getName());
            String[] parts = name.split("\\.");
            String filename = parts[0]+"."+parts[1];
            String path = "./result_folder/" + filename + ".txt";
//            System.out.println(filename);

            // read file
//            String path = file.getPath();
            PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
            parser.parse();
            PDDocument doc = PDDocument.load(file);
            String parsed_text = stripper.getText(doc);

//            FileWriter writer = new FileWriter("path");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                bw.write(parsed_text);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

        System.out.println("done");

    }
}
