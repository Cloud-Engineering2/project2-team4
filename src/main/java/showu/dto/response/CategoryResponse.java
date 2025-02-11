package showu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import showu.dto.CategoryDTO;

@Getter
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;

    public static CategoryResponse from(CategoryDTO categoryDTO) {
        return new CategoryResponse(categoryDTO.getId(), categoryDTO.getName());
    }
}
