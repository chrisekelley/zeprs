package org.cidrz.project.zeprs.valueobject.report.gen;

import org.cidrz.project.zeprs.valueobject.EncounterData;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import java.sql.Date;
import java.util.Set;
import java.sql.Time;
import java.sql.Timestamp;
import org.cidrz.webapp.dynasite.valueobject.AuditInfo;
import java.util.TreeSet;

/**
 * JavaBean InitialVisitReport generated from database;
 * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.
 *
 * @author Chris Kelley
 *         Date: 2008-05-30
 *         Time: 17:29:55
 *         Form Name: Initial Visit Physical Exam
 *         Form Id: 77
 */

/**
 * @hibernate.joined-subclass table="initialvisit"
 * @hibernate.joined-subclass-key column="id"
 */
public class InitialVisitReport extends EncounterData {

private transient Integer height_159;	//height_159
private String height_159R;
private transient Float temperature_266;	//temperature_266
private String temperature_266R;
private transient Integer heent_161;	//heent_161
private String heent_161R;
private transient String heent_abnorm_162;	//heent_abnorm_162
private String heent_abnorm_162R;
private transient Integer teeth_163;	//teeth_163
private String teeth_163R;
private transient String teeth_other_164;	//teeth_other_164
private String teeth_other_164R;
private transient Integer thyroid_165;	//thyroid_165
private String thyroid_165R;
private transient Integer breasts_166;	//breasts_166
private String breasts_166R;
private transient Integer respiratory_system_167;	//respiratory_system_167
private String respiratory_system_167R;
private transient String respiratory_system_other;	//respiratory_system_other
private String respiratory_system_otherR;
private transient Integer heart_169;	//heart_169
private String heart_169R;
private transient String heart_other_170;	//heart_other_170
private String heart_other_170R;
private transient Integer pulse_171;	//pulse_171
private String pulse_171R;
private transient Integer abdomen_172;	//abdomen_172
private String abdomen_172R;
private transient String abdomen_abnormal_173;	//abdomen_abnormal_173
private String abdomen_abnormal_173R;
private transient Integer extremities_174;	//extremities_174
private String extremities_174R;
private transient String extremities_abnormal_175;	//extremities_abnormal_175
private String extremities_abnormal_175R;
private transient Integer skin_176;	//skin_176
private String skin_176R;
private transient String skin_abnorm_177;	//skin_abnorm_177
private String skin_abnorm_177R;
private transient Integer lymph_nodes_178;	//lymph_nodes_178
private String lymph_nodes_178R;
private transient Integer rectum_179;	//rectum_179
private String rectum_179R;
private transient String rectum_abnormal_180;	//rectum_abnormal_180
private String rectum_abnormal_180R;
private transient Integer vulva_181;	//vulva_181
private String vulva_181R;
private transient String vulva_abnormal_182;	//vulva_abnormal_182
private String vulva_abnormal_182R;
private transient Integer vagina_183;	//vagina_183
private String vagina_183R;
private transient String vagina_abnormal_184;	//vagina_abnormal_184
private String vagina_abnormal_184R;
private transient Integer cervix_185;	//cervix_185
private String cervix_185R;
private transient String cervix_abnormal_186;	//cervix_abnormal_186
private String cervix_abnormal_186R;
private transient Integer uterus_187;	//uterus_187
private String uterus_187R;
private transient Integer adnexa_189;	//adnexa_189
private String adnexa_189R;
private transient String adnexa_abnormal_190;	//adnexa_abnormal_190
private String adnexa_abnormal_190R;
private transient Integer varicosities_191;	//varicosities_191
private String varicosities_191R;
private transient Integer pallor_193;	//pallor_193
private String pallor_193R;
private transient Integer cns_192;	//cns_192
private String cns_192R;
private transient String comments;	//comments
private String commentsR;


 /**
  * @return
  * @hibernate.property column="height_159"
  */
    public Integer getHeight_159() {
        return this.height_159;
    }

    public void setHeight_159(Integer height_159) {
        this.height_159 = height_159;
    }



    public String getHeight_159R() {
        return this.height_159R;
    }

