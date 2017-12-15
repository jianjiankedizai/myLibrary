package cn.easyar.samples.helloar;

import java.util.List;

/**
 * Created by WuXiaolong
 * on: 2017-12-11 15:01
 * 个人博客: http://wuxiaolong.me
 */
public class ObjData {

    private String obj;
    private String mtl;
    private List<String> texures;

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getMtl() {
        return mtl;
    }

    public void setMtl(String mtl) {
        this.mtl = mtl;
    }

    public List<String> getTexures() {
        return texures;
    }

    public void setTexures(List<String> texures) {
        this.texures = texures;
    }
}
