package by.chebatul.task6.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceProvider {

	private static final ServiceProvider INSTANCE = new ServiceProvider();

	private UserService userService = new UserService();
	private UserRoleService userRoleService = new UserRoleService();
	private AdminRoleService adminRoleService = new AdminRoleService();

	public UserService getUserService() {
		return userService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public AdminRoleService getAdminRoleService() {
		return adminRoleService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public void setAdminRoleService(AdminRoleService adminRoleService) {
		this.adminRoleService = adminRoleService;
	}

	public static ServiceProvider getInstance() {
		return INSTANCE;
	}

}
