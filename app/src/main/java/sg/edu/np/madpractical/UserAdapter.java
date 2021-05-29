package sg.edu.np.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> list;
    public UserAdapter(ArrayList<User> uList) {
        list = uList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == 0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_userinfo_alt, parent, false);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_userinfo, parent, false);
        }
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User u = list.get(position);
        holder.nameText.setText(u.name);
        holder.descText.setText(u.description);
        holder.profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(holder.profilePic.getContext());
                alert.setTitle("Profile")
                .setMessage(u.name.toString())
                .setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent newIntent = new Intent(holder.profilePic.getContext(), MainActivity.class);
                        newIntent.putExtra("Id", position);
                        holder.profilePic.getContext().startActivity(newIntent);
                    }
                })
                .setNegativeButton("Close", null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        String name = list.get(position).name;
        if (name.charAt(name.length() - 1) == '7'){
            return 0;
        }
        else{
            return 1;
        }
    }
}
