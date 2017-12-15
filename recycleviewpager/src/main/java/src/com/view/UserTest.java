package src.com.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuXiaolong
 * on: 2017-12-13 17:17
 * 个人博客: http://wuxiaolong.me
 */
public class UserTest implements IModel {
    private List<User> data = new ArrayList();
    private static int time = 0;

    public UserTest() {
        time++;
        for (int i = 0; i < time; i++) {
            data.add(new User());
        }
    }

    @Override
    public String getTitle() {
        return "diyigehoaba";
    }

    @Override
    public List getData() {
        return data;
    }

    static class User {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String name = "往好处那个键";

    }


}
