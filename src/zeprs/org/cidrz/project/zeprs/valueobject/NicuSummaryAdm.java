/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.project.zeprs.valueobject;

/**
 * JavaBean NicuSummaryAdm generated from database;
 * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.
 *
 * @author Chris Kelley
 */

/**
 * @hibernate.joined-subclass table="nicusummaryadm"
 * @hibernate.joined-subclass-key column="id"
 */
public class NicuSummaryAdm extends EncounterData {

private String field816;
private Boolean field1373;
private Boolean field1375;
private Boolean field1374;
private Boolean field1376;
private Boolean field1377;
private Boolean field1378;
private Boolean field1379;
private Boolean field1380;
private Boolean field1381;
private Boolean field1382;
private Boolean field1383;
private String field818;
private String field1184;


 /**
  * @return
  * @hibernate.property column="investigations" type="text"
  */
    public String getField816() {
        return this.field816;
    }

    public void setField816(String field816) {
        this.field816 = field816;
    }





 /**
  * @return
  * @hibernate.property column="oxygen_not_ventilated"
  */
    public Boolean getField1373() {
        return this.field1373;
    }

    public void setField1373(Boolean field1373) {
        this.field1373 = field1373;
    }





 /**
  * @return
  * @hibernate.property column="mechanical_ventilation"
  */
    public Boolean getField1375() {
        return this.field1375;
    }

    public void setField1375(Boolean field1375) {
        this.field1375 = field1375;
    }





 /**
  * @return
  * @hibernate.property column="antibiotics"
  */
    public Boolean getField1374() {
        return this.field1374;
    }

    public void setField1374(Boolean field1374) {
        this.field1374 = field1374;
    }





 /**
  * @return
  * @hibernate.property column="nevirapine"
  */
    public Boolean getField1376() {
        return this.field1376;
    }

    public void setField1376(Boolean field1376) {
        this.field1376 = field1376;
    }





 /**
  * @return
  * @hibernate.property column="pressors"
  */
    public Boolean getField1377() {
        return this.field1377;
    }

    public void setField1377(Boolean field1377) {
        this.field1377 = field1377;
    }





 /**
  * @return
  * @hibernate.property column="intravenous_fluids"
  */
    public Boolean getField1378() {
        return this.field1378;
    }

    public void setField1378(Boolean field1378) {
        this.field1378 = field1378;
    }





 /**
  * @return
  * @hibernate.property column="umbilical_artery_catheter"
  */
    public Boolean getField1379() {
        return this.field1379;
    }

    public void setField1379(Boolean field1379) {
        this.field1379 = field1379;
    }





 /**
  * @return
  * @hibernate.property column="xs_transfusion"
  */
    public Boolean getField1380() {
        return this.field1380;
    }

    public void setField1380(Boolean field1380) {
        this.field1380 = field1380;
    }





 /**
  * @return
  * @hibernate.property column="phototherapy"
  */
    public Boolean getField1381() {
        return this.field1381;
    }

    public void setField1381(Boolean field1381) {
        this.field1381 = field1381;
    }





 /**
  * @return
  * @hibernate.property column="vitamin_k"
  */
    public Boolean getField1382() {
        return this.field1382;
    }

    public void setField1382(Boolean field1382) {
        this.field1382 = field1382;
    }





 /**
  * @return
  * @hibernate.property column="treatment_other"
  */
    public Boolean getField1383() {
        return this.field1383;
    }

    public void setField1383(Boolean field1383) {
        this.field1383 = field1383;
    }





 /**
  * @return
  * @hibernate.property column="treatment_other_desc_818" type="text"
  */
    public String getField818() {
        return this.field818;
    }

    public void setField818(String field818) {
        this.field818 = field818;
    }





 /**
  * @return
  * @hibernate.property column="antibics_used_1184" type="text"
  */
    public String getField1184() {
        return this.field1184;
    }

    public void setField1184(String field1184) {
        this.field1184 = field1184;
    }





}
