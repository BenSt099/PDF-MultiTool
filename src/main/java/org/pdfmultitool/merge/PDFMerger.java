package org.pdfmultitool.merge;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.pdfmultitool.pdfmethods.StatusCodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class PDFMerger {

    public static String approveInput(String pathToFolder, String sequenceOfPdf, String nameOfFinalPdf, String pathToFinalFolder, String[] files){

        if(files == null || files.length == 1 || pathToFolder == null || sequenceOfPdf == null
                || nameOfFinalPdf == null || isValidSequence(sequenceOfPdf)){
            System.out.println(pathToFolder);
            System.out.println(sequenceOfPdf);
            System.out.println(nameOfFinalPdf);
            System.out.println(pathToFinalFolder);
            System.out.println(Arrays.toString(files));
            System.out.println(isValidSequence(sequenceOfPdf));
            return StatusCodes.FALSEINPUT.toString();
        }

        if(pathToFinalFolder == null){
            pathToFinalFolder = pathToFolder;
        }

        StatusCodes merge = merge(pathToFolder, pathToFinalFolder, nameOfFinalPdf, files, sequenceOfPdf);
        return merge.toString();
    }

    private static StatusCodes merge(String directory, String targetDirectory, String outputFileName, String[] files, String sequence){

        String[] orderArray = sequence.split(","); // [A][C][B]

        String[] filesOrderArray = new String[files.length]; //[a.pdf][b.pdf]

        int a = 0;
        for (String h: orderArray){

            for (String k: files) {
                String[] split = k.split(":");

                if (split[1].equals(h)) {
                    filesOrderArray[a] = split[0];
                    a++;
                }
            }
        }
        /*
         * Merge files
         * */
        PDFMergerUtility mergerUtility = new PDFMergerUtility();
        mergerUtility.setDestinationFileName(targetDirectory + "/" + outputFileName + ".pdf");
        for (String w: filesOrderArray) {
            File file = new File(directory + "/" + w);
            try {
                mergerUtility.addSource(file);
            } catch (FileNotFoundException e) {
                return StatusCodes.MERGINGFAILURE;
            }
        }
        try {
            mergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        } catch (IOException e) {
            return StatusCodes.MERGINGFAILURE;
        }
        return StatusCodes.SUCCESS;
    }

    private static boolean isValidSequence(String sequence){

        return Pattern.matches("[A-Z],*", sequence);
    }
}