    public void setHeight_159R(String height_159R) {
        this.height_159R = height_159R;
    }



 /**
  * @return
  * @hibernate.property column="temperature_266"
  */
    public Float getTemperature_266() {
        return this.temperature_266;
    }

    public void setTemperature_266(Float temperature_266) {
        this.temperature_266 = temperature_266;
    }



    public String getTemperature_266R() {
        return this.temperature_266R;
    }

    public void setTemperature_266R(String temperature_266R) {
        this.temperature_266R = temperature_266R;
    }



 /**
  * @return
  * @hibernate.property column="heent_161"
  */
    public Integer getHeent_161() {
        return this.heent_161;
    }

    public void setHeent_161(Integer heent_161) {
        this.heent_161 = heent_161;
    }



    public String getHeent_161R() {
        return this.heent_161R;
    }

    public void setHeent_161R(String heent_161R) {
        this.heent_161R = heent_161R;
    }



 /**
  * @return
  * @hibernate.property column="heent_abnorm_162" type="text"
  */
    public String getHeent_abnorm_162() {
        return this.heent_abnorm_162;
    }

    public void setHeent_abnorm_162(String heent_abnorm_162) {
        this.heent_abnorm_162 = heent_abnorm_162;
    }



    public String getHeent_abnorm_162R() {
        return this.heent_abnorm_162R;
    }

    public void setHeent_abnorm_162R(String heent_abnorm_162R) {
        this.heent_abnorm_162R = heent_abnorm_162R;
    }



 /**
  * @return
  * @hibernate.property column="teeth_163"
  */
    public Integer getTeeth_163() {
        return this.teeth_163;
    }

    public void setTeeth_163(Integer teeth_163) {
        this.teeth_163 = teeth_163;
    }



    public String getTeeth_163R() {
        return this.teeth_163R;
    }

    public void setTeeth_163R(String teeth_163R) {
        this.teeth_163R = teeth_163R;
    }



 /**
  * @return
  * @hibernate.property column="teeth_other_164" type="text"
  */
    public String getTeeth_other_164() {
        return this.teeth_other_164;
    }

    public void setTeeth_other_164(String teeth_other_164) {
        this.teeth_other_164 = teeth_other_164;
    }



    public String getTeeth_other_164R() {
        return this.teeth_other_164R;
    }

    public void setTeeth_other_164R(String teeth_other_164R) {
        this.teeth_other_164R = teeth_other_164R;
    }



 /**
  * @return
  * @hibernate.property column="thyroid_165"
  */
    public Integer getThyroid_165() {
        return this.thyroid_165;
    }

    public void setThyroid_165(Integer thyroid_165) {
        this.thyroid_165 = thyroid_165;
    }



    public String getThyroid_165R() {
        return this.thyroid_165R;
    }

    public void setThyroid_165R(String thyroid_165R) {
        this.thyroid_165R = thyroid_165R;
    }



 /**
  * @return
  * @hibernate.property column="breasts_166"
  */
    public Integer getBreasts_166() {
        return this.breasts_166;
    }

    public void setBreasts_166(Integer breasts_166) {
        this.breasts_166 = breasts_166;
    }



    public String getBreasts_166R() {
        return this.breasts_166R;
    }

    public void setBreasts_166R(String breasts_166R) {
        this.breasts_166R = breasts_166R;
    }



 /**
  * @return
  * @hibernate.property column="respiratory_system_167"
  */
    public Integer getRespiratory_system_167() {
        return this.respiratory_system_167;
    }

    public void setRespiratory_system_167(Integer respiratory_system_167) {
        this.respiratory_system_167 = respiratory_system_167;
    }



    public String getRespiratory_system_167R() {
        return this.respiratory_system_167R;
    }

    public void setRespiratory_system_167R(String respiratory_system_167R) {
        this.respiratory_system_167R = respiratory_system_167R;
    }



 /**
  * @return
  * @hibernate.property column="respiratory_system_other" type="text"
  */
    public String getRespiratory_system_other() {
        return this.respiratory_system_other;
    }

    public void setRespiratory_system_other(String respiratory_system_other) {
        this.respiratory_system_other = respiratory_system_other;
    }



