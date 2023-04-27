package neu.edu.AirLinePortal.service;

import jakarta.servlet.http.HttpServletRequest;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.entities.UserCredentialUpdate;
import neu.edu.AirLinePortal.entities.UserUpdate;
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

    /**
     * update user without email and password
     */
    public User update(UserUpdate user) throws Exception {
        System.out.println(user.toString());
        Optional<User> optional = userRepo.findById(user.getUuid());
        if (optional.isPresent()) {
            User userExist = optional.get();
            userExist.setDob(user.getDob());
            userExist.setFname(user.getFname());
            userExist.setLname(user.getLname());
            userExist.setPhoneno(user.getPhoneno());
            userExist.setGender(User.Gender.valueOf(user.getGender()));
            userExist.setUuid(user.getUuid());
            userRepo.save(userExist);
            return userExist;
        } else {
            throw new RuntimeException("User not exist");
        }
    }

    /**
     * update user  email and password
     */
    public User updateCredential(UserCredentialUpdate user) throws Exception {
        Optional<User> optional = userRepo.findById(user.getUuid());
        if (optional.isPresent()) {
            User userExist = optional.get();
            userExist.setEmail(user.getEmail());
            userExist.setPassword(CommonUtils.md5(user.getPassword()));
            userExist.setUuid(user.getUuid());
            userRepo.save(userExist);
            return userExist;
        } else {
            throw new RuntimeException("User not exist");
        }
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
