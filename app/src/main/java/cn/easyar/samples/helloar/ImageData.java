package cn.easyar.samples.helloar;

/**
 * Created by WuXiaolong
 * on: 2017-12-11 14:19
 * 个人博客: http://wuxiaolong.me
 */
public class ImageData {
    private String path;
    private int type;

    public static final int TYPE_ASSERTS = 0;
    public static final int TYPE_APP_FILE = 0;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
