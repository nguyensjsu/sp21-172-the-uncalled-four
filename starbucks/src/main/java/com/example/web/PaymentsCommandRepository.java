package com.example.web;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsCommandRepository extends JpaRepository<PaymentsCommand,Long> {
}