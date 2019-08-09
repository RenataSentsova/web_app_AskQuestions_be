package service.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.models.Category;
import service.models.Subcategory;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubcategoryDto {
    private Long id;
    private String nameSubcat;
    public static SubcategoryDto from(Subcategory subcategory) {
        return SubcategoryDto.builder()
                .id(subcategory.getId())
                .nameSubcat(subcategory.getNameSubcat())
                .build();
    }
    public static List<SubcategoryDto> from(List<Subcategory> subcategories) {
        return subcategories.stream().map(SubcategoryDto::from).collect(Collectors.toList());
    }
}
