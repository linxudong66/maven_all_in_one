package com.maven.court.service.api;

import com.maven.court.entity.Emp;

public interface EmpService {
    Emp getEmpByLoginAccount(String loginAccount, String loginPassword);
}
