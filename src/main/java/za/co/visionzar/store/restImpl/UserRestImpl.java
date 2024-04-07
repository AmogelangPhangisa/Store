package za.co.visionzar.store.restImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import za.co.visionzar.store.constents.StoreConstents;
import za.co.visionzar.store.rest.UserRest;
import za.co.visionzar.store.service.UserService;
import za.co.visionzar.store.utils.StoreUtils;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StoreUtils.getResponseEntity(StoreConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
