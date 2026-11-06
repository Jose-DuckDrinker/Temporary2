package com.acme.controller;

import com.acme.model.Report;
import com.acme.reporting.RenderPipeline;
import com.acme.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private RenderPipeline renderPipeline;

    // PROF-115: calls a method also named settle(), but on an unrelated class. It must NOT be
    // credited with the ownership check that EntitlementGate#settle represents.
    @GetMapping("/{reportId}")
    public Report getReport(@PathVariable Long reportId, Principal principal) {
        Report report = reportRepository.findById(reportId).orElseThrow();
        renderPipeline.settle(report, principal.getName());
        return report;
    }
}