package com.rereverb.advertisement.entity;

import com.rereverb.advertisement.model.AdvertisementStatus;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "advertisement")
@TypeDefs({
        @TypeDef(
                name = "pgsql_enum",
                typeClass = PostgreSQLEnumType.class
        )
})
@NoArgsConstructor
@Getter
@Setter
public class AdvertisementEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column("user_id")
    private UUID userId;

    @Column
    private String header;

    @Column
    private String description;

    @Column
    private double price;

    @Type(type = "pgsql_enum")
    @Enumerated(EnumType.STRING)

    @Column(name = "status", columnDefinition = "advertisement_status_type")
    private AdvertisementStatus status;
}
