package com.hitesh.livedata.mynotes.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hitesh.livedata.R;
import com.hitesh.livedata.databinding.ItemNoteLayoutBinding;
import com.hitesh.livedata.mynotes.db.Note;

import java.util.List;

public class NotesRecylerViewAdapter extends RecyclerView.Adapter<NotesRecylerViewAdapter.MyViewHolder> {

    private List<Note> mList;
    private LayoutInflater mInflater;

    public NotesRecylerViewAdapter(List<Note> mList) {
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        ItemNoteLayoutBinding binding
                = DataBindingUtil.inflate(mInflater, R.layout.item_note_layout, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.binding.setNote(mList.get(position));

    }

    @Override
    public int getItemCount() {
        if (mList != null && mList.size() > 0)
            return mList.size();
        else
            return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemNoteLayoutBinding binding;

        public MyViewHolder(final ItemNoteLayoutBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    public void addNotes(List<Note> noteList) {
        this.mList = noteList;
        notifyDataSetChanged();
    }
}
