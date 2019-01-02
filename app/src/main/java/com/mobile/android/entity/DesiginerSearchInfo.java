package com.mobile.android.entity;

/**
 * Created by wangqiang on 2018/5/28.
 */
public class DesiginerSearchInfo {


    /**
     * firm_name : test
     * material_id : 56
     * mobile : 18217107662
     * type : material
     */

    private String firm_name;
    private String material_id;
    private String mobile;
    private String type;
    /**
     * designer_id : 23
     */

    private String designer_id;

    public String getFirm_name() {
        return firm_name;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesigner_id() {
        return designer_id;
    }

    public void setDesigner_id(String designer_id) {
        this.designer_id = designer_id;
    }
}
