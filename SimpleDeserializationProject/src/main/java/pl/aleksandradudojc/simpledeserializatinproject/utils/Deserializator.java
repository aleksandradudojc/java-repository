package pl.aleksandradudojc.simpledeserializatinproject.utils;




import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import pl.aleksandradudojc.simpledeserializatinproject.model.InstagramProfile;

public class Deserializator {

    public static InstagramProfile deserialize(String jsonProfileFromFile) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        InstagramProfile instagramProfile = null;

        try {
            instagramProfile = mapper.readValue(jsonProfileFromFile, InstagramProfile.class);
        } catch (IOException e) {
            System.out.println("We are sorry, mapper was unable to deserialize jsonProfileFile to InstagramProfile.class(means objects):"+e.getMessage());
        }
        return instagramProfile;
    }
}
