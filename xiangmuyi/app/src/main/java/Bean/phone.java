package Bean;

/**
*作者:侯亮亮
*时间:2017/4/13 20:46
*类描述:获取侧滑数据的Bean对象
*/

public class phone {

    private String name;
    private int im;

    public phone(String name, int im) {
        this.name = name;
        this.im = im;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIm() {
        return im;
    }

    public void setIm(int im) {
        this.im = im;
    }
}
