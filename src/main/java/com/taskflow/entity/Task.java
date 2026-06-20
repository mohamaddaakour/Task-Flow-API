package com.taskflow.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "tasks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String title;

   private String description;

   @Builder.Default
   @Column(nullable = false)
   private boolean done = false;

   @Column(nullable = false, updatable = false)
   private LocalDateTime createdAt;

   // PrePersist is to apply this method before saving to the database
   @PrePersist
   protected void onCreate() {
    this.createdAt = LocalDateTime.now();
   }
}
