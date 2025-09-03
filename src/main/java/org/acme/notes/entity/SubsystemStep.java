/*
package org.acme.notes.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.Instant;
import java.util.UUID;

public class SubsystemStep {

    @Id
    @GeneratedValue
    public UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id", nullable = false)
    public DetailRecord detailRecord;

    @Column(name = "step_name", nullable = false)
    public String stepName;

    @Column(name = "step_status")
    public String stepStatus;

    @Column(name = "step_timestamp")
    public Instant stepTimestamp = Instant.now();

    @Type(type = "jsonb")
    @Column(name = "step_data", columnDefinition = "jsonb")
    public String stepData; // Store JSON as String or map to POJO
}
*/
