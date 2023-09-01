package com.ddd.filmo.login.data.remote

import com.ddd.filmo.login.data.model.UserResponse
import com.ddd.filmo.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface UserRemoteDataSource {
    suspend fun getUser(): UserResponse
    suspend fun isExitUser(userId: String): Boolean
    suspend fun saveUser(user: User): Boolean

    suspend fun updateNickName(userId: String, nickName: String): Boolean
    suspend fun deleteUser(): Boolean
}

class UserRemoteDataSourceImpl @Inject constructor(
    private val firebaseDB: FirebaseFirestore,
) : UserRemoteDataSource {
    override suspend fun getUser(): UserResponse {
        val userResponse = firebaseDB.collection("User").document("117111581200385730511").get().await().toObject<UserResponse>()
        return userResponse?: UserResponse()
    }

    override suspend fun isExitUser(userId: String): Boolean =
        suspendCancellableCoroutine<Boolean> { continaution ->
            val collection = firebaseDB.collection("User")
            collection.document(userId).get().addOnSuccessListener {
                if (it.exists()) {
                    continaution.resume(true)
                } else {
                    continaution.resume(false)
                }
            }.addOnFailureListener { e ->
                continaution.resumeWithException(e)
            }
        }

    override suspend fun saveUser(user: User): Boolean =
        suspendCancellableCoroutine<Boolean> { continuation ->
            firebaseDB.collection("User")
                .document(user.userId ?: return@suspendCancellableCoroutine).set(user)
                .addOnSuccessListener {
                    continuation.resume(true)
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }

    override suspend fun updateNickName(userId: String, nickName: String): Boolean =
        suspendCancellableCoroutine<Boolean> { continaution ->
            val userRef =
                firebaseDB.collection("User").document(userId ?: return@suspendCancellableCoroutine)

            firebaseDB.runTransaction { transaction ->
                val snapshot = transaction.get(userRef)
                val serverNickName = snapshot.getString("nickName")

                if (serverNickName != nickName || nickName.isNotBlank()) {
                    transaction.update(
                        userRef,
                        "nickName",
                        nickName,
                    )
                }
            }.addOnSuccessListener {
                continaution.resume(true)
            }.addOnFailureListener {
                continaution.resumeWithException(it)
            }.addOnCanceledListener {
            }
        }

    override suspend fun deleteUser(): Boolean {
        TODO("Not yet implemented")
    }
}
