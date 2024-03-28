package com.ssafy.domain.company.entity;

import com.ssafy.domain.common.embeddable.Factor;
import com.ssafy.domain.patent.entity.Patent;

import com.ssafy.domain.users.entity.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "found_at")
    private String foundAt;

    @Column(name = "owner")
    private String owner;

    @Column(name = "form")
    private String form;

    @Column(name = "capital")
    private Long capital;

    @Column(name = "employee_cnt")
    private int employeeCnt;

    @Column(name = "type")
    private String type;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "address")
    private String address;

    @Embedded
    private Factor factor;

    @NotNull
    @Column(name = "youth_company")
    private boolean youthCompany;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "views")
    private Long views;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyStatement> companyStatements;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyRate> companyRates;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Patent> patents;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private CompanyYouth companyYouth;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private CompanyQuarterInfo companyQuarterInfo;


    public void companyViewsCnt (){
        this.views++;
    }

}
