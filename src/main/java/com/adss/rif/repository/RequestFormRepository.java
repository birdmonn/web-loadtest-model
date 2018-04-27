package com.adss.rif.repository;

import com.adss.rif.entities.RequestForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestFormRepository extends JpaRepository<RequestForm, Long> {

    @Query("SELECT rf FROM RequestForm rf WHERE rf.id = ?1 ")
    RequestForm findAsId(Long id);

    @Query("SELECT rf FROM RequestForm rf WHERE rf.projectId Like %?1% And rf.projectName Like %?2% And rf.contact Like %?3%" )
    List<RequestForm> findByProjectIdAndProjectNameAndContact(String projectId,String projectName,String contact);

    @Query("SELECT rf FROM RequestForm rf WHERE rf.projectId Like %?1%" )
    List<RequestForm> findByProjectId(String projectId);

    @Query("SELECT rf FROM RequestForm rf WHERE rf.projectName Like %?1% " )
    List<RequestForm> findByProjectName(String projectName);

    @Query("SELECT rf FROM RequestForm rf WHERE rf.contact Like %?1%" )
    List<RequestForm> findByContact(String contact);

    @Query("SELECT rf FROM RequestForm rf WHERE rf.projectId Like %?1% And rf.projectName Like %?2%" )
    List<RequestForm> findByProjectIdAndProjectName(String projectId,String projectName);

    @Query("SELECT rf FROM RequestForm rf WHERE rf.projectId Like %?1% And rf.contact Like %?2%" )
    List<RequestForm> findByProjectIdAndContact(String projectId,String contact);

    @Query("SELECT rf FROM RequestForm rf WHERE rf.projectName Like %?1% And rf.contact Like %?2% " )
    List<RequestForm> findByProjectNameAndContact(String projectName,String contact);
}
