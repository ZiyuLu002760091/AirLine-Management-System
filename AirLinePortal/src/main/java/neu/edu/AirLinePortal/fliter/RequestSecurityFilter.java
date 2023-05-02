package neu.edu.AirLinePortal.fliter;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author YUlia
 * @version 1.0
 */
@Component
public class RequestSecurityFilter implements Filter {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Token");
        String decodedToken = new String(Base64.getDecoder().decode(token));
        String[] splitted = decodedToken.split(".");
        if (splitted.length != 2) {
            return;
        }
        String username = splitted[0];
        String password = splitted[1];
        // check if match login user
        HttpSession session = request.getSession();
        if (username.equals(session.getAttribute("username"))
                && password.equals(session.getAttribute("password"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        // check if right user
        User user = userRepo.findByEmailAndPassword(username, password);
        if (null != user) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
