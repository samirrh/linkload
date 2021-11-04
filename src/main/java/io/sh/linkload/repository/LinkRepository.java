package io.sh.linkload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.sh.linkload.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

}
