/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

/*
 * Created on Apr 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.cidrz.project.zeprs.report;

/**
 * @author ericl
 * @deprecated - no longer used
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DeliveryRegisterBaby {

    private String babySex;
    private int babyBirthWeight;
    private String babyBirthOutcome;
    private String babyPerinatalProbs;

    DeliveryRegisterBaby() {

        babySex = "";
        babyBirthWeight = 0;
        babyBirthOutcome = "";
        babyPerinatalProbs = "";
    }

    /**
     * @return Returns the babyBirthType.
     */
    public String getBabyBirthOutcome() {
        return babyBirthOutcome;
    }

    /**
     * @param babyBirthOutcome The babyBirthType to set.
     */
    public void setBabyBirthOutcome(String babyBirthOutcome) {
        this.babyBirthOutcome = babyBirthOutcome;
    }

    /**
     * @return Returns the babyWeight.
     */
    public int getBabyBirthWeight() {
        return babyBirthWeight;
    }

    /**
     * @param babyBirthWeight The babyWeight to set.
     */
    public void setBabyBirthWeight(int babyBirthWeight) {
        this.babyBirthWeight = babyBirthWeight;
    }

    /**
     * @return Returns the babyPerinatalProbs.
     */
    public String getBabyPerinatalProbs() {
        return babyPerinatalProbs;
    }

    /**
     * @param babyPerinatalProbs The babyPerinatalProbs to set.
     */
    public void setBabyPerinatalProbs(String babyPerinatalProbs) {
        this.babyPerinatalProbs = babyPerinatalProbs;
    }

    /**
     * @return Returns the babySex.
     */
    public String getBabySex() {
        return babySex;
    }

    /**
     * @param babySex The babySex to set.
     */
    public void setBabySex(String babySex) {
        this.babySex = babySex;
    }

}
