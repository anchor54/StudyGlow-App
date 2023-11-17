package com.example.studyglows.network.apis

import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.AlertItemResponse
import com.example.studyglows.screens.editorial_currentaffair.vacancies.model.NotificationItem
import retrofit2.Response
import javax.inject.Inject

class LatestVacancyApis @Inject constructor(){
    suspend fun getJobAlertsAndUpdates(): Response<List<AlertItemResponse>> =
        Response.success(
            listOf(
                AlertItemResponse(
                    title = "Bihar DLRS Various Post Online Form 2022",
                    tag = "BIHAR",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "SBI PO Online Form 2022",
                    tag = "SBI",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "RSMSSB Common Eligibility Test CET Online Form 2022",
                    tag = "RSMSSB",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "UPSC GEO Scientist Online Form 2022",
                    tag = "UPSC",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "BPSC CDPO 2021 Mains Online Form 2022",
                    tag = "BPSC",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "BPSC APO 2020 Mains Online Form",
                    tag = "BPSC",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "Bihar DLRS Various Post Online Form 2022",
                    tag = "BIHAR",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "SBI PO Online Form 2022",
                    tag = "SBI",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "RSMSSB Common Eligibility Test CET Online Form 2022",
                    tag = "RSMSSB",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "UPSC GEO Scientist Online Form 2022",
                    tag = "UPSC",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "BPSC CDPO 2021 Mains Online Form 2022",
                    tag = "BPSC",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "BPSC APO 2020 Mains Online Form",
                    tag = "BPSC",
                    date = 1697826600000
                )
            )
        )

    suspend fun getAdmitCardAlertsAndUpdates(): Response<List<AlertItemResponse>> =
        Response.success(
            listOf(
                AlertItemResponse(
                    title = "UPNHM Mental Health Various Post Admit Card 2022",
                    tag = "UPNHM",
                ),
                AlertItemResponse(
                    title = "Indian Navy SSR Admit Card 2022",
                    tag = "INDIAN",
                ),
                AlertItemResponse(
                    title = "IBPS RRB XI Officer Scale 1 Phase II Admit Card 2022",
                    tag = "IBPS",
                ),
                AlertItemResponse(
                    title = "UPPSC Staff Nurse 2021 Document Verification Letter",
                    tag = "UPPSC",
                ),
                AlertItemResponse(
                    title = "Supreme Court Junior Court Assistant Admit Card 2022",
                    tag = "SC",
                ),
                AlertItemResponse(
                    title = "DSSSB Various Post Exam Date 2022",
                    tag = "DSSSB",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "CSBC Bihar Police Prohibition Constable Exam Date 2022",
                    tag = "CSBC",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "UPNHM Mental Health Various Post Admit Card 2022",
                    tag = "UPNHM",
                ),
                AlertItemResponse(
                    title = "Indian Navy SSR Admit Card 2022",
                    tag = "INDIAN",
                ),
                AlertItemResponse(
                    title = "IBPS RRB XI Officer Scale 1 Phase II Admit Card 2022",
                    tag = "IBPS",
                ),
                AlertItemResponse(
                    title = "UPPSC Staff Nurse 2021 Document Verification Letter",
                    tag = "UPPSC",
                ),
                AlertItemResponse(
                    title = "Supreme Court Junior Court Assistant Admit Card 2022",
                    tag = "SC",
                ),
                AlertItemResponse(
                    title = "DSSSB Various Post Exam Date 2022",
                    tag = "DSSSB",
                    date = 1697826600000
                ),
                AlertItemResponse(
                    title = "CSBC Bihar Police Prohibition Constable Exam Date 2022",
                    tag = "CSBC",
                    date = 1697826600000
                ),
            )
        )

    suspend fun getResultAlertsAndUpdates(): Response<List<AlertItemResponse>> =
        Response.success(
            listOf(
                AlertItemResponse(
                    title = "UP NHM Various 2900 Post Result 2022",
                    tag = "UP NHM",
                ),
                AlertItemResponse(
                    title = "UPPSC APO Pre 2022 Result",
                    tag = "UPPSC",
                ),
                AlertItemResponse(
                    title = "Indian Airforce AFCAT 02/2022 Result",
                    tag = "INDIAN",
                ),
                AlertItemResponse(
                    title = "BPSSC Enforcement Sub Inspector 2019 Final Result",
                    tag = "BPSSC",
                ),
                AlertItemResponse(
                    title = "JOSAA Allotment Result 2022",
                    tag = "JOSAA",
                ),
                AlertItemResponse(
                    title = "UPSC CDS II Result 2022",
                    tag = "UPSC",
                ),
                AlertItemResponse(
                    title = "SSC Stenographer 2020 Skill Test Result",
                    tag = "SSC",
                ),
                AlertItemResponse(
                    title = "UP NHM Various 2900 Post Result 2022",
                    tag = "UP NHM",
                ),
                AlertItemResponse(
                    title = "UPPSC APO Pre 2022 Result",
                    tag = "UPPSC",
                ),
                AlertItemResponse(
                    title = "Indian Airforce AFCAT 02/2022 Result",
                    tag = "INDIAN",
                ),
                AlertItemResponse(
                    title = "BPSSC Enforcement Sub Inspector 2019 Final Result",
                    tag = "BPSSC",
                ),
                AlertItemResponse(
                    title = "JOSAA Allotment Result 2022",
                    tag = "JOSAA",
                ),
                AlertItemResponse(
                    title = "UPSC CDS II Result 2022",
                    tag = "UPSC",
                ),
                AlertItemResponse(
                    title = "SSC Stenographer 2020 Skill Test Result",
                    tag = "SSC",
                ),
            )
        )
}