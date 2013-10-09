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
 * Created on Apr 7, 2005
 *
 */
package org.cidrz.project.zeprs.report;

/**
 * @author ericl
 */
public class Constants {

    // When query is not site dependent, pass this value
    protected static final int ALL_SITES = -1;

    // Perineum constants
    protected static final int PERINEUM_INTACT = 325;
    protected static final int PERINEUM_HEALING_NORMALLY = 856;
    protected static final int PERINEUM_TEAR = 2773;
    protected static final int PERINEUM_EPISIOTOMY = 2774;
    protected static final int PERINEUM_BROKEN_EPISIOTOMY = 2775;
    protected static final int PERINEUM_SWOLLEN = 2776;
    protected static final int PERINEUM_INFECTION = 1907;
    protected static final int PERINEUM_OTHER = 2033;

    // Placenta constants
    protected static final int PLACENTA_TYPE_INTACT = 2778;
    protected static final int PLACENTA_TYPE_FRAGMENTED = 2779;
    protected static final int PLACENTA_TYPE_SCHULTZ = 1228;
    protected static final int PLACENTA_TYPE_MATTHEW_DUNCAN = 1514;
    protected static final int PLACENTA_SPONTANEOUS = 255;
    protected static final int PLACENTA_CCT = 787;
    protected static final int PLACENTA_MANUAL_REMOVAL = 1227;
    protected static final int PLACENTA_COLOR_NORMAL = 279;
    protected static final int PLACENTA_COLOR_PALE = 811;
    protected static final int PLACENTA_STATE_INTACT = 2642;
    protected static final int PLACENTA_STATE_FRAGMENTED = 2641;

    // Blood Loss constants
    protected static final int BLOOD_LOSS_UNDER_500cc = 274;
    protected static final int BLOOD_LOSS_501to1000cc = 806;
    protected static final int BLOOD_LOSS_1001to1500cc = 1238;
    protected static final int BLOOD_LOSS_1501to2000cc = 1523;
    protected static final int BLOOD_LOSS_2001to2500cc = 1730;
    protected static final int BLOOD_LOSS_OVER_2500cc = 1891;

    // Mode of Delivery constants
    protected static final int DELIVERY_MODE_SPONTANEOUS = 260;
    protected static final int DELIVERY_MODE_CESAREAN = 792;
    protected static final int DELIVERY_MODE_VACUUM = 1232;
    protected static final int DELIVERY_MODE_FORCEPS = 1517;
    protected static final int DELIVERY_MODE_ASSISTED = 1727;

    // Birth Outcome constants
    protected static final int ALIVE = 289;
    protected static final int FRESH_STILL_BIRTH = 821;
    protected static final int MACERATED_STILL_BIRTH = 2720;

    // Birth Trauma constants
    protected static final int TRAUMA_NONE = 288;
    protected static final int TRAUMA_FRACTURE_SKULL = 820;
    protected static final int TRAUMA_FRACTURE_CLAVICLE = 1244;
    protected static final int TRAUMA_FRACTURE_UPPER_LIMBS = 1529;
    protected static final int TRAUMA_FRACTURE_LOWER_LIMBS = 2022;
    protected static final int TRAUMA_ERBS_PALSY = 1735;
    protected static final int TRAUMA_HIP_DISLOCATION = 1896;
    protected static final int TRAUMA_OTHER = 2614;

    // Death Enums
    protected static final int MATERNAL_DEATH = 1264;

    // Type of Birth Enums
    protected static final int SPONTANEOUS_VAGINAL = 260;
    protected static final int ASSISTED_BREECH_DELIVERY = 1727;
    protected static final int FORCEPS_DELIVERY = 1517;
    protected static final int CESAREAN_DELIVERY = 792;
    protected static final int VACUUMN_DELIVERY = 1232;

    // RPR Enums
    protected static final int RPR_NOT_DONE = 2786;
    protected static final int RPR_REACTIVE_POSITIVE = 2785;
    protected static final int RPR_TREATMENT_NONE = 2786;

    // Maximum Low Birth Weight
    protected static final int MAX_LOW_BIRTH_WEIGHT = 2500;

    // refer
    protected static final int REFER_TO_UTH_PUER = 2908;
    protected static final int REFER_TO_UTH_DISCH = 2817;

    // Postnatal infant visit
    protected static final int POSTNATAL_INFANT_DEATH = 2743;

    // RPR

    protected static final int RPR_RESULT_NONREACTIVE = 2784;
    protected static final int RPR_RESULT_REACTIVE = 2785;
    protected static final int RPR_RESULT_NOT_DONE = 2786;
    protected static final int RPR_TREATMENT_PENICILLIN = 2787;
    protected static final int RPR_TREATMENT_ERYTHROMYCIN = 2788;







}
