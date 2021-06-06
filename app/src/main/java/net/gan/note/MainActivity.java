package net.gan.note;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.gan.note.controller.ListNoteController;
import net.gan.note.controller.NoteController;

public class MainActivity extends AppCompatActivity implements NoteController, ListNoteController {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        NoteEntity noteEntity = new NoteEntity(null, null, null);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_list_notes:
                            break;
                        case R.id.action_add_new_note:
                            openNote(noteEntity);
                            break;
                        case R.id.action_about:
                            break;
                    }
                    return false;
                });
    }

    private void init() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ListNoteFragment())
                .commit();
    }

    @Override
    public void saveResult(NoteEntity noteEntity) {

    }

    @Override
    public void openNote(NoteEntity noteEntity) {
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(isLandscape ? R.id.fragment_container_land : R.id.fragment_container,
                        NoteDescriptionFragment.newInstance(noteEntity))
                .addToBackStack(null)
                .commit();
    }
}