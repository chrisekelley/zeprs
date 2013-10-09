/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.logic;

public class SystemStateManager {
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_LOCK_WARNING = 1;
    public static final int STATUS_LOCKED = 2;

    private static final String MESSAGE_NORMAL = "System status is normal.";

    private static int currentState = STATUS_NORMAL;
    private static String currentMessage = MESSAGE_NORMAL;

    private SystemStateManager() {
        // no instances allowed
    }

    public static int getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(int state, String message) throws Exception {
        switch (state) {
            case STATUS_NORMAL:
                if (currentState != STATUS_LOCK_WARNING) {
                    throw new Exception("Can only go to STATUS_NORMAL from STATUS_LOCK_WARNING.");
                }
                currentState = state;
                currentMessage = message;
                break;
            case STATUS_LOCK_WARNING:
                if (currentState != STATUS_NORMAL) {
                    throw new Exception("Can only go to STATUS_LOCK_WARNING from STATUS_NORMAL.");
                }
                currentState = state;
                currentMessage = message;
                break;
            case STATUS_LOCKED:
                currentState = state;
                currentMessage = message;
                break;
            default:
                throw new Exception("Unknown new state.");
        }
    }

    public static String getMessage() {
        return currentMessage;
    }

}
