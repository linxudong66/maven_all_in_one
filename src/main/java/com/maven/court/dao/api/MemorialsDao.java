package com.maven.court.dao.api;

import com.maven.court.entity.Memorials;

import java.util.List;

public interface MemorialsDao {
    List<Memorials> selectAllMemorialsDigest();
}
