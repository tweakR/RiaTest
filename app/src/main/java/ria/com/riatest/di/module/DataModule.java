package ria.com.riatest.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ria.com.riatest.api.RestApi;
import ria.com.riatest.db.DbManager;
import ria.com.riatest.interactor.DataInteractor;

@Module
public class DataModule {
    @Provides
    @Singleton
    DataInteractor provideDataInteractor(DbManager dbManager, RestApi api) {
        return new DataInteractor(dbManager, api);
    }
}
