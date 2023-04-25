package neu.edu.AirLinePortal.service;

import jakarta.servlet.http.HttpServletRequest;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.repo.UserRepo;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author YUlia
 * @version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Deprecated, use RegisterService instead
     *
     * @see neu.edu.AirLinePortal.service.RegisterService
     */
    public void createUser(User user) throws Exception {
        //md5 the password customers give and replace the old one
        user.setPassword(CommonUtils.md5(user.getPassword()));

        //do the validation here

        userRepo.save(user);
    }

    public User findUserById(String id) {
        Optional<User> optional = userRepo.findById(id);
        return optional.orElse(null);
    }

    /**
     * Not used for login
     * <p>
     * For Login:
     *
     * @see LoginService#login(String, String, HttpServletRequest)
     */
    public User findUserByEmailAndPassword(String email, String password) {
        return userRepo.findByEmailAndPassword(email, password);
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