    public String getRespiratory_system_otherR() {
        return this.respiratory_system_otherR;
    }

    public void setRespiratory_system_otherR(String respiratory_system_otherR) {
        this.respiratory_system_otherR = respiratory_system_otherR;
    }



 /**
  * @return
  * @hibernate.property column="heart_169"
  */
    public Integer getHeart_169() {
        return this.heart_169;
    }

    public void setHeart_169(Integer heart_169) {
        this.heart_169 = heart_169;
    }



    public String getHeart_169R() {
        return this.heart_169R;
    }

    public void setHeart_169R(String heart_169R) {
        this.heart_169R = heart_169R;
    }



 /**
  * @return
  * @hibernate.property column="heart_other_170" type="text"
  */
    public String getHeart_other_170() {
        return this.heart_other_170;
    }

    public void setHeart_other_170(String heart_other_170) {
        this.heart_other_170 = heart_other_170;
    }



    public String getHeart_other_170R() {
        return this.heart_other_170R;
    }

    public void setHeart_other_170R(String heart_other_170R) {
        this.heart_other_170R = heart_other_170R;
    }



 /**
  * @return
  * @hibernate.property column="pulse_171"
  */
    public Integer getPulse_171() {
        return this.pulse_171;
    }

    public void setPulse_171(Integer pulse_171) {
        this.pulse_171 = pulse_171;
    }



    public String getPulse_171R() {
        return this.pulse_171R;
    }

    public void setPulse_171R(String pulse_171R) {
        this.pulse_171R = pulse_171R;
    }



 /**
  * @return
  * @hibernate.property column="abdomen_172"
  */
    public Integer getAbdomen_172() {
        return this.abdomen_172;
    }

    public void setAbdomen_172(Integer abdomen_172) {
        this.abdomen_172 = abdomen_172;
    }



    public String getAbdomen_172R() {
        return this.abdomen_172R;
    }

    public void setAbdomen_172R(String abdomen_172R) {
        this.abdomen_172R = abdomen_172R;
    }



 /**
  * @return
  * @hibernate.property column="abdomen_abnormal_173" type="text"
  */
    public String getAbdomen_abnormal_173() {
        return this.abdomen_abnormal_173;
    }

    public void setAbdomen_abnormal_173(String abdomen_abnormal_173) {
        this.abdomen_abnormal_173 = abdomen_abnormal_173;
    }



    public String getAbdomen_abnormal_173R() {
        return this.abdomen_abnormal_173R;
    }

    public void setAbdomen_abnormal_173R(String abdomen_abnormal_173R) {
        this.abdomen_abnormal_173R = abdomen_abnormal_173R;
    }



 /**
  * @return
  * @hibernate.property column="extremities_174"
  */
    public Integer getExtremities_174() {
        return this.extremities_174;
    }

    public void setExtremities_174(Integer extremities_174) {
        this.extremities_174 = extremities_174;
    }



    public String getExtremities_174R() {
        return this.extremities_174R;
    }

    public void setExtremities_174R(String extremities_174R) {
        this.extremities_174R = extremities_174R;
    }



 /**
  * @return
  * @hibernate.property column="extremities_abnormal_175" type="text"
  */
    public String getExtremities_abnormal_175() {
        return this.extremities_abnormal_175;
    }

    public void setExtremities_abnormal_175(String extremities_abnormal_175) {
        this.extremities_abnormal_175 = extremities_abnormal_175;
    }



    public String getExtremities_abnormal_175R() {
        return this.extremities_abnormal_175R;
    }

    public void setExtremities_abnormal_175R(String extremities_abnormal_175R) {
        this.extremities_abnormal_175R = extremities_abnormal_175R;
    }



 /**
  * @return
  * @hibernate.property column="skin_176"
  */
    public Integer getSkin_176() {
        return this.skin_176;
    }

    public void setSkin_176(Integer skin_176) {
        this.skin_176 = skin_176;
    }



    public String getSkin_176R() {
        return this.skin_176R;
    }

    public void setSkin_176R(String skin_176R) {
        this.skin_176R = skin_176R;
    }



