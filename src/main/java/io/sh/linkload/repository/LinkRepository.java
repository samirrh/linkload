package io.sh.linkload.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.sh.linkload.dto.LinkRequest;
import io.sh.linkload.model.Link;
import io.sh.linkload.model.User;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    Collection<Link> findAllByUser(User user);

}
