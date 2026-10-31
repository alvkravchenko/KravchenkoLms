package com.example.lms.scheduler;

import com.example.lms.repository.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduleCleanupJob {

    private static final Logger log = LoggerFactory.getLogger(ScheduleCleanupJob.class);

    private final ScheduleRepository scheduleRepository;

    public ScheduleCleanupJob(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Scheduled(cron = "${app.cleanup.cron}")
    public void deleteOldSchedules() {
        LocalDate cutoff = LocalDate.now().minusYears(1);
        int deleted = scheduleRepository.deleteByEndDateBefore(cutoff);
        log.info("Deleted {} schedules with endDate before {}", deleted, cutoff);
    }
}