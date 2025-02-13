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
* 전익주    2025.02.10    RestController에서 Controller로 변경
* 배희창    2025.02.11    /categories 부분 추가
* ========================================================
*/ 

package showu.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import showu.dto.CategoryDTO;
import showu.service.CategoryService;

@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Controller
public class CategoryController {
	private final CategoryService categoryService;
	
	// json category 받기용 추가
	@GetMapping("/categories")
	@ResponseBody
	public List<CategoryDTO> getCategories() {
	    return categoryService.getAllCategory();
	}
	
	// 카테고리 추가
	@PostMapping("/category")
	public String addCategory(@RequestParam("cname") String cname, ModelMap map) {
		CategoryDTO category = categoryService.addCategory(cname);
		List<CategoryDTO> categories = categoryService.getAllCategory();
		map.addAttribute("categories", categories);
		return "category";
	}
	
	// 카테고리 수정
	@PutMapping("/category/{cid}")
	public String editCategory(@PathVariable("cid") Long cid, @RequestParam("cname") String cname, ModelMap map) {
		CategoryDTO category = categoryService.editCategory(cid, cname);
		List<CategoryDTO> categories = categoryService.getAllCategory();
		map.addAttribute("categories", categories);
		return "category";
	}
	
	// 카테고리 삭제
	@DeleteMapping("/category/{cid}")
	public String deleteCategory(@PathVariable("cid") Long cid, ModelMap map) {
	    categoryService.delelteCategory(cid);
	    List<CategoryDTO> categories = categoryService.getAllCategory();
	    map.addAttribute("categories", categories);
	    return "category";
	}
}
