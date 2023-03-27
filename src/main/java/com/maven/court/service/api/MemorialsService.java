package com.maven.court.service.api;

import com.maven.court.entity.Memorials;

import java.util.List;

public interface MemorialsService {

    List<Memorials> gatAllMemorialsDigest();

    Memorials gatAllMemorialsDetailById(String memorialsId);

    void updateMemorialsStatusToRead(String memorialsId);

    void updateMemorialsFeedBack(String memorialsId, String feedbackContent);
}
