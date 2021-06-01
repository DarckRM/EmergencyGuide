package com.emergencyguide.Entity;

/**
 * @author DarckLH
 * @date 2021/6/1 23:13
 * @Description 默认图片
 */
public class Image {

    private int id;
    private String imgname;
    private String path;
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imgname='" + imgname + '\'' +
                ", path='" + path + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
