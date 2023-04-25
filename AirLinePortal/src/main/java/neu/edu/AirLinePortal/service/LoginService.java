package neu.edu.AirLinePortal.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.repo.UserRepo;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

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
    public User login(String email, String pwd, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
//        if(isLogin(request)) {
//            return true;
//        }
        User user = userRepo.findByEmailAndPassword(email, pwd);
        if(null == user) {
            return null;
        }
        httpSession.setAttribute("user",user);
        return user;
    }

    public boolean isLogin(HttpServletRequest request) {
        return getLoginUser(request) != null;
    }

    public User getLoginUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }


}
