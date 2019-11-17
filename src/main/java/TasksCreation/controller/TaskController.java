package TasksCreation.controller;

import TasksCreation.domain.Task;
import TasksCreation.repository.TaskRepo;
import TasksCreation.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class TaskController {

    @Autowired
    private TaskRepo taskRepo;

    /*
    * Базовое отображение страницы с задачами
    * */
    @GetMapping("/tasks")
    public String findTasks(Map<String, Object> model) {
        Iterable<Task> tasks = taskRepo.findAll();

        model.put("assignees", getAssignees(tasks));
        model.put("tasks", tasks);

        return "findTasks";
    }


    /*
   * Возвращает на страницу данные по заданным критериям
   * */
    @PostMapping("filter")
    public String filter(@RequestParam Map<String, String> form, Map<String, Object> model) {
        Iterable<Task> tasks = taskRepo.findAll();

        model.put("assignees", getAssignees(tasks));

        String assignee = form.get("assignee");
        String startDate = form.get("startDate");
        String endDate = form.get("endDate");
        String allAssignees = "All assignees";

        if (!Validation.isDateValid(startDate, endDate)) {
            model.put("message", "wrong date format!");
            return "findTasks";
        }

        if (!startDate.isEmpty() && !endDate.isEmpty()) {
            if (!assignee.equals(allAssignees)) {
                tasks = taskRepo.findByAssigneeAndStartDateAndEndDate(assignee,
                        Date.valueOf(startDate), Date.valueOf(endDate));
            } else {
                tasks = taskRepo.findByStartDateAndEndDate(Date.valueOf(startDate), Date.valueOf(endDate));
            }
        } else if (!startDate.isEmpty() && endDate.isEmpty()) {
            if (!assignee.equals(allAssignees)) {
                tasks = taskRepo.findByAssigneeAndStartDate(assignee, Date.valueOf(startDate));
            } else {
                tasks = taskRepo.findByStartDate(Date.valueOf(startDate));
            }
        } else if (startDate.isEmpty() && !endDate.isEmpty()) {
            if (!assignee.equals(allAssignees)) {
                tasks = taskRepo.findByAssigneeAndEndDate(assignee, Date.valueOf(endDate));
            } else {
                tasks = taskRepo.findByEndDate(Date.valueOf(endDate));
            }
        } else if (startDate.isEmpty() && endDate.isEmpty() && !assignee.equals(allAssignees)) {
            tasks = taskRepo.findByAssignee(assignee);
        }

        if(!tasks.iterator().hasNext()){
            model.put("message", "no data by criteria");
        }

        model.put("tasks", tasks);
        return "findTasks";
    }

    /*
    * Получение всех возможных Assignees
    * */
    public Set<String> getAssignees(Iterable<Task> tasks) {
        Set<String> assignees = new HashSet<>();
        for (Task t : tasks) {
            assignees.add(t.getAssignee());
        }
        return assignees;
    }

}
