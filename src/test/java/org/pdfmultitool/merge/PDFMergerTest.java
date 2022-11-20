package org.pdfmultitool.merge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PDFMergerTest {

    @Test
    @DisplayName("Test if isValidSequence() works; true exp.")
    void testSequence(){
        String input1 = "C,S,F,E,P";
        assertThat(PDFMerger.isValidSequence(input1)).isEqualTo(true);
    }

    @Test
    @DisplayName("Test if isValidSequence() works; false exp. 1")
    void testSequence2(){
        String input1 = "C,S,F,E,P,897";
        assertThat(PDFMerger.isValidSequence(input1)).isEqualTo(false);
    }

    @Test
    @DisplayName("Test if isValidSequence() works; false exp. 2")
    void testSequence3(){
        String input1 = "C,S;E,R,Z,A";
        assertThat(PDFMerger.isValidSequence(input1)).isEqualTo(false);
    }

    @Test
    @DisplayName("Test if isValidSequence() works; false exp. 3")
    void testSequence4(){
        String input1 = "C,S,E,R,Z,A,";
        assertThat(PDFMerger.isValidSequence(input1)).isEqualTo(true);
    }
}