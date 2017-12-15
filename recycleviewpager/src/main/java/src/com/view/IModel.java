package src.com.view;

import java.util.List;

/**
 * Created by WuXiaolong
 * on: 2017-12-13 15:53
 * 个人博客: http://wuxiaolong.me
 */
public interface IModel<T extends List> {
    String getTitle();

    T getData();


}
