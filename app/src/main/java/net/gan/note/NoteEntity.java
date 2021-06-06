package net.gan.note;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteEntity implements Parcelable {

    private final String name;
    private final String dateOfCreation;
    private final String description;


    public NoteEntity(String name, String dateOfCreation, String description) {
        this.name = name;
        this.dateOfCreation = dateOfCreation;
        this.description = description;
    }

    protected NoteEntity(Parcel in) {
        name = in.readString();
        dateOfCreation = in.readString();
        description = in.readString();
    }

    public static final Creator<NoteEntity> CREATOR = new Creator<NoteEntity>() {
        @Override
        public NoteEntity createFromParcel(Parcel in) {
            return new NoteEntity(in);
        }

        @Override
        public NoteEntity[] newArray(int size) {
            return new NoteEntity[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(dateOfCreation);
    }
}
