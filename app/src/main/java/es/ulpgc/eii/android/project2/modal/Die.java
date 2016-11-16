package es.ulpgc.eii.android.project2.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Field;
import java.util.Random;

import es.ulpgc.eii.android.project2.R;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class which shows the correct image for the different results of the die throwing //
class Die implements Parcelable {

    private int faces;

    Die() {
        setNumberOfFaces();
    }

    int getValue() {
        return showRandomInteger(1, faces);
    }

    // Returns a random number between two values //
    private int showRandomInteger(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long) end - (long) start + 1;
        Random random = new Random();
        long fraction = (long) (range * random.nextDouble());
        return (int) (fraction + start);
    }

    // It establishes the number of faces that the die has //
    private void setNumberOfFaces() {
        Field[] fields = R.drawable.class.getFields();
        int numberOfFacesDrawables = 0;
        for (Field field : fields) {
            // Take only those which name starts with "face" //
            if (field.getName().startsWith("face")) numberOfFacesDrawables++;
        }
        faces = numberOfFacesDrawables;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.faces);
    }

    protected Die(Parcel in) {
        this.faces = in.readInt();
    }

    public static final Parcelable.Creator<Die> CREATOR = new Parcelable.Creator<Die>() {
        @Override
        public Die createFromParcel(Parcel source) {
            return new Die(source);
        }

        @Override
        public Die[] newArray(int size) {
            return new Die[size];
        }
    };
}