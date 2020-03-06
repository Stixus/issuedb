package issuedb.Entities;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "issue")
@EntityListeners(AuditingEntityListener.class)
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "issue", nullable = false)
    private String issue;
    @Column(name = "resolved", nullable = false)
    private boolean resolved;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public boolean getResolved() { return resolved; }

    public void setResolved(boolean resolved) { this.resolved = resolved; }
}
