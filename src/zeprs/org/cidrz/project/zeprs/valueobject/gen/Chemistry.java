package org.cidrz.project.zeprs.valueobject.gen;

import org.cidrz.project.zeprs.valueobject.gen.*;
import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import java.sql.Date;
import java.util.Set;
import java.sql.Time;
import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import java.util.TreeSet;

/**
 * JavaBean Chemistry generated from database;
 * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.
 *
 * @author Chris Kelley
 *         Date: 2008-02-25
 *         Time: 13:17:03
 *         Form Name: Chemistry
 *         Form Id: 102
 */

/**
 * @hibernate.joined-subclass table="chemistry"
 * @hibernate.joined-subclass-key column="id"
 */
public class Chemistry extends EncounterData {

private Float field2018;	//na
private String field2019;	//potassium
private Integer field2020;	//chlorine
private Float field2021;	//bicarb
private Float field2022;	//gluc
private Integer field2023;	//creat
private Float field2024;	//bun
private Long field2037;	//labtest_id


 /**
  * @return
  * @hibernate.property column="na"
  */
    public Float getField2018() {
        return this.field2018;
    }

    public void setField2018(Float field2018) {
        this.field2018 = field2018;
    }





 /**
  * @return
  * @hibernate.property column="potassium"
  */
    public String getField2019() {
        return this.field2019;
    }

    public void setField2019(String field2019) {
        this.field2019 = field2019;
    }





 /**
  * @return
  * @hibernate.property column="chlorine"
  */
    public Integer getField2020() {
        return this.field2020;
    }

    public void setField2020(Integer field2020) {
        this.field2020 = field2020;
    }





 /**
  * @return
  * @hibernate.property column="bicarb"
  */
    public Float getField2021() {
        return this.field2021;
    }

    public void setField2021(Float field2021) {
        this.field2021 = field2021;
    }





 /**
  * @return
  * @hibernate.property column="gluc"
  */
    public Float getField2022() {
        return this.field2022;
    }

    public void setField2022(Float field2022) {
        this.field2022 = field2022;
    }





 /**
  * @return
  * @hibernate.property column="creat"
  */
    public Integer getField2023() {
        return this.field2023;
    }

    public void setField2023(Integer field2023) {
        this.field2023 = field2023;
    }





 /**
  * @return
  * @hibernate.property column="bun"
  */
    public Float getField2024() {
        return this.field2024;
    }

    public void setField2024(Float field2024) {
        this.field2024 = field2024;
    }





 /**
  * @return
  * @hibernate.property column="labtest_id"
  */
    public Long getField2037() {
        return this.field2037;
    }

    public void setField2037(Long field2037) {
        this.field2037 = field2037;
    }





}
