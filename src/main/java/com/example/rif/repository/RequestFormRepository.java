package com.example.rif.repository;

import com.example.rif.entities.RequestForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestFormRepository extends JpaRepository<RequestForm, Long> {

    @Query("SELECT rf FROM RequestForm rf WHERE rf.id = ?1 ")
    RequestForm findAsId(Long id);

    @Query("SELECT rf FROM RequestForm rf WHERE rf.projectId Like %?1% And rf.projectName Like %?2% And rf.contact Like %?3%" )
    List<RequestForm> findByprojectIdAndprojectNameAndcontact(String projectId,String projectName,String contact);
}
