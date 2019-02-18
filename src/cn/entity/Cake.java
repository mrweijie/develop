package cn.entity;


public class Cake {
    /**
     * id
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 价格
     */
    private double price;
    /**
     * 描述
     */
    private String description;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 主页图
     */
    private String pic;
    /**
     * 品牌
     */
    private String pingpai;
    /**
     * 规格
     */
    private String guige;
    /**
     * 口味
     */
    private String kouwei;
    private String[] piclist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    public String getPingpai() {
        return pingpai;
    }

    public void setPingpai(String pingpai) {
        this.pingpai = pingpai;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getKouwei() {
        return kouwei;
    }

    public void setKouwei(String kouwei) {
        this.kouwei = kouwei;
    }

    public String[] getPiclist() {
        return piclist;
    }

    public void setPiclist(String[] piclist) {
        this.piclist = piclist;
    }

    public Cake() {
    }

    public Cake(Integer id, String name, double price, String description, Integer stock, String pic, String pingpai, String guige, String kouwei, String[] piclist) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.pic = pic;
        this.pingpai = pingpai;
        this.guige = guige;
        this.kouwei = kouwei;
        this.piclist = piclist;
    }
}


