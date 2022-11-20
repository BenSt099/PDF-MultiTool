package org.pdfmultitool.split;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class PDfSplitter {

    public static void split(String targetDirectory, String outputFileName, String sequence){

        String[] orderOfSplit = sequence.split(",");

        PDDocument document;

        File file = new File(outputFileName);

        try {
            document = PDDocument.load(file);
        } catch (IOException e) {

            return;
        }


        int a = 1;
        for(String h: orderOfSplit){


            File newFile = new File(targetDirectory + "/MultiTool" + h + "_" + a + ".pdf");
            PDDocument doc = new PDDocument();

            a++;

            String[] arraysBorder = h.split("-");

            int lowerBorder = Integer.parseInt(arraysBorder[0]);
            int higherBorder = Integer.parseInt(arraysBorder[1]);
            higherBorder++;
            for(int i = lowerBorder; i < higherBorder; i++){

                doc.addPage(document.getPage(i));
            }

            try {
                doc.save(newFile);
                doc.close();
            } catch (IOException e) {

                return;
            }
        }

        try {
            document.close();
        } catch (IOException e) {
            return;
        }

        return;
    }


    private boolean isValidSequence(String sequence){

        return Pattern.matches("[0-9]*-[0-9]*,*", sequence);
    }


}