 /**
  * @return
  * @hibernate.property column="skin_abnorm_177" type="text"
  */
    public String getSkin_abnorm_177() {
        return this.skin_abnorm_177;
    }

    public void setSkin_abnorm_177(String skin_abnorm_177) {
        this.skin_abnorm_177 = skin_abnorm_177;
    }



    public String getSkin_abnorm_177R() {
        return this.skin_abnorm_177R;
    }

    public void setSkin_abnorm_177R(String skin_abnorm_177R) {
        this.skin_abnorm_177R = skin_abnorm_177R;
    }



 /**
  * @return
  * @hibernate.property column="lymph_nodes_178"
  */
    public Integer getLymph_nodes_178() {
        return this.lymph_nodes_178;
    }

    public void setLymph_nodes_178(Integer lymph_nodes_178) {
        this.lymph_nodes_178 = lymph_nodes_178;
    }



    public String getLymph_nodes_178R() {
        return this.lymph_nodes_178R;
    }

    public void setLymph_nodes_178R(String lymph_nodes_178R) {
        this.lymph_nodes_178R = lymph_nodes_178R;
    }



 /**
  * @return
  * @hibernate.property column="rectum_179"
  */
    public Integer getRectum_179() {
        return this.rectum_179;
    }

    public void setRectum_179(Integer rectum_179) {
        this.rectum_179 = rectum_179;
    }



    public String getRectum_179R() {
        return this.rectum_179R;
    }

    public void setRectum_179R(String rectum_179R) {
        this.rectum_179R = rectum_179R;
    }



 /**
  * @return
  * @hibernate.property column="rectum_abnormal_180" type="text"
  */
    public String getRectum_abnormal_180() {
        return this.rectum_abnormal_180;
    }

    public void setRectum_abnormal_180(String rectum_abnormal_180) {
        this.rectum_abnormal_180 = rectum_abnormal_180;
    }



    public String getRectum_abnormal_180R() {
        return this.rectum_abnormal_180R;
    }

    public void setRectum_abnormal_180R(String rectum_abnormal_180R) {
        this.rectum_abnormal_180R = rectum_abnormal_180R;
    }



 /**
  * @return
  * @hibernate.property column="vulva_181"
  */
    public Integer getVulva_181() {
        return this.vulva_181;
    }

    public void setVulva_181(Integer vulva_181) {
        this.vulva_181 = vulva_181;
    }



    public String getVulva_181R() {
        return this.vulva_181R;
    }

    public void setVulva_181R(String vulva_181R) {
        this.vulva_181R = vulva_181R;
    }



 /**
  * @return
  * @hibernate.property column="vulva_abnormal_182" type="text"
  */
    public String getVulva_abnormal_182() {
        return this.vulva_abnormal_182;
    }

    public void setVulva_abnormal_182(String vulva_abnormal_182) {
        this.vulva_abnormal_182 = vulva_abnormal_182;
    }



    public String getVulva_abnormal_182R() {
        return this.vulva_abnormal_182R;
    }

    public void setVulva_abnormal_182R(String vulva_abnormal_182R) {
        this.vulva_abnormal_182R = vulva_abnormal_182R;
    }



 /**
  * @return
  * @hibernate.property column="vagina_183"
  */
    public Integer getVagina_183() {
        return this.vagina_183;
    }

    public void setVagina_183(Integer vagina_183) {
        this.vagina_183 = vagina_183;
    }



    public String getVagina_183R() {
        return this.vagina_183R;
    }

    public void setVagina_183R(String vagina_183R) {
        this.vagina_183R = vagina_183R;
    }



 /**
  * @return
  * @hibernate.property column="vagina_abnormal_184" type="text"
  */
    public String getVagina_abnormal_184() {
        return this.vagina_abnormal_184;
    }

    public void setVagina_abnormal_184(String vagina_abnormal_184) {
        this.vagina_abnormal_184 = vagina_abnormal_184;
    }



    public String getVagina_abnormal_184R() {
        return this.vagina_abnormal_184R;
    }

    public void setVagina_abnormal_184R(String vagina_abnormal_184R) {
        this.vagina_abnormal_184R = vagina_abnormal_184R;
    }



