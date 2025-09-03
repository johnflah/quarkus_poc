/*
package org.acme.notes.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "detail_records")
public class DetailRecord {
    @Id
    @GeneratedValue
    public UUID id;

    @Column(name = "entity_id", nullable = false)
    public UUID entityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subsystem_id", nullable = false)
    public Subsystem subsystem;

    @Column(name = "created_at")
    public Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "detailRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<SubsystemStep> subsystemSteps;
}
*/
