package org.pdfmultitool.split;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PDfSplitterTest {

    @Test
    @DisplayName("test1: true exp.")
    void testisValidOrder1(){

        assertThat(PDfSplitter.isValidSequence("2-3,8-9,3-8")).isEqualTo(true);
    }

    @Test
    @DisplayName("test2: false exp.")
    void testisValidOrder2(){

        assertThat(PDfSplitter.isValidSequence("2-3,8-9,3-8,")).isEqualTo(true);
    }

    @Test
    @DisplayName("test3: false exp.")
    void testisValidOrder3(){

        assertThat(PDfSplitter.isValidSequence("3-2,9-8,3-4")).isEqualTo(false);
    }

}