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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name_cat")
    private String nameCategory;

    @OneToMany(mappedBy = "category")
    List<Subcategory> subcategories;

    public Category(Long id, String nameCategory){
        this.id = id;
        this.nameCategory = nameCategory;
    }
}
