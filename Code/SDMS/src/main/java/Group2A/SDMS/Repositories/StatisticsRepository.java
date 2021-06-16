package Group2A.SDMS.Repositories;

import Group2A.SDMS.Entities.ProcurementOrder;
import Group2A.SDMS.Entities.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {

    Statistics findByStatsID(int statsID);

    @Query("FROM Statistics WHERE deleted = false")
    List<Statistics> getAllStatistics();

    @Modifying
    @Query("update Statistics set deleted = true where statsID = :statsID")
    void deleteStatisticsByID(@Param("statsID") int statsID);

    @Modifying
    @Query(value = "update Statistics set month = :month, year = :year, wages = :wages, pOrdersTotal = :pOrdersTotal," +
            "revenue = :revenue, income = :income where statsID = :statsID", nativeQuery = true)
    void editStatistics(@Param("month") Timestamp month, @Param("year") int year, @Param("wages") double wages,
                        @Param("pOrdersTotal") double pOrdersTotal, @Param("revenue") double revenue,
                        @Param("income") double income, @Param("statsID") int statsID);
}
