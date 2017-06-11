package ria.com.riatest.db.entities;

import android.databinding.Observable;
import android.os.Parcelable;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Persistable;

@Entity
public interface CityData extends Observable, Parcelable, Persistable {

    @Key
    @Generated
    int getId();

    String getCity();

}
