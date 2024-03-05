package com.example.studyglows.screens.auth.common.models

data class User(
    val active: Boolean? = null,
    val additional_details: String? = null,
    val address: String? = null,
    val city: String? = null,
    val designation: String? = null,
    val email: String? = null,
    val email_verified: Boolean? = null,
    val first_login: Boolean? = null,
    val first_name: String? = null,
    val groups: List<Any>? = null,
    val id: Int,
    val is_superuser: Boolean? = null,
    val joining_date: String? = null,
    val last_login: String? = null,
    val last_name: String? = null,
    val phone: String? = null,
    val phone_verified: Boolean? = null,
    val resource: String? = null,
    val role: String? = null,
    val state: String? = null,
    val user_permissions: List<Any>? = null,
    val username: String? = null
)