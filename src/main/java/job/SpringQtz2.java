package job;

import java.util.Date;

/**
 * Created by shilc on 2016/3/8.
 */
public class SpringQtz2 {
    private  static int counter=0;
    protected   static void sayHello()
    {
        System.out.println(new Date()+" 你好！第"+counter+++"世界!");
    }
}
