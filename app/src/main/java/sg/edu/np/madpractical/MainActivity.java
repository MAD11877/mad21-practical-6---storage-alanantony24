package sg.edu.np.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        int position = intent.getIntExtra("Position", 0);
        u = ListActivity.userArrayList.get(position);
        TextView hello = findViewById(R.id.helloWorld);
        TextView desc = findViewById(R.id.description);
        hello.setText(u.name);
        desc.setText(u.description);
        setFollowBtn();
    }
    public void followToggler(View view) {
        u.followed = !u.followed;
        int id = u.getId();
        DBHandler db = new DBHandler(this);
        if(u.followed)
            Toast.makeText(this, "Followed", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"Unfollowed", Toast.LENGTH_SHORT).show();
        db.updateUser(u, id);
        setFollowBtn();
    }
    private void setFollowBtn() {
        Button b = findViewById(R.id.followBtn);
        if(u.followed) {
            b.setText("Unfollow");
        }
        else {
            b.setText("Follow");
        }
    }
}