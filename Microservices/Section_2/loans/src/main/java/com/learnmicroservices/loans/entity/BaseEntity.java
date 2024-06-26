package com.learnmicroservices.loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntity {

    @CreatedDate
    @Column(name = "created_at",updatable = false)
    public LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by",updatable = false)
    public String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at",insertable = false)
    public LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by",insertable = false)
    public String updatedBy;

}
