package edu.hljcu.encodingFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
    private FilterConfig filterConfig;
    private String encoding;
    public void destroy() {
        this.filterConfig = null;

    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1,
            FilterChain arg2) throws IOException, ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        arg0.setCharacterEncoding(encoding);
        arg1.setCharacterEncoding(encoding);
        arg2.doFilter(arg0, arg1);
    }

    public void init(FilterConfig arg0) throws ServletException {
        this.filterConfig = arg0;

    }

}

