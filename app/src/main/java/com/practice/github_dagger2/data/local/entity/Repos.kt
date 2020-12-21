package com.practice.github_dagger2.data.local.entity

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "repos")
data class Repos(
    @PrimaryKey
    @SerializedName("id")
    var repoId: Int,

    @SerializedName("name")
    var name: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("stargazers_count")
    var starCount: Int,

    @SerializedName("language")
    var language: String?,

    @SerializedName("open_issues")
    var openIssues: Int,

    @Embedded
    @SerializedName("owner")
    var user: User?

) : Parcelable