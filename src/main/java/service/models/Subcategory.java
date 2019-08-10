package service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "subcategory")
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name_subcat")
    private String nameSubcat;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;
    @OneToMany(mappedBy = "subcategory")
    private List<Question> questions;
    public Subcategory(Long id, String nameSubcat, Category category){
        this.id = id;
        this.nameSubcat = nameSubcat;
        this.category = category;
    }
}
