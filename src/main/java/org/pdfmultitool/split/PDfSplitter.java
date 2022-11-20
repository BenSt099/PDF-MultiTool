package org.pdfmultitool.split;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.pdfmultitool.pdfmethods.StatusCodes;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class PDfSplitter {

    public static String approveInput(String pathToFinalFolder, String pathToFolder, String sequenceOfPdf){

        if(pathToFinalFolder == null || pathToFolder == null || sequenceOfPdf == null || !isValidSequence(sequenceOfPdf)){

            return StatusCodes.FALSEINPUT.toString();
        }

        StatusCodes split = split(pathToFinalFolder, pathToFolder, sequenceOfPdf);
        return split.toString();
    }

    private static StatusCodes split(String targetDirectory, String inputPathToFile, String sequence){

        String[] orderOfSplit = sequence.split(",");
        PDDocument document;
        File file = new File(inputPathToFile);

        try {
            document = PDDocument.load(file);
        } catch (IOException e) {
            return StatusCodes.SPLITTINGFAILURE;
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
                return StatusCodes.SPLITTINGFAILURE;
            }
        }
        try {
            document.close();
        } catch (IOException e) {
            return StatusCodes.SPLITTINGFAILURE;
        }
        return StatusCodes.SUCCESS;
    }

    public static boolean isValidSequence(String sequence){

        if(!sequence.contains(",")){
            return false;
        }
        String[] splitStrings = sequence.split(",");

        for(String h: splitStrings){
            if(!Pattern.matches("[0-9]-[0-9],?", h)){
                return false;
            }

            String[] splitNum = h.split("-");
            if(Integer.parseInt(splitNum[0]) > Integer.parseInt(splitNum[1])){
                return false;
            }
        }
        return true;
    }
}