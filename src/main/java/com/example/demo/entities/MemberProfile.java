package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "member_profiles")
public class MemberProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String address;
    @Column
    private String phoneNumber;
    @Column
    private String additionalInfo;
    // One-to-One relationship with Member
    @OneToOne
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;
}
