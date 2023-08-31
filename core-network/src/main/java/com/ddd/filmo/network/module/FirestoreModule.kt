package com.ddd.filmo.network.module

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirestoreModule {

    @Provides
    @Singleton
    fun provideDBInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}
