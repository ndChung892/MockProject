package com.example.mockproject.view.main.fragmentelement.song.element.albums;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mockproject.R;
import com.example.mockproject.databinding.ItemAlbumsBinding;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {
    ItemAlbumsBinding mBinding;
    List<AlbumsModel> albumsModelList;
    OnclickAlbums mOnclickAlbums;
    private Context mContext;

    public AlbumsAdapter(List<AlbumsModel> albumsModelList, Context context ) {
        this.albumsModelList = albumsModelList;
        mContext = context;
//        this.mOnclickAlbums = onclickAlbums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = ItemAlbumsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumsModel albums = albumsModelList.get(position);
        if (albums == null) {
            return;
        }
        holder.mBinding.nameAlbums.setText(albums.getNameAlbums());
        holder.mBinding.imgAlbums.setImageResource(albums.getImgAlbums());
        holder.mBinding.singersAlbums.setText(albums.getSingerAlbums());
        holder.mBinding.songNumAlbum.setText(albums.getNumSong());
        holder.mBinding.imgMenuAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlbumsAdapter.OnclickAlbums(albums);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (albumsModelList != null) {
            return albumsModelList.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{
        ItemAlbumsBinding mBinding;

        public ViewHolder(@NonNull ItemAlbumsBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            mBinding.imgMenuAlbum.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            Log.d("", "onClickItem Albums: ");
            showPopupMenu(view);
        }
        private void showPopupMenu(View view){
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
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
    public interface OnclickAlbums{
        void onClickitem(AlbumsModel albumsModel);
    }
}
