package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
	Optional<Admin> findAdminByUsernameAndPassword(String username, String password);
}
