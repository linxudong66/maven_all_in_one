package com.maven.court.dao.api;

import com.maven.court.entity.Emp;

public interface EmpDao {

    Emp selectEmpByLoginAccount(String loginAccount, String encodedLoginPassword);
}
