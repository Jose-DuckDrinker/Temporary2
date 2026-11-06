package com.acme.reporting;

import com.acme.model.Report;
import org.springframework.stereotype.Component;

/** PROF-115: a different class with a method of the same name that only formats output. */
@Component
public class RenderPipeline {
    public void settle(Report report, String caller) {
        report.setId(report.getId());
    }
}
