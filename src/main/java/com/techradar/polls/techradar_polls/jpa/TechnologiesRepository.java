package com.techradar.polls.techradar_polls.jpa;

import com.techradar.polls.techradar_polls.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechnologiesRepository extends JpaRepository<Technology, Long> {
    @Query(value = "select * from Technologies t where (?1 is null or t.cat_id = ?1) and (?2 is null or t.sec_id = ?2)", nativeQuery = true)
    List<Technology> findTechnologiesByCategoryAndSection(Long catId, Long secId);

    Technology findTechnologiesById(Long id);
}
//
//@Query( value = """
//        select * from Technologies t
//        join Categories c
//           on t.cat_ID = c.cat_ID
//        join Sections s
//           on s.sec_Id = t.sec_Id
//        join Rings r
//           on r.ring_id = t.ring_id
//        join Statuses st
//           on st.stat_id = t.stat_id
//        where (?1 is null or c.cat_name = ?1)
//          and (?2 is null or s.sec_name = ?2)
//        """, nativeQuery = true)
//List<Technologies> findTechnologiesByCategoryAndSection(String catName, String secName);
//
//}