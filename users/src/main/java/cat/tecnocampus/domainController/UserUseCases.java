package cat.tecnocampus.domainController;

import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.persistence.UserLabDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by roure on 14/09/2017.
 *
 * All methods update the database
 */
@Service("userUseCases")
public class UserUseCases {

    private final UserLabDAO userLabDAO;

    public UserUseCases(UserLabDAO UserLabDAO) {
        this.userLabDAO = UserLabDAO;
    }

    public UserLab createUser(String username, String name, String secondName, String email) {
        UserLab userLab = new UserLab.UserLabBuilder(username, email).name(name).secondName(secondName).build();
        registerUser(userLab);
        return userLab;
    }

    //The @Transactiona annotation states that saveUser is a transaction. So ,if a unchecked exception is signaled
    // (and not cached) during the saveUser method the transaction is going to rollback
    @Transactional
    public int registerUser(UserLab userLab) {
        return userLabDAO.insert(userLab);
    }

    public int deleteUser(String username) {
        return userLabDAO.delete(username);
    }


    //Note that users don't have their notes with them
    public List<UserLab> getUsers() {
        return userLabDAO.findAll();
    }

    public UserLab getUser(String userName) {
        return userLabDAO.findByUsername(userName);
    }

}
