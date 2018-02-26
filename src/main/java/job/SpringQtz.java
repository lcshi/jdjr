package job;

import java.util.Date;

/**
 * Created by shilc on 2016/3/8.
 */
public class SpringQtz {
    private static int counter=0;
    protected static void execute()
    {
        long ms=System.currentTimeMillis();
        System.out.print("\t\t"+new Date(ms));
        System.out.print("("+counter+++")");
    }
}
