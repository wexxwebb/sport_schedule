package services;

import common.RegisterDataCheck;

public interface RegisterService {

    RegisterDataCheck register(
            String login, String password, String passwordApprove,
            String name, String lastName, String sex, String birthday
    );

}
