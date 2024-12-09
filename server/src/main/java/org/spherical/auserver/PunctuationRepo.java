package org.spherical.auserver;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PunctuationRepo extends JpaRepository<Punctuation, Integer> {
}
