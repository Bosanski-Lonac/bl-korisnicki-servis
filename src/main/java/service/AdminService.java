package service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dto.AdminCreateDto;
import dto.AdminDto;
import dto.AdminUpdateDto;

public interface AdminService {
	Page<AdminDto> findAll(Pageable pageable);
	AdminDto findById(String id);
	AdminDto add(AdminCreateDto adminCreateDto);
	AdminDto update(String id, AdminUpdateDto adminUpdateDto);
	void deleteById(String id);
}
