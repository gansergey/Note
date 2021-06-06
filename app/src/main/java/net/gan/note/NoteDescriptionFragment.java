package net.gan.note;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import net.gan.note.controller.NoteController;


public class NoteDescriptionFragment extends Fragment{
    private static final String NOTE_ARGS_KEY = "NOTE_ARGS_KEY";

    public NoteDescriptionFragment() {
    }

    //Для передачи данных во фрагмент. т.к. нельзя передавать данные просто в конструкторе, про
    //повороте будет вылет т.к. фрагмент пересоздатся и в конструктор ни чего не придёт.
    //также нужно сохранить данные в Bundle
    public static NoteDescriptionFragment newInstance(NoteEntity noteEntity) {
        NoteDescriptionFragment noteDescriptionFragment = new NoteDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(NOTE_ARGS_KEY, noteEntity);
        noteDescriptionFragment.setArguments(args);
        return noteDescriptionFragment;
    }

    private EditText name;
    private EditText dateOfCreation;
    private EditText description;
    private Button saveNote;
    private TextView textWarning;
    private NoteEntity note = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof NoteController)) {
            throw new RuntimeException("Активити должна имплементироваться от NoteController");
        }
        if (getArguments() != null) {
            //Вытаскиваем и присваиваем класс из Bundle
            note = getArguments().getParcelable(NOTE_ARGS_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_description, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        init(view);

        name.setText(note.getName());
        dateOfCreation.setText(note.getDateOfCreation());
        description.setText(note.getDescription());
        saveNote.setOnClickListener(v -> {
            NoteController noteController = (NoteController) getActivity();
            if (name.length() == 0 || dateOfCreation.length() == 0 || description.length() == 0) {
                textWarning.setText(getResources().getString(R.string.text_warning));
            } else {
                if (noteController != null) {
                    noteController.saveResult(new NoteEntity(
                            name.getText().toString(),
                            dateOfCreation.getText().toString(),
                            description.getText().toString()
                    ));
                }
            }
        });
    }

    private void init(View view) {
        name = view.findViewById(R.id.edit_text_name);
        dateOfCreation = view.findViewById(R.id.edit_text_date);
        description = view.findViewById(R.id.edit_text_description);
        textWarning = view.findViewById(R.id.text_warning);
        saveNote = view.findViewById(R.id.button_save_note);
    }
}