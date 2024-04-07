package za.co.visionzar.store.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.ScalarToken;
import za.co.visionzar.store.POJO.User;
import za.co.visionzar.store.constents.StoreConstents;
import za.co.visionzar.store.dao.UserDao;
import za.co.visionzar.store.service.UserService;
import za.co.visionzar.store.utils.StoreUtils;

import java.util.Map;
import java.util.Objects;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return StoreUtils.getResponseEntity("Successfully registered",
                            HttpStatus.OK);
                } else {

                    return StoreUtils.getResponseEntity("Email already exist ",
                            HttpStatus.BAD_REQUEST);
                }
            } else {
                return StoreUtils.getResponseEntity(StoreConstents.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    return StoreUtils.getResponseEntity(StoreConstents.SOMETHING_WENT_WRONG,
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }
    private User getUserFromMap(Map<String,String>requestMap){
        User user =new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contact"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;

    }
}
