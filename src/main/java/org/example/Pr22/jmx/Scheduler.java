package org.example.Pr22.jmx;

import lombok.AllArgsConstructor;
import org.example.Pr22.services.SchedulerService;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(description = "create backup Scheduler")
@AllArgsConstructor
public class Scheduler {
    private final SchedulerService service;
    @ManagedOperation(description = "start backup")
    public void backup() throws Exception {
        service.cleanBackupDB();
    }
}
