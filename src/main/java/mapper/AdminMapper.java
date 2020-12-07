package mapper;

import dto.AdminDto;
import model.Admin;

public class AdminMapper {
	public AdminDto AdminToAdminDto(Admin admin) {
		AdminDto adminDto=new AdminDto();
		adminDto.setUsername(admin.getUsername());
		adminDto.setPassword(admin.getPassword());
		return adminDto;
	}
	
	public Admin AdminToAdminDto(AdminDto adminDto) {
		Admin admin=new Admin();
		admin.setUsername(adminDto.getUsername());
		admin.setPassword(adminDto.getPassword());
		return admin;
	}
}
