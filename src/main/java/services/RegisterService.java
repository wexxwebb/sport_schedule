package services;

import common.RegisterDataCheck;
import common.Result;

import java.util.Map;

public interface RegisterService {

    Result<Map<String, String>> register(String login, String password, String passwordApprove,
                                         String name, String lastName, String sex, String birthday);

}
