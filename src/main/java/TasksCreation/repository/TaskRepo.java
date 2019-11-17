package TasksCreation.repository;

import TasksCreation.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface TaskRepo extends CrudRepository<Task,Long>{
    Iterable<Task> findByAssignee(String assignee);
    Iterable<Task> findByStartDate(Date start);
    Iterable<Task> findByEndDate(Date end);
    Iterable<Task> findByAssigneeAndStartDateAndEndDate(String assignee, Date start, Date end);
    Iterable<Task> findByStartDateAndEndDate(Date start, Date end);
    Iterable<Task> findByAssigneeAndStartDate(String assignee, Date start);
    Iterable<Task> findByAssigneeAndEndDate(String assignee, Date end);
}