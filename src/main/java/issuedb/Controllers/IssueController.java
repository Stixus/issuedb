package issuedb.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import issuedb.Entities.Issue;
import issuedb.Exceptions.ResourceNotFoundException;
import issuedb.Repositories.IssueRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class IssueController {
    @Autowired
    private IssueRepository issueRepository;

    /**
     * Get all issues list.
     *
     * @return the list
     */
    @GetMapping("/issues")
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    /**
     * Gets issues by id.
     *
     * @param issueId the issue id
     * @return the issues by id
     * @throws ResourceNotFoundException the resource not found exception
     */


    @GetMapping("/issue/{id}")
    public ResponseEntity<Issue> getIssuesById(@PathVariable(value = "id") Long issueId)
            throws ResourceNotFoundException {
        Issue issue =
                issueRepository
                        .findById(issueId)
                        .orElseThrow(() -> new ResourceNotFoundException("Issue not found on :: " + issueId));
        return ResponseEntity.ok().body(issue);
    }

    /**
     * Create issue.
     *
     * @param issue the issue
     * @return the issue
     */

    @PostMapping("/issue")
    public Issue createIssue(@Valid @RequestBody Issue issue) {
        return issueRepository.save(issue);
    }

    /**
     * Update issue response entity.
     *
     * @param issueId the issue id
     * @param issueDetails the issue details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/issue/{id}")
    public ResponseEntity<Issue> updateIssue(
            @PathVariable(value = "id") Long issueId, @Valid @RequestBody Issue issueDetails)
            throws ResourceNotFoundException {
        Issue issue =
                issueRepository
                        .findById(issueId)
                        .orElseThrow(() -> new ResourceNotFoundException("Issue not found on :: " + issueId));
        issue.setEmail(issueDetails.getEmail());
        issue.setIssue(issueDetails.getIssue());
        issue.setResolved(issueDetails.getResolved());
        final Issue updatedIssue = issueRepository.save(issue);
        return ResponseEntity.ok(updatedIssue);
    }

    /**
     * Delete issue map.
     *
     * @param issueId the issue id
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/issue/{id}")
    public Map<String, Boolean> deleteIssue(@PathVariable(value = "id") Long issueId) throws Exception {
        Issue issue =
                issueRepository
                        .findById(issueId)
                        .orElseThrow(() -> new ResourceNotFoundException("Issue not found on :: " + issueId));
        issueRepository.delete(issue);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
