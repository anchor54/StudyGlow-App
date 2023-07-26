package com.example.studyglows.screens.home.common.models

import com.google.gson.annotations.SerializedName

class PopularSubjects(
    @SerializedName("popularSubjects") val popularSubjectList: List<SubjectwiseCourse>
)

class SubjectwiseCourse(
    @SerializedName("subjectName") val subjectName: String?,
    @SerializedName("courses") val courses: List<Course>?
)