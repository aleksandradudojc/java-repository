package pl.aleksandradudojc.simpledeserializatinproject;

import pl.aleksandradudojc.simpledeserializatinproject.model.InstagramProfile;
import pl.aleksandradudojc.simpledeserializatinproject.utils.Deserializator;
import pl.aleksandradudojc.simpledeserializatinproject.utils.FileReader;

public class ApplicationRunner {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader();
        String jsonProfileFromFile = fileReader.getFileContent("profile.json");
        System.out.println("File has been read from file succesfully: " + jsonProfileFromFile);

        InstagramProfile instagramProfile = Deserializator.deserialize(jsonProfileFromFile);
        System.out.println("Yeah, we are here! Mapper did stuff well done");

        System.out.println("Has this profile suggested instagram contacts? " + instagramProfile.getShow_suggested_profiles());
        System.out.println("Full name of our user: " + instagramProfile.getGraphql().getUser().getFull_name());
        System.out.println(("Number of followed by: "+ instagramProfile.getGraphql().getUser().getEdge_followed_by().getCount()));
        System.out.println("Number of follow people: "+ instagramProfile.getGraphql().getUser().getEdge_follow().getCount());
        System.out.println("Biography: "+ instagramProfile.getGraphql().getUser().getBiography());
        System.out.println("Id numer of the profile: "+instagramProfile.getGraphql().getUser().getId());
    }
}