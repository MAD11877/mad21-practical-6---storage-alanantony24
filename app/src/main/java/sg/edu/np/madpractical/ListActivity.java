package sg.edu.np.madpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    static ArrayList<User> userArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        User u = new User();
        DBHandler db = new DBHandler(ListActivity.this);
        userArrayList = db.getUser();
        //only run the 1st time to randomize(when app is installed)
        for (int i = 0; i < 20; i++) {
            u.name = "Name" + new Random().nextInt();
            u.description = "Description" + new Random().nextInt();
            u.id = i;
            u.followed = new Random().nextBoolean();
            userArrayList.add(u);
            db.addUser(u);
        }
        RecyclerView rv = findViewById(R.id.rv);
        UserAdapter userAdapter = new UserAdapter(userArrayList);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(userAdapter);
    }
}