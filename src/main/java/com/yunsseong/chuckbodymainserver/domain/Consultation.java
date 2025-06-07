package com.yunsseong.chuckbodymainserver.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Setter
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Lob
    @Setter
    @Column(columnDefinition = "LONGBLOB")
    private byte[] voice;

    @Setter
    private String summary;

    @Setter
    private String tags;

    @Lob
    @Setter
    private String conversation;
}
