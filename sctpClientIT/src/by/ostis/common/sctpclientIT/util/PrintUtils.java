/**
 * 
 */
package by.ostis.common.sctpclientIT.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Andrew Nepogoda Apr 11, 2015
 */
public class PrintUtils {

    public static String stackTraceToString(Throwable e) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
