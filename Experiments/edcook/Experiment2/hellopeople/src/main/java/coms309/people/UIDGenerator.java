//package coms309.people;
//
//import java.util.Random;
//
//public class UIDGenerator {
//    public static String generateUID(){
//        //generate a UID that is at least 8 chars long
//        Random random = new Random();
//        //random.nextInt(90000000) --> generates a random int btwn 0(inlcuded) and 90000000(excluded)
//        // adding 1000000 --> this makes sure that the resulting number is at least 8 digits long
//        // example: random.nextInt(90000000) generates 12345 then 10000000 + 12345 = 10012345
//        int uid = 10000000 + random.nextInt(90000000); // this ensures that it is 8 digits
//        return String.valueOf(uid);
//    }
//}
