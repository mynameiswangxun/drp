package drp.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "CharsetEncodingFilter",urlPatterns = "*.jsp"
        ,initParams =@WebInitParam(name="encoding",value = "utf-8"))
/**
 * 采用Filter统一设置字符集
 */
public class CharsetEncodingFilter implements Filter {
    private String encoding = "";
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        this.encoding = config.getInitParameter("encoding");
    }

}
