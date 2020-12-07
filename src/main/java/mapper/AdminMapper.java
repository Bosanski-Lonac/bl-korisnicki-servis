package mapper;

import dto.AdminCreateDto;
import dto.AdminDto;
import model.Admin;

public class AdminMapper {
	public AdminDto AdminToAdminDto(Admin admin) {
		AdminDto adminDto=new AdminDto();
		adminDto.setUsername(admin.getUsername());
		adminDto.setPassword(admin.getPassword());
		return adminDto;
	}
	
	public Admin AdminToAdminDto(AdminCreateDto adminCreateDto) {
		Admin admin=new Admin();
		admin.setUsername(adminCreateDto.getUsername());
		admin.setPassword(adminCreateDto.getPassword());
		return admin;
	}
}
