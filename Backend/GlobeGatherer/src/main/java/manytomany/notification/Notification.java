package manytomany.notification;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String adminName;

    @Lob
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent")
    private Date sent = new Date();

    public Notification() {};

    public Notification(String adminName, String content) {
        this.adminName = adminName;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    
}
