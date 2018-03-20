package com.example.wzw.biubiubiu;

/**
 * Created by wzw on 2018/3/20.
 */

public class Bean
{
    Integer imageId;
    String conte;
    public Bean(Integer imageId, String conte) {
        this.imageId = imageId;
        this.conte = conte;
    }
    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getConte() {
        return conte;
    }

    public void setConte(String conte) {
        this.conte = conte;
    }

    @Override
    public String toString() {
        return "Bean{" + "imageId=" + imageId + ", conte='" + conte + '\'' + '}';
    }
}
