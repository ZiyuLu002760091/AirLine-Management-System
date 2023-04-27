package neu.edu.AirToronto.service;

import neu.edu.AirToronto.entities.LoginEntity;
import neu.edu.AirToronto.entities.User;
import neu.edu.AirToronto.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YUlia
 * @version 1.0
 */
@Service
public class AirlineLoginService {

    @Autowired
    private UserRepo userRepo;

    public User login(LoginEntity loginEntity) {
        return userRepo.findByEmailAndPassword(loginEntity.getEmail(), loginEntity.getPassword());
    }
}
