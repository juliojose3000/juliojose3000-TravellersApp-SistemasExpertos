package com.example.travellersapp_sistemasexpertos.domain;

public class ListViewItem {

    private int imgFoto;

    private String title;

    private String price;

    public ListViewItem(int imgFoto, String title, String price) {
        this.imgFoto = imgFoto;
        this.title = title;
        this.price = price;
    }

    public int getImgFoto() {return imgFoto;}

    public String getTitle() {return title;}

    public String getPrice() {return price;}

    @Override
    public String toString() {
        return "ListViewItem{" +
                "imgFoto=" + imgFoto +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
