package org.acme.notes.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 2000, nullable = false)
    public String content;

    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    // Soft delete flag
    @Column(nullable = false)
    public boolean isDeleted = false;

    // Reply structure
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_note_id")
    public Note parentNote;

    // Case association
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "object_id", nullable = false)
    public Object objectId;

    // Subsystem association
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subsystem_id", nullable = false)
    public Subsystem subsystem;

    // Category within subsystem
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    public Category category;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
