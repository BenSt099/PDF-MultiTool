package org.pdfmultitool.pdfmethods;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFMethodCollection {

    private static final String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public static String[] getAllPdfs(String directory){


        String[] arrayContainingPdfNames = new String[getNumberOfPdfs(directory)];

        File f1 = new File(directory);

        File[] files = f1.listFiles();
        if(files == null || getNumberOfPdfs(directory) > 26){
            return arrayContainingPdfNames;
        }

        int i = 0;
        for (File f: files){

            if(!isValidPdfName(f.getName())){
                continue;
            } else {
                StringBuilder pdfsInDirectory = new StringBuilder();
                arrayContainingPdfNames[i] = pdfsInDirectory.append(f.getName()).append(":").append(alphabet[i]).toString();
                i++;
            }
        }

        return arrayContainingPdfNames;
    }

    public static int getNumberOfPdfs(String directory){
        int numberOfPdfs = 0;
        File f1 = new File(directory);

        File[] files = f1.listFiles();
        if(files == null){
            return 0;
        }
        for (File f: files){
            if(isValidPdfName(f.getName())){
                numberOfPdfs++;
            }
        }

        return numberOfPdfs;
    }

    public static boolean fileExists(String inputPath){

        Path path = Paths.get(inputPath);
        return Files.exists(path);
    }


    public static boolean pathExists(String inputPath){

        Path path = Paths.get(inputPath);
        return Files.isDirectory(path);
    }

    public static boolean ispdfFile(String path){

        if (fileExists(path)){

            String[] splittedPath = path.split("/");

            String filename = splittedPath[splittedPath.length-1];

            if (!filename.contains(".")){
                return false;
            }
            String[] splittedFilename = filename.split("\\.");

            return splittedFilename[splittedFilename.length-1].equals("pdf");
        }
        return false;
    }

    public static boolean isValidPdfName(String input){

        if(!input.contains(".") || input.equals("")){

            return false;
        }
        String[] splittedInput = input.split("\\.");

        if(!splittedInput[1].equals("pdf")){
            return false;
        }

        return true;
    }


}