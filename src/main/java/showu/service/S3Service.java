/* S3Service.java
 * showU Service - 자랑
 * 게시물 레파지토리
 * 작성자 : lion4 (김예린, 배희창, 이홍비, 전익주, 채혜송)
 * 최종 수정 날짜 : 2025.02.08
 *
 * ========================================================
 * 프로그램 수정 / 보완 이력
 * ========================================================
 * 작업자       날짜       수정 / 보완 내용
 * ========================================================
 * 배희창   2025.02.09    최초 작성 : S3Service작성
 * ========================================================
 */

package showu.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

	private final AmazonS3 amazonS3;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	private final String DIR_NAME = "posts"; // S3에 저장할 폴더명

	/**
	 * S3에 파일 업로드 후 URL 반환
	 */
	@Transactional
	public String uploadS3File(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new IllegalArgumentException("파일이 비어 있습니다.");
		}

		String originalFilename = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		String newFilename = uuid + "_" + originalFilename;
		String s3Key = DIR_NAME + "/" + newFilename;

		try {
			// 메타데이터 설정
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(file.getSize());

			// S3에 업로드
			amazonS3.putObject(bucketName, s3Key, file.getInputStream(), metadata);

			// 업로드된 파일의 URL 반환
			return amazonS3.getUrl(bucketName, s3Key).toString();
		} catch (IOException e) {
			throw new RuntimeException("S3 파일 업로드 실패", e);
		}
	}

	// post 삭제시 s3 파일 삭제
	public void deleteS3File(String imageUrl) {
		if (imageUrl == null || imageUrl.isEmpty()) {
			return;
		}

		try {
			// ✅ URL 디코딩 추가 (한글 깨짐 방지)
			String decodedUrl = URLDecoder.decode(imageUrl, StandardCharsets.UTF_8);

			// ✅ "posts/" 이후의 전체 파일명을 가져와야 함 (UUID 포함)
			String objectKey = decodedUrl.substring(decodedUrl.indexOf(DIR_NAME + "/") + DIR_NAME.length() + 1);
			String s3Key = DIR_NAME + "/" + objectKey;

			// S3에서 파일 삭제
			amazonS3.deleteObject(new DeleteObjectRequest(bucketName, s3Key));
			System.out.println("✅ S3에서 파일 삭제 완료: " + s3Key);
		} catch (Exception e) {
			System.err.println("❌ S3 파일 삭제 실패: " + e.getMessage());
		}
	}
}
