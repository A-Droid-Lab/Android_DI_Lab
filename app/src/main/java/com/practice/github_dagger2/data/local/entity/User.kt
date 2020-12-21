package com.practice.github_dagger2.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userid")
    @SerializedName("id")
    var userId: Int,

    @SerializedName("login")
    var username: String?,

    @SerializedName("avatar_url")
    var avatarUrl: String?,

    @SerializedName("html_url")
    var htmlUrl: String

): Parcelable