 /**
  * @return
  * @hibernate.property column="cervix_185"
  */
    public Integer getCervix_185() {
        return this.cervix_185;
    }

    public void setCervix_185(Integer cervix_185) {
        this.cervix_185 = cervix_185;
    }



    public String getCervix_185R() {
        return this.cervix_185R;
    }

    public void setCervix_185R(String cervix_185R) {
        this.cervix_185R = cervix_185R;
    }



 /**
  * @return
  * @hibernate.property column="cervix_abnormal_186" type="text"
  */
    public String getCervix_abnormal_186() {
        return this.cervix_abnormal_186;
    }

    public void setCervix_abnormal_186(String cervix_abnormal_186) {
        this.cervix_abnormal_186 = cervix_abnormal_186;
    }



    public String getCervix_abnormal_186R() {
        return this.cervix_abnormal_186R;
    }

    public void setCervix_abnormal_186R(String cervix_abnormal_186R) {
        this.cervix_abnormal_186R = cervix_abnormal_186R;
    }



 /**
  * @return
  * @hibernate.property column="uterus_187"
  */
    public Integer getUterus_187() {
        return this.uterus_187;
    }

    public void setUterus_187(Integer uterus_187) {
        this.uterus_187 = uterus_187;
    }



    public String getUterus_187R() {
        return this.uterus_187R;
    }

    public void setUterus_187R(String uterus_187R) {
        this.uterus_187R = uterus_187R;
    }



 /**
  * @return
  * @hibernate.property column="adnexa_189"
  */
    public Integer getAdnexa_189() {
        return this.adnexa_189;
    }

    public void setAdnexa_189(Integer adnexa_189) {
        this.adnexa_189 = adnexa_189;
    }



    public String getAdnexa_189R() {
        return this.adnexa_189R;
    }

    public void setAdnexa_189R(String adnexa_189R) {
        this.adnexa_189R = adnexa_189R;
    }



 /**
  * @return
  * @hibernate.property column="adnexa_abnormal_190" type="text"
  */
    public String getAdnexa_abnormal_190() {
        return this.adnexa_abnormal_190;
    }

    public void setAdnexa_abnormal_190(String adnexa_abnormal_190) {
        this.adnexa_abnormal_190 = adnexa_abnormal_190;
    }



    public String getAdnexa_abnormal_190R() {
        return this.adnexa_abnormal_190R;
    }

    public void setAdnexa_abnormal_190R(String adnexa_abnormal_190R) {
        this.adnexa_abnormal_190R = adnexa_abnormal_190R;
    }



 /**
  * @return
  * @hibernate.property column="varicosities_191"
  */
    public Integer getVaricosities_191() {
        return this.varicosities_191;
    }

    public void setVaricosities_191(Integer varicosities_191) {
        this.varicosities_191 = varicosities_191;
    }



    public String getVaricosities_191R() {
        return this.varicosities_191R;
    }

    public void setVaricosities_191R(String varicosities_191R) {
        this.varicosities_191R = varicosities_191R;
    }



 /**
  * @return
  * @hibernate.property column="pallor_193"
  */
    public Integer getPallor_193() {
        return this.pallor_193;
    }

    public void setPallor_193(Integer pallor_193) {
        this.pallor_193 = pallor_193;
    }



    public String getPallor_193R() {
        return this.pallor_193R;
    }

    public void setPallor_193R(String pallor_193R) {
        this.pallor_193R = pallor_193R;
    }



 /**
  * @return
  * @hibernate.property column="cns_192"
  */
    public Integer getCns_192() {
        return this.cns_192;
    }

    public void setCns_192(Integer cns_192) {
        this.cns_192 = cns_192;
    }



    public String getCns_192R() {
        return this.cns_192R;
    }

    public void setCns_192R(String cns_192R) {
        this.cns_192R = cns_192R;
    }



 /**
  * @return
  * @hibernate.property column="comments" type="text"
  */
    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }



    public String getCommentsR() {
        return this.commentsR;
    }

    public void setCommentsR(String commentsR) {
        this.commentsR = commentsR;
    }



}
