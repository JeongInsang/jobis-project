package com.ssafy.domain.users.entity;

import com.ssafy.domain.common.embeddable.Factor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tendency")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tendency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tendency_id")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @Embedded
    private Factor factor;

}
