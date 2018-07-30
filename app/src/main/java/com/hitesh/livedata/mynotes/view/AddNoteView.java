package com.hitesh.livedata.mynotes.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hitesh.livedata.R;
import com.hitesh.livedata.mynotes.db.Note;
import com.hitesh.livedata.mynotes.db.NotesDatabase;

import java.util.Date;

public class AddNoteView extends DialogFragment {


    public NotesDatabase mNoteDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_note_view_layout, container);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        final EditText editText = (EditText) view.findViewById(R.id.edt_comment);
        Button button1 = (Button) view.findViewById(R.id.buttonSubmit);
        Button button2 = (Button) view.findViewById(R.id.buttonCancel);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // DO SOMETHINGS
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    initialiseDB();
                    addNote(editText.getText().toString());
                    dismiss();
                }
                else{
                    Toast.makeText(getActivity(), "Note can't be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initialiseDB() {
        mNoteDatabase = mNoteDatabase.getDataBase(getActivity());
    }

    public void addNote(final String msg) {
        new Thread(new Runnable() {
            public void run() {
                Note n = new Note();
                n.setNoteDate(new Date());
                n.setNoteMsg(msg);
                mNoteDatabase.daoAccess().insertSingleNote(n);
            }
        }).start();
    }
}
