package TasksCreation.repository;

import TasksCreation.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task,Long>{

}