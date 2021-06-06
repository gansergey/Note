package net.gan.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import net.gan.note.controller.ListNoteController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ListNoteFragment extends Fragment {

    private LinearLayoutCompat linearListNote;

    private final NoteEntity note1 = new NoteEntity("Сходить в магазин", getCurrentDate(), "Купить, что-нибудь");
    private final NoteEntity note2 = new NoteEntity("Заплатить каско", getCurrentDate(), "на Ветеранов 166");
    private final NoteEntity note3 = new NoteEntity("Сдать ДЗ", getCurrentDate(), "Желательно 2");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_note, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        linearListNote = view.findViewById(R.id.linear_note_list);
        addNoteToList(note1);
        addNoteToList(note2);
        addNoteToList(note3);
    }

    private void addNoteToList(NoteEntity noteEntity) {
        Button button = new Button(getContext());
        button.setText(noteEntity.getName());
        button.setOnClickListener(v -> {
            ListNoteController listNoteController = (ListNoteController) getActivity();
            if (listNoteController != null) {
                listNoteController.openNote(noteEntity);
            }
        });
        linearListNote.addView(button);
    }

    private String getCurrentDate() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return (dateFormat.format(currentDate));
    }

}