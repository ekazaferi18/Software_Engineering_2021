package Group2A.SDMS.Services;

import Group2A.SDMS.Entities.ProductCategories;
import Group2A.SDMS.Entities.Statistics;
import Group2A.SDMS.Repositories.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    public Statistics getCategoryID(int statsID){
        return statisticsRepository.findByStatsID(statsID);
    }

    public List<Statistics> getAllStatistics(){
        return statisticsRepository.getAllStatistics();
    }

    public void saveStatistics(Statistics statistics){ statisticsRepository.save(statistics); }

    public void deleteStatisticsByID(int statsID){
        statisticsRepository.deleteStatisticsByID(statsID);
    }

}
