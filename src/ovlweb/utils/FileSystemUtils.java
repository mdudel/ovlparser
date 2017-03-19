/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils;

/**
 *
 * @author KKEITH
 */
public class FileSystemUtils {

    public static String getExecutionPath() {
        try {
            String executionPath = System.getProperty("user.dir");

            if (isWindows()) {
                return executionPath;
            } else if (isMac()) {
                return executionPath.replace("\\", "/");
            } else if (isUnix()) {
                return executionPath.replace("\\", "/");
            } else if (isSolaris()) {
                return executionPath.replace("\\", "/");
            } else {
                System.out.println("Current OS is not support!!");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean isWindows() {

        String os = System.getProperty("os.name").toLowerCase();
        // windows
        return (os.indexOf("win") >= 0);

    }

    public static boolean isMac() {

        String os = System.getProperty("os.name").toLowerCase();
        // Mac
        return (os.indexOf("mac") >= 0);

    }

    public static boolean isUnix() {

        String os = System.getProperty("os.name").toLowerCase();
        // linux or unix
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);

    }

    public static boolean isSolaris() {

        String os = System.getProperty("os.name").toLowerCase();
        // Solaris
        return (os.indexOf("sunos") >= 0);

    }
}
