package fr.esgi.pa.editing_together_api.app.projects.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "snippet")
public class SnippetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String content;

    @Column(name = "project_id")
    private Integer projectId;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_user_id")
    private Long createUserId;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user_id")
    private Long updateUserId;

}
