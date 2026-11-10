package com.example.lms.specification;

import com.example.lms.dto.filter.ScheduleFilterDTO;
import com.example.lms.entity.Schedule;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ScheduleSpecification {
    public static Specification<Schedule> byFilter(ScheduleFilterDTO filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getGroupId() != null) {
                predicates.add(cb.equal(root.get("group").get("id"), filter.getGroupId()));
            }
            if (filter.getTeacherId() != null) {
                predicates.add(cb.equal(root.get("teacher").get("id"), filter.getTeacherId()));
            }
            if (filter.getStartDateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), filter.getStartDateFrom()));
            }
            if (filter.getStartDateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), filter.getStartDateTo()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}