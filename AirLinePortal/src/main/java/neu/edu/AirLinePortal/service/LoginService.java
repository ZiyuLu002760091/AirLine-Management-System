package neu.edu.AirLinePortal.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.repo.UserRepo;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author YUlia
 * @version 1.0
 */

@org.springframework.stereotype.Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    /**
     * As frontend may store the user info as session, we return the user info here
     */
    public User login(String email, String pwd, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        User user = userRepo.findByEmailAndPassword(email, pwd);
        if(null == user) {
            return null;
        }
        user.setPassword("***");
        httpSession.setAttribute("user",user);
        return user;
    }

    /**
     * This method is deprecated as the login session logic is implemented in React.
     * See React: login.js, loginService.js
     */
    @Deprecated
    public boolean isLogin(HttpServletRequest request) {
        return getLoginUser(request) != null;
    }

    /**
     * Same as above
     *
     * @see #isLogin(HttpServletRequest)
     */
    @Deprecated
    public User getLoginUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }


}
