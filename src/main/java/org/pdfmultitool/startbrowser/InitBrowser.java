package org.pdfmultitool.startbrowser;

import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;

public class InitBrowser {

    public static void startBrowser(){
        String url = "localhost:9000";
        Runtime runtime = Runtime.getRuntime();

        if (isThisLinux()) {
            String[] shell = {"xdg-open", url};
            try {
                runtime.exec(shell).waitFor();
            } catch (InterruptedException | IOException e) {

                // TODO: Add Logger
            }

        } else if (isThisMac()) {
            String[] shell = {"open", url};
            try {
                runtime.exec(shell).waitFor();
            } catch (InterruptedException | IOException e) {

                // TODO: Add Logger
            }
        } else if (isThisWindows()) {
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url).waitFor();
            } catch (InterruptedException | IOException e) {

                // TODO: Add Logger
            }
        } else {
            // TODO: Add Logger
        }
    }

    private static boolean isThisMac() {
        return SystemUtils.IS_OS_MAC;
    }

    private static boolean isThisWindows() {
        return SystemUtils.IS_OS_WINDOWS;
    }

    private static boolean isThisLinux() {
        return SystemUtils.IS_OS_LINUX;
    }
}