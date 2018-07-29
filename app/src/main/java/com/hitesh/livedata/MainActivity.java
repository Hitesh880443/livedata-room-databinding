package com.hitesh.livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hitesh.livedata.databinding.ActivityMainBinding;
import com.hitesh.livedata.mynotes.Note;
import com.hitesh.livedata.mynotes.NoteViewModel;
import com.hitesh.livedata.mynotes.NotesDatabase;
import com.hitesh.livedata.mynotes.NotesRecylerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private NotesDatabase mNoteDatabase;
    private AddNoteView mAddNoteDialog;
    private NoteViewModel mNoteViewModel;
    private Button btn_addNote;
    private NotesRecylerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpView();
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        mNoteViewModel.getNoteList().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                if (notes != null && notes.size() > 0) {
                    binding.setNoteViewModel(mNoteViewModel);
                    Log.d("Size", String.valueOf(notes.size()));
                    mAdapter.addNotes(notes);
                }
                else
                    Log.d("Size", "null");

            }
        });

    }

    private void setUpView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });

        binding.rvNotesList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NotesRecylerViewAdapter(new ArrayList<Note>());
        binding.rvNotesList.setAdapter(mAdapter);

    }

    public void addNote() {
        mAddNoteDialog = new AddNoteView();
        mAddNoteDialog.show(getSupportFragmentManager(), "addNote");
    }


}
