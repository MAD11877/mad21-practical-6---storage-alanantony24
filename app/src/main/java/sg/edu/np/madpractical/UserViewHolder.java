package sg.edu.np.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public TextView nameText;
    public TextView descText;
    public ImageView profilePic;
    public UserViewHolder(View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.nameText);
        descText = itemView.findViewById(R.id.descriptionText);
        profilePic = itemView.findViewById(R.id.profilePic);
    }
}
