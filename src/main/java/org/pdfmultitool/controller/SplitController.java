package org.pdfmultitool.controller;

import org.pdfmultitool.pdfmethods.PDFMethodCollection;
import org.pdfmultitool.split.PDfSplitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

@Controller
@RequestMapping("/split")
public class SplitController {

    private static String pathToFinalFolder = null;
    private static String pathToInputFile = null;
    private static String sequenceOfPdf = null;

    private final JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    @PostMapping("/")
    public String merge(){

        return "index";
    }

    @PostMapping("/finalFolder")
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

    @PostMapping("/fileInput")
    public String fileInput(Model model){

        jFileChooser.setDialogTitle("Choose File");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.pdf", "pdf"));
        int returnValue = jFileChooser.showSaveDialog(null);
        if(jFileChooser.getSelectedFile() == null){

            model.addAttribute("filename", "Nothing Selected");
            return "index";
        }
        pathToInputFile = jFileChooser.getSelectedFile().getAbsolutePath();
        model.addAttribute("filename", pathToInputFile);

        return "index";
    }

    @PostMapping("/split")
    public String seq(@RequestParam(value = "inputseq", required = true) String seq, Model model){

        sequenceOfPdf = seq;
        String approveInput = PDfSplitter.approveInput(pathToFinalFolder, pathToInputFile, sequenceOfPdf);
        model.addAttribute("status", approveInput);
        return "index";
    }
}