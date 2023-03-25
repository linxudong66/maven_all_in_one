package com.maven.court.service.impl;

import com.maven.court.dao.api.MemorialsDao;
import com.maven.court.dao.impl.MemorialsDaoImpl;
import com.maven.court.entity.Memorials;
import com.maven.court.service.api.MemorialsService;

import java.util.List;

public class MemorialsServiceImpl implements MemorialsService {
    private MemorialsDao memorialsDao = new MemorialsDaoImpl();

    @Override
    public List<Memorials> gatAllMemorialsDigest() {
        return memorialsDao.selectAllMemorialsDigest();
    }
}
