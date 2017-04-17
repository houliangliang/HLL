package Bean;

import android.graphics.Bitmap;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
*作者:侯亮亮
*时间:2017/4/16 15:17
*类描述:user注册表对象
*/
@Table(name = "UserBean",onCreated = "CREATE UNIQUE INDEX index_name ON UserBean(phone)")
public class UserBean {
    /**
     * name = "id"：数据库表中的一个字段
     * isId = true：是否是主键
     * autoGen = true：是否自动增长
     * property = "NOT NULL"：添加约束
     */
    @Column(name = "id",isId = true,autoGen = true,property = "NOT NULL")
     private int id;
    @Column(name = "loginpaw")
    private String loginpaw;

    @Column(name = "phone")

    private String phone;
    @Column(name = "sign")
    private int sign;
    @Column(name = "name")
    private String name;
    @Column(name = "bitmap")
    private Bitmap bitmap;

    public UserBean() {
        super();
    }

    public UserBean(int id, String phone, String loginpaw, int sign, String name, Bitmap bitmap) {
        this.id = id;
        this.phone = phone;
        this.loginpaw = loginpaw;
        this.sign = sign;
        this.name = name;
        this.bitmap = bitmap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginpaw() {
        return loginpaw;
    }

    public void setLoginpaw(String loginpaw) {
        this.loginpaw = loginpaw;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
