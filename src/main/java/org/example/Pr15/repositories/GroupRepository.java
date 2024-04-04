package org.example.Pr15.repositories;

import org.example.Pr15.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
