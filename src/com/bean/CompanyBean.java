package com.bean;

public class CompanyBean {
//    company_id,title(name),intro,logo,desc,photo1,photo2;
    int company_id;
    String title;
    String desc;
    String logo;
    String image1_path;
    String image2_path;
    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImage1_path() {
        return image1_path;
    }

    public void setImage1_path(String image1_path) {
        this.image1_path = image1_path;
    }

    public String getImage2_path() {
        return image2_path;
    }

    public void setImage2_path(String image2_path) {
        this.image2_path = image2_path;
    }

}
