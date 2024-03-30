package com.ssafy.domain.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "financial_rate")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "financial_rate_id")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "asset_rate_type")
    private AssetRateType assetRateType;

    @NotNull
    @Column(name = "first_quarter")
    private float firstQuarter;

    @NotNull
    @Column(name = "second_quarter")
    private float secondQuarter;

    @NotNull
    @Column(name = "third_quarter")
    private float thirdQuarter;

}
