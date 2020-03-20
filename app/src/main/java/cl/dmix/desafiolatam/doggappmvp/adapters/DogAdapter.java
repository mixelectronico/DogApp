package cl.dmix.desafiolatam.doggappmvp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cl.dmix.desafiolatam.doggappmvp.R;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {

    private Context context;
    private List<String> dogURLs;
    private onLongDogClickListener listener;

    public DogAdapter(List<String> dogURLs, Context context, onLongDogClickListener listener){
        this.context = context;
        this.dogURLs = dogURLs;
        this.listener = listener;
    }

    public interface onLongDogClickListener{
        void onLongClick(ViewHolder viewHolder, String dogURL);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_picture_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.dogPicture.getContext())
                .load(dogURLs.get(position))
                .centerCrop()
                .into(holder.dogPicture);
    }

    @Override
    public int getItemCount() {
        return dogURLs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private ImageView dogPicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dogPicture = itemView.findViewById(R.id.dog_pictures);
            dogPicture.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            listener.onLongClick(this, getUrlByPosition(getAdapterPosition()));
            return false;
        }

        private String getUrlByPosition(int adapterPosition) {
            return dogURLs.get(adapterPosition);
        }
    }
}
