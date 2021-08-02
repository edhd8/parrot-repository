package io.parrotsoftware.pos.common.responses

import io.parrotsoftware.pos.common.dto.Report

data class ReportResponse(
    var mostSelledProducts: List<Report>
)