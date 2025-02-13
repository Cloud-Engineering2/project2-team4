/* CategoryService.java
* 카테고리 서비스
* 작성자 : 전익주
* 최종 수정 날짜 : 25.02.08
*
* ========================================================
* 프로그램 수정 / 보완 이력
* ========================================================
* 작업자       날짜       수정 / 보완 내용
* ========================================================
* 전익주    2025.02.08    Category Service 생성
* 전익주    2025.02.08    Category 조회, 추가, 수정, 삭제 기능 구현
* 전익주    2025.02.09    CategoryDto 기반으로 메서드 수정
* ========================================================
*/ 


package showu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import showu.dto.CategoryDTO;
import showu.entity.Category;
import showu.repository.CategoryRepository;

@RequiredArgsConstructor
@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	// 모든 카테고리 조회
	public List<CategoryDTO> getAllCategory() {
		return categoryRepository.findAll().stream().map(CategoryDTO::from).toList();
	}
	
	// 카테고리 추가
	public CategoryDTO addCategory(String cname) {
		return CategoryDTO.from(categoryRepository.save(Category.of(cname)));
	}
	
	// 카테고리 수정
	@Transactional
	public CategoryDTO editCategory(Long cid, String cname) {
		Category category = categoryRepository.findById(cid)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 카테고리입니다: " + cid));
		category.setCname(cname);
		return CategoryDTO.from(category);
	}
	
	// 카테고리 삭제
	public void delelteCategory(Long cid) {
		Category category = categoryRepository.findById(cid)
				.orElseThrow(() -> new IllegalStateException("존재하지 않는 카테고리입니다: " + cid));
		categoryRepository.deleteById(cid);
	}
}
