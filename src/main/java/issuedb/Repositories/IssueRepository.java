package issuedb.Repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import issuedb.Entities.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>{}
