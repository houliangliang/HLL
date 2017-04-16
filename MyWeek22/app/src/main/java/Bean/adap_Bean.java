package Bean;

/**
 * date: 2017/4/15
 * author:侯亮亮候亮亮
 */

public class adap_Bean {

    private String title;
    private String baike;
    private String price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBaike() {
        return baike;
    }

    public void setBaike(String baike) {
        this.baike = baike;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                 title + "\r\n" +
                 baike +  "\r\n" +
                 price +  "\r\n";

    }
}
