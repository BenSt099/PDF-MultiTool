package org.pdfmultitool.controller;

import org.pdfmultitool.merge.PDFMerger;
import org.pdfmultitool.pdfmethods.PDFMethodCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

@Controller
@RequestMapping("/merge")
public class MergeController {

    private final JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    private final static String[] listOfValues = {"v01","v02","v03","v04","v05","v06","v07","v08","v09","v10","v11","v12","v13","v14",
            "v15","v16","v17","v18","v19","v20","v21","v22","v23","v24","v25"};
    private static String pathToFolder = null;
    private static String pathToFinalFolder = null;
    private static String nameOfFinalPdf = null;
    private static String sequenceOfPdf = null;
    private static String[] allPdfs = null;

    @PostMapping("/")
    public String merge(){

        return "index";
    }

    @PostMapping("/folder")
    public String folder(Model model){

        jFileChooser.setDialogTitle("Choose Folder");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jFileChooser.showSaveDialog(null);
        File selectedFile = jFileChooser.getSelectedFile();

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (selectedFile.isDirectory()) {
                pathToFolder = selectedFile.getAbsolutePath();

                    allPdfs = PDFMethodCollection.getAllPdfs(pathToFolder);
                for(int i = 0; i < allPdfs.length; i++){
                    model.addAttribute(listOfValues[i], allPdfs[i]);
                }
            }
        }

        return "index";
    }

    @PostMapping("/targetFolder")
    public String chooseFinalFolder(){

        jFileChooser.setDialogTitle("Choose Folder");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jFileChooser.showSaveDialog(null);
        File selectedFile = jFileChooser.getSelectedFile();

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (selectedFile.isDirectory()) {
                pathToFinalFolder = selectedFile.getAbsolutePath();
            }
        }
        return "index";
    }

    @PostMapping("/merging")
    public String merging(@RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "seq", required = true) String seq, Model model){

        nameOfFinalPdf = name;
        sequenceOfPdf = seq;

        String approveInput = PDFMerger.approveInput(pathToFolder, sequenceOfPdf, nameOfFinalPdf, pathToFinalFolder, allPdfs);
        model.addAttribute("name", approveInput);
        return "index";
    }
}