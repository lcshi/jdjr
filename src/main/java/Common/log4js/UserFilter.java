package Common.log4js;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by shilc on 2016/3/22.
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
         Logger logger = Logger.getLogger(UserFilter.class);
        HttpSession session=req.getSession();
        if(session==null)
        {
            System.out.println("初始化了admin");
            MDC.put("userid","admin");
            logger.error("初始化admin的信息");
        }else
        {
            String userid=(String)session.getAttribute("userid");
            if(userid==null||userid.length()<0)
            {
                System.out.println("初始化了admin");
                try {
                    MDC.put("userid", "admin");
                    String a=MDC.get("userid").toString();
                    logger.error("初始化admin的信息");
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
