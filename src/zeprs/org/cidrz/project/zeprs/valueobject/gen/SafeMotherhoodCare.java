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
 * JavaBean SafeMotherhoodCare generated from database;
 * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.
 *
 * @author Chris Kelley
 *         Date: 2007-08-14
 *         Time: 18:08:15
 *         Form Name: Safe Motherhood Care
 *         Form Id: 92
 */

/**
 * @hibernate.joined-subclass table="safemotherhoodcare"
 * @hibernate.joined-subclass-key column="id"
 */
public class SafeMotherhoodCare extends EncounterData {

private Byte field1675;	//prior_hiv_testing
private Date field1676;	//prior_hiv_testing_date
private transient Integer field1677;	//prior_hiv_test_result
private Integer field1866;	//hiv_test_result
private transient Byte field1892;	//rbd_home_del
private Integer field1899;	//rbd_home_regimen
private Integer field1893;	//rbd_home_dosage
private Integer field1946;	//regimen_delivery
private Byte field1600;	//rbd_home
private Byte field1904;	//rbd_home_administerred
private Byte field1894;	//rbd_ld
private transient Integer field1895;	//rbd_ld_dosage
private transient Integer field1900;	//rbd_ld_regimen
private Byte field1734;	//patient_sleep_ITN
private Byte field1887;	//tt1_done
private Date field110;	//tt1_110
private Byte field1888;	//tt2_done
private Date field111;	//tt2_111
private Byte field1889;	//tt3_done
private Date field112;	//tt3_112
private Byte field1890;	//tt4_done
private Date field113;	//tt4_113
private Byte field1891;	//tt5_done
private Date field114;	//tt5_114
private Byte field107;	//childhood_dp_immun_107


 /**
  * @return
  * @hibernate.property column="prior_hiv_testing"
  */
    public Byte getField1675() {
        return this.field1675;
    }

    public void setField1675(Byte field1675) {
        this.field1675 = field1675;
    }





 /**
  * @return
  * @hibernate.property column="prior_hiv_testing_date"
  */
    public Date getField1676() {
        return this.field1676;
    }

    public void setField1676(Date field1676) {
        this.field1676 = field1676;
    }









 /**
  * @return
  * @hibernate.property column="hiv_test_result"
  */
    public Integer getField1866() {
        return this.field1866;
    }

    public void setField1866(Integer field1866) {
        this.field1866 = field1866;
    }









 /**
  * @return
  * @hibernate.property column="rbd_home_regimen"
  */
    public Integer getField1899() {
        return this.field1899;
    }

    public void setField1899(Integer field1899) {
        this.field1899 = field1899;
    }





 /**
  * @return
  * @hibernate.property column="rbd_home_dosage"
  */
    public Integer getField1893() {
        return this.field1893;
    }

    public void setField1893(Integer field1893) {
        this.field1893 = field1893;
    }





 /**
  * @return
  * @hibernate.property column="regimen_delivery"
  */
    public Integer getField1946() {
        return this.field1946;
    }

    public void setField1946(Integer field1946) {
        this.field1946 = field1946;
    }





 /**
  * @return
  * @hibernate.property column="rbd_home"
  */
    public Byte getField1600() {
        return this.field1600;
    }

    public void setField1600(Byte field1600) {
        this.field1600 = field1600;
    }





 /**
  * @return
  * @hibernate.property column="rbd_home_administerred"
  */
    public Byte getField1904() {
        return this.field1904;
    }

    public void setField1904(Byte field1904) {
        this.field1904 = field1904;
    }





 /**
  * @return
  * @hibernate.property column="rbd_ld"
  */
    public Byte getField1894() {
        return this.field1894;
    }

    public void setField1894(Byte field1894) {
        this.field1894 = field1894;
    }













 /**
  * @return
  * @hibernate.property column="patient_sleep_ITN"
  */
    public Byte getField1734() {
        return this.field1734;
    }

    public void setField1734(Byte field1734) {
        this.field1734 = field1734;
    }





 /**
  * @return
  * @hibernate.property column="tt1_done"
  */
    public Byte getField1887() {
        return this.field1887;
    }

    public void setField1887(Byte field1887) {
        this.field1887 = field1887;
    }





 /**
  * @return
  * @hibernate.property column="tt1_110"
  */
    public Date getField110() {
        return this.field110;
    }

    public void setField110(Date field110) {
        this.field110 = field110;
    }





 /**
  * @return
  * @hibernate.property column="tt2_done"
  */
    public Byte getField1888() {
        return this.field1888;
    }

    public void setField1888(Byte field1888) {
        this.field1888 = field1888;
    }





 /**
  * @return
  * @hibernate.property column="tt2_111"
  */
    public Date getField111() {
        return this.field111;
    }

    public void setField111(Date field111) {
        this.field111 = field111;
    }





 /**
  * @return
  * @hibernate.property column="tt3_done"
  */
    public Byte getField1889() {
        return this.field1889;
    }

    public void setField1889(Byte field1889) {
        this.field1889 = field1889;
    }





 /**
  * @return
  * @hibernate.property column="tt3_112"
  */
    public Date getField112() {
        return this.field112;
    }

    public void setField112(Date field112) {
        this.field112 = field112;
    }





 /**
  * @return
  * @hibernate.property column="tt4_done"
  */
    public Byte getField1890() {
        return this.field1890;
    }

    public void setField1890(Byte field1890) {
        this.field1890 = field1890;
    }





 /**
  * @return
  * @hibernate.property column="tt4_113"
  */
    public Date getField113() {
        return this.field113;
    }

    public void setField113(Date field113) {
        this.field113 = field113;
    }





 /**
  * @return
  * @hibernate.property column="tt5_done"
  */
    public Byte getField1891() {
        return this.field1891;
    }

    public void setField1891(Byte field1891) {
        this.field1891 = field1891;
    }





 /**
  * @return
  * @hibernate.property column="tt5_114"
  */
    public Date getField114() {
        return this.field114;
    }

    public void setField114(Date field114) {
        this.field114 = field114;
    }





 /**
  * @return
  * @hibernate.property column="childhood_dp_immun_107"
  */
    public Byte getField107() {
        return this.field107;
    }

    public void setField107(Byte field107) {
        this.field107 = field107;
    }





}
