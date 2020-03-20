package cl.dmix.desafiolatam.doggappmvp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.dmix.desafiolatam.doggappmvp.R;

public class BreedAdapter extends RecyclerView.Adapter<BreedAdapter.ViewHolder> {//Escribiendo esto, se generan errores que generan el resto del codigo!

    private Context context;
    private List<String> breedList;
    private OnItemClickListener listener;

    public BreedAdapter(List<String> breedList, Context context, OnItemClickListener listener) {
        this.breedList = breedList;
        this.context = context;
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void OnClick(ViewHolder viewHolder, String dogBreed);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Se crea una vista (view) inflándole el contenido de la item_list.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.breed_list_items,parent,false);
        return new ViewHolder(view); //Se retorna la vista inflada.
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Este método setea cada ViewHolder con la informacion que le corresponde, en este caso segun la lista.
        holder.setButtonText(breedList.get(position));
    }

    @Override
    public int getItemCount() {
        return breedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Declaro los objetos del viewHolder que se usaran en el Recycler.
        private Button btnBreedName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Enlazo los elementos declarados con las vistas del viewHolder con sus respectivos ID.
            btnBreedName = itemView.findViewById(R.id.btn_breedListItem);
            btnBreedName.setOnClickListener(this);
        }

        void setButtonText(String breedName) {
            btnBreedName.setText(breedName);
        }

        @Override
        public void onClick(View v) {
            listener.OnClick(this, getBreedByPosition(getAdapterPosition()));
        }
    }

    private String getBreedByPosition(int position){
        if(position != RecyclerView.NO_POSITION){
            return breedList.get(position);
        }else{
            return "No Breed in this position";
        }
    }
}
