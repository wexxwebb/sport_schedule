package services;

import common.Result;

import java.io.Serializable;
import java.util.Map;

public interface RegisterService extends Serializable {

    Result<Map<String, String>> register(String login, String password, String passwordApprove,
                                         String name, String lastName, String sex, String birthday);

}
