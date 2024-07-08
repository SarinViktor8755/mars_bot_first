package models;



import java.util.ArrayList;

public class Users {
    static ArrayList<UserExte> users = new ArrayList<>();

    static public void add_km_for_user(int km, com.pengrad.telegrambot.model.User user) {
        int id = fine_user(user.id());
        if (id == -1) users.add(new UserExte(user.id(),user));
        id = fine_user(user.id());
        users.get(id).add_km(km);
    //    System.out.println(users);
    }

    static private int fine_user(Long id_user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id().equals(id_user)) return i;
        }
        return -1;
    }


}
