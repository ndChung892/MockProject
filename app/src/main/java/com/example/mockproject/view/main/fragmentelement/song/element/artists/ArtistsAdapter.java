package com.example.mockproject.view.main.fragmentelement.song.element.artists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.R;
import com.example.mockproject.databinding.ItemArtistsBinding;

import java.util.List;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ViewHolder> {
    ItemArtistsBinding mBinding;
    List<ArtistsModel> artistsModelList;
    Context mContext;

    public ArtistsAdapter(List<ArtistsModel> artistsModelList, Context mContext) {
        this.artistsModelList = artistsModelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = ItemArtistsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArtistsModel artists = artistsModelList.get(position);
        holder.mBinding.imgArtists.setImageResource(artists.getImgArtists());
        holder.mBinding.txtNameArtists.setText(artists.getNameArtist());
        holder.mBinding.txtNumSongArtists.setText(artists.getNumSongs());
        holder.mBinding.txtNumAlbums.setText(artists.getNumAlbums());

    }

    @Override
    public int getItemCount() {
        if(artistsModelList!=null) {
            return artistsModelList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        ItemArtistsBinding mBinding;
        public ViewHolder(@NonNull ItemArtistsBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            mBinding.menuItemArtists.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            showPopupMenu(view);

        }
        private void showPopupMenu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
//            popupMenu.
            popupMenu.inflate(R.menu.menu_item_albums);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            Toast.makeText(mContext, "You Clicked: "+ menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
