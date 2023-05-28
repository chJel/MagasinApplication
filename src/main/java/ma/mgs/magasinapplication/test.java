package ma.mgs.magasinapplication;

import ma.mgs.magasinapplication.entities.User;
import ma.mgs.magasinapplication.service.MagasinService;
import ma.mgs.magasinapplication.service.UserService;

public class test {
    public static void main(String[] args) {
        MagasinService magasinService= new MagasinService();
        magasinService.findAll().forEach(System.out::println);

        UserService userService=new UserService();
        System.out.println(userService.auth(new User("admin","1234")));
    }



}
