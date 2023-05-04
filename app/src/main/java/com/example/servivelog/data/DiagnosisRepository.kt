package com.example.servivelog.data

import com.example.servivelog.data.database.dao.DiagnosisDao
import javax.inject.Inject

class DiagnosisRepository @Inject constructor(
    private val diagnosisDao: DiagnosisDao
) {
}