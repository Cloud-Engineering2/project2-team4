/* CategoryController.java
* 카테고리 컨트롤러
* 작성자 : 전익주
* 최종 수정 날짜 : 25.02.08
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 작업자       날짜       수정 / 보완 내용
* ========================================================
* 전익주    2025.02.08    Category Controller 생성
* 전익주    2025.02.08    Category 조회, 추가, 수정, 삭제 기능 구현
* 전익주    2025.02.09    CategoryDto 기반으로 메서드 수정
* ========================================================
*/ 

package showu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import showu.dto.CategoryDTO;
import showu.service.CategoryService;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@RestController
public class CategoryController {
	private final CategoryService categoryService;
	
	// 모든 카테고리 조회
	@GetMapping("/category")
	public List<CategoryDTO> getCategories() {
		List<CategoryDTO> categories = categoryService.getAllCategory();
		return categories;
	}
	
	// 카테고리 추가
	@PostMapping("/category")
	public CategoryDTO addCategory(String cname) {
		CategoryDTO category = categoryService.addCategory(cname);
		return category;
	}
	
	// 카테고리 수정
	@PutMapping("/category/{cid}")
	public CategoryDTO editCategory(@PathVariable("cid") Long cid, String cname) {
		CategoryDTO category = categoryService.editCategory(cid, cname);
		return category;
	}
	
	// 카테고리 삭제
	@DeleteMapping("/category/{cid}")
	public String delelteCategory(@PathVariable("cid") Long cid) {
		categoryService.delelteCategory(cid);
		return "Category deleted successfully";
	}
}
