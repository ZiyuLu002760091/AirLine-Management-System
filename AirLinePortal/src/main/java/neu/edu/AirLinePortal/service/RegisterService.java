package neu.edu.AirLinePortal.service;

import jakarta.transaction.Transactional;
import neu.edu.AirLinePortal.entities.User;
import neu.edu.AirLinePortal.entities.UserRequestEntity;
import neu.edu.AirLinePortal.repo.UserRepo;
import neu.edu.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author YUlia
 * @version 1.0
 */
@Service
public class RegisterService {
    @Autowired
     private UserRepo userRepo;

    /**
     * return value:
     * if successful, return empty string as there's no error message
     * if not, return any error message as string
     */
    public String regiSuccess(UserRequestEntity user) throws Exception{
        if(null != user) {
            user.setUuid(UUID.randomUUID().toString());
            if(null == userRepo.findByEmail(user.getEmail())) {
                User jpaUser = new User();
                jpaUser.setUuid(user.getUuid());
                jpaUser.setDob(user.getDob());
                jpaUser.setFname(user.getFname());
                jpaUser.setLname(user.getLname());
                jpaUser.setGender(User.Gender.valueOf(user.getGender()));
                // only available for customer registration
                // warn: change this if copy code to airline system
                jpaUser.setRole(User.Role.customer);
                // must be active for here
                jpaUser.setStatus(User.Status.active);
                jpaUser.setPhoneno(user.getPhoneno());
                jpaUser.setPassword(CommonUtils.md5(user.getPassword()));
                jpaUser.setEmail(user.getEmail());
                userRepo.save(jpaUser);
                return "";
            } else {
                return "email already exists";
            }
        }
        return "registration failed";
    }
}
