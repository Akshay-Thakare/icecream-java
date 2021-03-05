package org.icecream;

import org.icecream.exceptions.IceCreamException;
import org.icecream.pojo.CallerDetails;

public class IceCream {

    /**
     * NOTE
     * 1. Logger support was intentionally dropped to de-clutter output
     */

    private static String prefix = "ic | ";
    private static boolean includeContext = true;
    private static boolean includeFilename = false;
    private static boolean includeClassName = true;
    private static boolean includeMethodNameAndLineNumber = true;
    private static final String ERROR_CONTEXT_DISABLED = "includeContext needs to be enabled to set this config";

    private IceCream() {
        // Instance creation not allowed
    }

    public static void setPrefix(String value) {
        prefix = value;
    }

    public static void includeContext(boolean incContext) {
        includeContext = incContext;
    }

    public static void includeFilename(boolean flag) {
        if (!includeContext) {
            throw new IceCreamException(ERROR_CONTEXT_DISABLED);
        }
        includeFilename = flag;
    }

    public static void includeClassName(boolean flag) {
        if (!includeContext) {
            throw new IceCreamException(ERROR_CONTEXT_DISABLED);
        }
        includeClassName = flag;
    }

    public static void includeMethodNameAndLineNumber(boolean flag) {
        if (!includeContext) {
            throw new IceCreamException(ERROR_CONTEXT_DISABLED);
        }
        includeMethodNameAndLineNumber = flag;
    }

    public static void ic(Object... object) {
        printer(getCallerDetails(Thread.currentThread()), object);
    }

    private static CallerDetails getCallerDetails(Thread currentThread) {
        StackTraceElement[] stackTraceElements = currentThread.getStackTrace();
        StackTraceElement caller = stackTraceElements[3];

        return new CallerDetails()
                .setClassName(caller.getClassName())
                .setMethodName(caller.getMethodName())
                .setLineNumber(String.valueOf(caller.getLineNumber()))
                .setFileName(caller.getFileName());
    }

    private static void printer(CallerDetails callerDetails, Object... objects) {
        StringBuilder builder = new StringBuilder(prefix);
        if (includeContext) {
            if (includeFilename) {
                builder.append(callerDetails.getFileName()).append(" > ");
            }
            if (includeClassName) {
                builder.append(callerDetails.getClassName()).append(" > ");
            }
            if (includeMethodNameAndLineNumber) {
                builder.append(callerDetails.getMethodName()).append(":L").append(callerDetails.getLineNumber()).append(" ");
            }
            if (objects.length > 0) {
                builder.append("> ");
            }
        }
        for (int i = 0; i < objects.length; i++) {
            builder.append("param_").append(i).append(" = ").append(objects[i]);
            if ((i + 1) != objects.length) builder.append(" | ");
        }
        System.out.println(builder.toString());
    }

}
