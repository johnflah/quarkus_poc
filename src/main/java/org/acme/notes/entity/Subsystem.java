package org.acme.notes.entity;

import jakarta.persistence.*; import java.time.Instant; import java.util.List; import java.util.UUID;

import org.hibernate.annotations.TypeDef;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Table(name = "subsystems")
public class Subsystem {
    @Id
    @GeneratedValue
    public UUID id;

    @Column(nullable = false, unique = true)
    public String name;

    public String displayName;

    @Column(name = "created_at")
    public Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "subsystem", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DetailRecord> detailRecords;
}
