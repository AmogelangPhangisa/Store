package za.co.visionzar.store.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StoreUtils {
    public static ResponseEntity<String> getResponseEntity;

    private StoreUtils() {


    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage,  HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}",httpStatus);

    }

}
