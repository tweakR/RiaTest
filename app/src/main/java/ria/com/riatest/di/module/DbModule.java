package ria.com.riatest.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.android.BuildConfig;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.rx.RxSupport;
import io.requery.rx.SingleEntityStore;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;
import ria.com.riatest.constant.Constant;
import ria.com.riatest.db.DbManager;
import ria.com.riatest.db.entities.Models;

@Module
public class DbModule {

    @Provides
    @Singleton
    DbManager provideDataBase(Context context) {
        try {
            SingleEntityStore<Persistable> dataStore;
            DatabaseSource databaseSource = new DatabaseSource(context, Models.DEFAULT, Constant.DB_NAME, 1);
            if (BuildConfig.DEBUG) {
                databaseSource.setTableCreationMode(TableCreationMode.DROP_CREATE);
            }
            Configuration configuration = databaseSource.getConfiguration();
            dataStore = RxSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));

            return new DbManager(dataStore);

        } catch (Exception e) {
            return new DbManager(null);
        }
    }
}
