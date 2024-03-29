package com.ssafy.domain.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "financial_statement")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "financial_statement_id")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "asset_value_type")
    private AssetValueType assetValueType;

    @NotNull
    @Column(name = "first_quarter")
    private Long firstQuarter;

    @NotNull
    @Column(name = "second_quarter")
    private Long secondQuarter;

    @NotNull
    @Column(name = "third_quarter")
    private Long thirdQuarter;

}
